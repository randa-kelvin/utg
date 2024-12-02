package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.PurchaseOrderTransactionsRequestDTO;
import com.untucapital.usuite.utg.dto.response.PurchaseOrderTransactionsResponseDTO;
import com.untucapital.usuite.utg.dto.response.RequisitionsResponseDTO;
import com.untucapital.usuite.utg.exception.DuplicateEntryException;
import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import com.untucapital.usuite.utg.model.po.Requisitions;
import com.untucapital.usuite.utg.pos.dto.POSSupplierDto;
import com.untucapital.usuite.utg.pos.model.POSCategory;
import com.untucapital.usuite.utg.pos.model.POSParameter;
import com.untucapital.usuite.utg.pos.service.POSCategoryService;
import com.untucapital.usuite.utg.pos.service.POSParameterService;
import com.untucapital.usuite.utg.pos.service.SupplierService;
import com.untucapital.usuite.utg.repository.PurchaseOrderTransactionsRepository;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@javax.transaction.Transactional
@RequiredArgsConstructor
public class PurchaseOrderTransactionsService {

    @Autowired
    private PurchaseOrderTransactionsRepository purchaseOrderTransactionsRepository;

    private final POSCategoryService posCategoryService;

    private final SupplierService supplierService;

    private final POSParameterService parameterService;

    //    private final RequisitionService requisitionService;
    private final RequisitionRepository requisitionRepository;

    private final UserService userService;

    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactionsResponseDTO> getAllPurchaseOrderTransactions() {
        List<PurchaseOrderTransactionsResponseDTO> response = new ArrayList<>();
        List<PurchaseOrderTransactions> purchaseOrderTransactionsList = purchaseOrderTransactionsRepository.findAll();
        RequisitionsResponseDTO requisitionsResponseDTO = new RequisitionsResponseDTO();

        for (PurchaseOrderTransactions purchaseOrderTransaction : purchaseOrderTransactionsList) {
            PurchaseOrderTransactionsResponseDTO responseDTO = new PurchaseOrderTransactionsResponseDTO();

            // Copy properties from entity to DTO
            BeanUtils.copyProperties(purchaseOrderTransaction, responseDTO);

            // Fetch and set Supplier details
            Integer supplierId = Integer.valueOf(purchaseOrderTransaction.getPoSupplier());
            POSSupplierDto supplierDto = supplierService.getSupplierById(supplierId);
            responseDTO.setPoSupplierName(supplierDto.getName());

            // Fetch and set Category details
            Integer categoryId = Integer.valueOf(purchaseOrderTransaction.getPoCategory());
            POSCategory category = posCategoryService.getCategoryById(categoryId);
            responseDTO.setPoCategoryName(category.getName());

            // Fetch and set Requisition details
            String requisitionId = purchaseOrderTransaction.getPoRequisitionId();
            Optional<Requisitions> requisitionDto = requisitionRepository.findById(requisitionId);

            if (requisitionDto.isPresent()) {
                Requisitions requisitions1 = requisitionDto.get();
                log.info("Requisitions1:{}", requisitions1);
                BeanUtils.copyProperties(requisitions1, requisitionsResponseDTO);
                log.info("RequisitionsResponseDTO:{}", requisitionsResponseDTO);

            }

            responseDTO.setPoRequisitionName(requisitionsResponseDTO.getPoName());
//            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");

            responseDTO.setPoOriginator(requisitionsResponseDTO.getUserId() != null ? getUserFullName(requisitionsResponseDTO.getUserId()) : "");
            responseDTO.setCmsApprover(requisitionsResponseDTO.getCmsApproverName());
            responseDTO.setPoApprover(requisitionsResponseDTO.getPoApproverName());

            responseDTO.setPoNumber(requisitionsResponseDTO.getPoNumber());
            response.add(responseDTO);
        }

        return response;
    }

    private String getUserFullName(String userId) {
        return userService.getUserById(userId)
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .orElse("");
    }


    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactionsResponseDTO> getByRequisitionId(String requisitionId) {
        Optional<List<PurchaseOrderTransactions>> transactionsOptional = purchaseOrderTransactionsRepository
                .getPurchaseOrderTransactionsByPoRequisitionId(requisitionId);

        if (transactionsOptional.isPresent()) {
            List<PurchaseOrderTransactions> transactions = transactionsOptional.get();
            return transactions.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private PurchaseOrderTransactionsResponseDTO convertToDTO(PurchaseOrderTransactions transaction) {
        PurchaseOrderTransactionsResponseDTO dto = new PurchaseOrderTransactionsResponseDTO();

        // Copy properties from entity to DTO
        BeanUtils.copyProperties(transaction, dto);

        // Fetch and set Supplier details
        Integer supplierId = Integer.valueOf(transaction.getPoSupplier());
        POSSupplierDto supplierDto = supplierService.getSupplierById(supplierId);
        dto.setPoSupplierName(supplierDto.getName());

        // Fetch and set Category details
        Integer categoryId = Integer.valueOf(transaction.getPoCategory());
        POSCategory category = posCategoryService.getCategoryById(categoryId);
        dto.setPoCategoryName(category.getName());

        // Calculate ZIMRA Tax
        Double totalAmount = Double.valueOf(transaction.getPoAmount());
        Double discountedAmount = calculateZimraTax(totalAmount, supplierDto);

        dto.setZimraTax(discountedAmount);

        return dto;
    }

//    private Double calculateZimraTax(Double totalAmount, POSSupplierDto supplierDto) {
//        if ("No".equalsIgnoreCase(supplierDto.getTaxClearance())) {
//            List<ParameterDto> parameters = parameterService.getAllParameters(); // Assuming you have a method to fetch all parameters
//            for (ParameterDto parameter : parameters) {
//                if (totalAmount > parameter.getCumulative()) {
//                    return totalAmount * parameter.getTax() / 100;
//                }
//            }
//        }
//        return 0.0;
//    }

    private Double calculateZimraTax(Double totalAmount, POSSupplierDto supplierDto) {
        if ("No".equalsIgnoreCase(supplierDto.getTaxClearance()) || supplierDto.getTaxClearance() == null) {
            List<POSParameter> parameters = parameterService.getAllParameters(); // Assuming you have a method to fetch all parameters
            for (POSParameter parameter : parameters) {
                if (totalAmount > parameter.getCumulative()) {
                    return totalAmount * parameter.getTax() / 100;
                }
            }
        }
        return 0.0;
    }


//    private PurchaseOrderTransactionsResponseDTO convertToDTO(PurchaseOrderTransactions transaction) {
//        PurchaseOrderTransactionsResponseDTO dto = new PurchaseOrderTransactionsResponseDTO();
//        BeanUtils.copyProperties(transaction, dto);
//        return dto;
//    }

    @Transactional(value = "transactionManager")
    public void savePurchaseOrderTransaction(PurchaseOrderTransactionsRequestDTO request) {

        PurchaseOrderTransactions purchaseOrderTransactions = new PurchaseOrderTransactions();
        if (!purchaseOrderTransactionsRepository.existsByPoItemAndPoSupplierAndPoCategoryAndPoQuantityAndPoAmountAndPoRequisitionId(
                request.getPoItem(), request.getPoSupplier(), request.getPoCategory(),
                request.getPoQuantity(), request.getPoAmount(), request.getPoRequisitionId())) {

            BeanUtils.copyProperties(request, purchaseOrderTransactions);
            purchaseOrderTransactionsRepository.save(purchaseOrderTransactions);
        } else {
            throw new DuplicateEntryException("Duplicate entry for PurchaseOrderTransactions");
        }
    }

    @Transactional(value = "transactionManager")
    public PurchaseOrderTransactionsResponseDTO getPurchaseOrderTransactionById(String id) {

        PurchaseOrderTransactionsResponseDTO response = new PurchaseOrderTransactionsResponseDTO();
        Optional<PurchaseOrderTransactions> purchaseOrderTransactions = purchaseOrderTransactionsRepository.findById(id);

        if (purchaseOrderTransactions.isPresent()) {

            PurchaseOrderTransactions purchaseOrderTransactions1 = purchaseOrderTransactions.get();
            BeanUtils.copyProperties(purchaseOrderTransactions1, response);

            return response;
        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactionsResponseDTO> getPurchaseOrderTransactionsByRequisitionId(String id) {

        List<PurchaseOrderTransactionsResponseDTO> response = new ArrayList<>();
        Optional<List<PurchaseOrderTransactions>> purchaseOrderTransactionsList = purchaseOrderTransactionsRepository.getPurchaseOrderTransactionsByPoRequisitionId(id);

        if (purchaseOrderTransactionsList.isPresent()) {
            List<PurchaseOrderTransactions> purchaseOrderTransactionsList1 = purchaseOrderTransactionsList.get();
            for (PurchaseOrderTransactions purchaseOrderTransaction : purchaseOrderTransactionsList1) {
                PurchaseOrderTransactionsResponseDTO responseDTO = new PurchaseOrderTransactionsResponseDTO();
                BeanUtils.copyProperties(purchaseOrderTransaction, responseDTO);
                response.add(responseDTO);
            }
        } else {
            return Collections.emptyList();
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public long getTransactionCountByRequisitionId(String id) {
        Optional<List<PurchaseOrderTransactions>> purchaseOrderTransactionsList = purchaseOrderTransactionsRepository
                .getPurchaseOrderTransactionsByPoRequisitionId(id);

        return purchaseOrderTransactionsList.map(List::size).orElse(0);
    }

    @Transactional(value = "transactionManager")
    public List<PurchaseOrderTransactions> getPurchaseOrderTransactionsByCategory(String category) {
        return purchaseOrderTransactionsRepository.findPurchaseOrderTransactionsByPoCategory(category);
    }

    @Transactional(value = "transactionManager")
    public void deletePurchaseOrderTransaction(String id) {
        purchaseOrderTransactionsRepository.deleteById(id);
    }
}
