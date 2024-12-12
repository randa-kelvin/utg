package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.dto.request.RequisitionsRequestDTO;
import com.untucapital.usuite.utg.dto.response.PurchaseOrderTransactionsResponseDTO;
import com.untucapital.usuite.utg.dto.response.RequisitionsResponseDTO;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.po.Requisitions;
import com.untucapital.usuite.utg.pos.dto.POSSupplierDto;
import com.untucapital.usuite.utg.pos.model.Expenditure;
import com.untucapital.usuite.utg.pos.model.POSParameter;
import com.untucapital.usuite.utg.pos.repository.ExpenditureRepository;
import com.untucapital.usuite.utg.pos.service.POSParameterService;
import com.untucapital.usuite.utg.pos.service.SupplierService;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
@Slf4j
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private PurchaseOrderTransactionsService purchaseOrderTransactionsService;

    @Autowired
    private POSParameterService parameterService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private UserService userService;



    @Transactional(value = "transactionManager")
//    public List<RequisitionsResponseDTO> getAllRequistions() {
//
//        List<RequisitionsResponseDTO> response = new ArrayList<>();
//        List<Requisitions> requisitionsList= requisitionRepository.findAll();
//        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));
//
//        for (Requisitions requisition : requisitionsList) {
//            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
//            BeanUtils.copyProperties(requisition, responseDTO);
//            response.add(responseDTO);
//        }
//
//        return response;
//    }

//    public List<RequisitionsResponseDTO> getAllRequistions() {
//        List<RequisitionsResponseDTO> response = new ArrayList<>();
//        List<Requisitions> requisitionsList = requisitionRepository.findAll();
//        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));
//
//        for (Requisitions requisition : requisitionsList) {
//            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
//            BeanUtils.copyProperties(requisition, responseDTO);
//
//            // Calculate totalAmount
//            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
//                    .getByRequisitionId(requisition.getId());
//
//            double totalAmount = transactions.stream()
//                    .mapToInt(transaction -> Integer.parseInt(transaction.getPoAmount()))
//                    .sum();
//
//            responseDTO.setPoTotal(String.valueOf(totalAmount));
//            responseDTO.setTransactions(transactions); // Set the transactions
//
//            response.add(responseDTO);
//        }
//
//        return response;
//    }

    public List<RequisitionsResponseDTO> getAllRequistions() {
        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList = requisitionRepository.findAll();
        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);

            // Fetch transactions
            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
                    .getByRequisitionId(requisition.getId());

            // Calculate totalAmount
            double totalAmount = transactions.stream()
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getPoAmount()))
                    .sum();


            // Calculate poCount
            int poCount = transactions.size();

            // Calculate ZIMRA tax
            Double zimraTax = 0.0;
            if (!transactions.isEmpty()) {
                String poSupplier = transactions.get(0).getPoSupplier();
                zimraTax = calculateZimraTax(totalAmount, poSupplier);
            }

            // Set total amount, poCount, and zimraTax
            responseDTO.setPoTotal(String.valueOf(totalAmount));
            responseDTO.setPoCount(String.valueOf(poCount));
            responseDTO.setZimraTax(String.valueOf(zimraTax));
//            responseDTO.setTransactions(transactions);

            // Fetch and set approver details with null checks
            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");
            responseDTO.setCmsApproverName(requisition.getCmsApprover() != null ? getUserFullName(requisition.getCmsApprover()) : "");
            responseDTO.setTellerName(requisition.getTeller() != null ? getUserFullName(requisition.getTeller()) : "");


            response.add(responseDTO);
        }

        return response;
    }

    private String getUserFullName(String userId) {
        return userService.getUserById(userId)
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .orElse("");
    }


    private Double calculateZimraTax(double totalAmount, String supplierId) {
        // Fetch supplier details
        POSSupplierDto supplierDto = supplierService.getSupplierById(Integer.valueOf(supplierId));

        // Fetch parameters (assuming parameters() returns a list of parameter objects)
        List<POSParameter> parameters = parameterService.getAllParameters();

        // Calculate ZIMRA tax based on supplier and total amount
        if ("No".equals(supplierDto.getTaxClearance()) || supplierDto.getTaxClearance() == null) {
            for (POSParameter param : parameters) {
                if (totalAmount > param.getCumulative()) {
                    return (double) (totalAmount * param.getTax() / 100);
                }
            }
        }

        return 0.0;
    }


    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO saveRequisition(RequisitionsRequestDTO request) {

        Requisitions requisitions = new Requisitions();
        RequisitionsResponseDTO response = new RequisitionsResponseDTO();
        Expenditure expenditure = new Expenditure();

        BeanUtils.copyProperties(request, requisitions);

       Requisitions requisitions1 = requisitionRepository.save(requisitions);
       BeanUtils.copyProperties(requisitions1,response);
        return response;
    }




    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO getRequisitionById(String id) {

        RequisitionsResponseDTO requisitionsResponseDTO = new RequisitionsResponseDTO();
        log.info("ID: " + id);
        Optional<Requisitions> requisitions =  requisitionRepository.findById(id);

        log.info("Requisitions:{}", requisitions);

        if(requisitions.isPresent()) {
            Requisitions requisitions1 = requisitions.get();
            log.info("Requisitions1:{}", requisitions1);
            BeanUtils.copyProperties(requisitions1,requisitionsResponseDTO);
            log.info("RequisitionsResponseDTO:{}", requisitionsResponseDTO);

            return requisitionsResponseDTO;
        }else {
            return null;
        }

    }


//    @Transactional(value = "transactionManager")
//    public List<RequisitionsResponseDTO> getRequisitionByUserId(String userid) {
//
//        List<RequisitionsResponseDTO> requisitionsResponseDTO = new ArrayList<>();
//        List<Requisitions> requisitions = requisitionRepository.findRequisitionsByUserId(userid);
//
//        for (Requisitions requisition : requisitions) {
//            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
//            BeanUtils.copyProperties(requisition, responseDTO);
//            requisitionsResponseDTO.add(responseDTO);
//        }
//
//        return requisitionsResponseDTO;
//
////        return requisitionRepository.findRequisitionsByUserId(userid);
//    }

    @Transactional(value = "transactionManager")
    public List<RequisitionsResponseDTO> getRequisitionByUserId(String userid) {

        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList = requisitionRepository.findRequisitionsByUserId(userid);
        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);

            // Fetch transactions
            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
                    .getByRequisitionId(requisition.getId());

            // Calculate totalAmount
            double totalAmount = transactions.stream()
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getPoAmount()))
                    .sum();


            // Calculate poCount
            int poCount = transactions.size();

            // Calculate ZIMRA tax
            Double zimraTax = 0.0;
            if (!transactions.isEmpty()) {
                String poSupplier = transactions.get(0).getPoSupplier();
                zimraTax = calculateZimraTax(totalAmount, poSupplier);
            }

            // Set total amount, poCount, and zimraTax
            responseDTO.setPoTotal(String.valueOf(totalAmount));
            responseDTO.setPoCount(String.valueOf(poCount));
            responseDTO.setZimraTax(String.valueOf(zimraTax));
//        responseDTO.setTransactions(transactions);

            // Fetch and set approver details with null checks
            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");
            responseDTO.setCmsApproverName(requisition.getCmsApprover() != null ? getUserFullName(requisition.getCmsApprover()) : "");
            responseDTO.setTellerName(requisition.getTeller() != null ? getUserFullName(requisition.getTeller()) : "");

            response.add(responseDTO);
        }

        return response;
    }


//    @Transactional(value = "transactionManager")
//    public List<RequisitionsResponseDTO> getRequisitionByApproverId(String userid) {
//
//        List<RequisitionsResponseDTO> requisitionsResponseDTO = new ArrayList<>();
//        List<Requisitions> requisitions = requisitionRepository.findRequisitionByApprovers(userid);
//
//        for (Requisitions requisition : requisitions) {
//            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
//            BeanUtils.copyProperties(requisition, responseDTO);
//            requisitionsResponseDTO.add(responseDTO);
//        }
//
//        return requisitionsResponseDTO;
//
//    }

    @Transactional(value = "transactionManager")
    public List<RequisitionsResponseDTO> getRequisitionByApproverId(String userid) {
        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList = requisitionRepository.findRequisitionByApprovers(userid);
        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);

            // Fetch transactions
            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
                    .getByRequisitionId(requisition.getId());

            // Calculate totalAmount
            double totalAmount = transactions.stream()
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getPoAmount()))
                    .sum();


            // Calculate poCount
            int poCount = transactions.size();

            // Calculate ZIMRA tax
            Double zimraTax = 0.0;
            if (!transactions.isEmpty()) {
                String poSupplier = transactions.get(0).getPoSupplier();
                zimraTax = calculateZimraTax(totalAmount, poSupplier);
            }

            // Set total amount, poCount, and zimraTax
            responseDTO.setPoTotal(String.valueOf(totalAmount));
            responseDTO.setPoCount(String.valueOf(poCount));
            responseDTO.setZimraTax(String.valueOf(zimraTax));

            // Fetch and set approver details with null checks
            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");
            responseDTO.setCmsApproverName(requisition.getCmsApprover() != null ? getUserFullName(requisition.getCmsApprover()) : "");
            responseDTO.setTellerName(requisition.getTeller() != null ? getUserFullName(requisition.getTeller()) : "");

            response.add(responseDTO);
        }

        return response;
    }



//    @Transactional(value = "transactionManager")
//    public List<RequisitionsResponseDTO> getRequisitionsToBeApprovedByFinance() {
//
//        List<RequisitionsResponseDTO> requisitionsResponseDTO = new ArrayList<>();
//        List<Requisitions> requisitions = requisitionRepository.findRequisitionsByPoApproverIsNotNullAndCmsApproverIsNull();
//
//        for (Requisitions requisition : requisitions) {
//            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
//            BeanUtils.copyProperties(requisition, responseDTO);
//            requisitionsResponseDTO.add(responseDTO);
//        }
//
//        return requisitionsResponseDTO;
//
//    }


    @Transactional(value = "transactionManager")
    public List<RequisitionsResponseDTO> getRequisitionsToBeApprovedByFinance() {
        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList = requisitionRepository.findRequisitionsByPoApproverIsNotNullAndCmsApproverIsNull();
        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);

            // Fetch transactions
            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
                    .getByRequisitionId(requisition.getId());

            // Calculate totalAmount
            double totalAmount = transactions.stream()
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getPoAmount()))
                    .sum();

            // Calculate poCount
            int poCount = transactions.size();

            // Calculate ZIMRA tax
            Double zimraTax = 0.0;
            if (!transactions.isEmpty()) {
                String poSupplier = transactions.get(0).getPoSupplier();
                zimraTax = calculateZimraTax(totalAmount, poSupplier);
            }

            // Set total amount, poCount, and zimraTax
            responseDTO.setPoTotal(String.valueOf(totalAmount));
            responseDTO.setPoCount(String.valueOf(poCount));
            responseDTO.setZimraTax(String.valueOf(zimraTax));

            // Fetch and set approver details with null checks
            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");
            responseDTO.setCmsApproverName(requisition.getCmsApprover() != null ? getUserFullName(requisition.getCmsApprover()) : "");
            responseDTO.setTellerName(requisition.getTeller() != null ? getUserFullName(requisition.getTeller()) : "");

            response.add(responseDTO);
        }

        return response;
    }


//    @Transactional(value = "transactionManager")
//    public List<RequisitionsResponseDTO> getRequisitionsApprovedByFinance() {
//
//        List<RequisitionsResponseDTO> requisitionsResponseDTO = new ArrayList<>();
//        List<Requisitions> requisitions = requisitionRepository.findRequisitionsByPoApproverIsNotNullAndCmsApproverIsNotNull();
//
//        for (Requisitions requisition : requisitions) {
//            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
//            BeanUtils.copyProperties(requisition, responseDTO);
//            requisitionsResponseDTO.add(responseDTO);
//        }
//
//        return requisitionsResponseDTO;
//
//    }

    @Transactional(value = "transactionManager")
    public List<RequisitionsResponseDTO> getRequisitionsApprovedByFinance() {
        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList = requisitionRepository.findRequisitionsByPoApproverIsNotNullAndCmsApproverIsNotNull();
        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);

            // Fetch transactions
            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
                    .getByRequisitionId(requisition.getId());

            // Calculate totalAmount
            double totalAmount = transactions.stream()
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getPoAmount()))
                    .sum();

            // Calculate poCount
            int poCount = transactions.size();

            // Calculate ZIMRA tax
            Double zimraTax = 0.0;
            if (!transactions.isEmpty()) {
                String poSupplier = transactions.get(0).getPoSupplier();
                zimraTax = calculateZimraTax(totalAmount, poSupplier);
            }

            // Set total amount, poCount, and zimraTax
            responseDTO.setPoTotal(String.valueOf(totalAmount));
            responseDTO.setPoCount(String.valueOf(poCount));
            responseDTO.setZimraTax(String.valueOf(zimraTax));

            // Fetch and set approver details with null checks
            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");
            responseDTO.setCmsApproverName(requisition.getCmsApprover() != null ? getUserFullName(requisition.getCmsApprover()) : "");
            responseDTO.setTellerName(requisition.getTeller() != null ? getUserFullName(requisition.getTeller()) : "");

            response.add(responseDTO);
        }

        return response;
    }


//    @Transactional(value = "transactionManager")
//    public List<RequisitionsResponseDTO> getRequisitionsByTellerId(String tellerId) {
//
//        List<RequisitionsResponseDTO> requisitionsResponseDTO = new ArrayList<>();
//        List<Requisitions> requisitions = requisitionRepository.findRequisitionsByTeller(tellerId);
//
//        for (Requisitions requisition : requisitions) {
//            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
//            BeanUtils.copyProperties(requisition, responseDTO);
//            requisitionsResponseDTO.add(responseDTO);
//        }
//
//        return requisitionsResponseDTO;
//
//    }

    @Transactional(value = "transactionManager")
    public List<RequisitionsResponseDTO> getRequisitionsByTellerId(String tellerId) {
        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList = requisitionRepository.findRequisitionsByTeller(tellerId);
        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);

            // Fetch transactions
            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
                    .getByRequisitionId(requisition.getId());

            // Calculate totalAmount
            double totalAmount = transactions.stream()
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getPoAmount()))
                    .sum();

            // Calculate poCount
            int poCount = transactions.size();

            // Calculate ZIMRA tax
            Double zimraTax = 0.0;
            if (!transactions.isEmpty()) {
                String poSupplier = transactions.get(0).getPoSupplier();
                zimraTax = calculateZimraTax(totalAmount, poSupplier);
            }

            // Set total amount, poCount, and zimraTax
            responseDTO.setPoTotal(String.valueOf(totalAmount));
            responseDTO.setPoCount(String.valueOf(poCount));
            responseDTO.setZimraTax(String.valueOf(zimraTax));

            // Fetch and set approver details with null checks
            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");
            responseDTO.setCmsApproverName(requisition.getCmsApprover() != null ? getUserFullName(requisition.getCmsApprover()) : "");
            responseDTO.setTellerName(requisition.getTeller() != null ? getUserFullName(requisition.getTeller()) : "");

            response.add(responseDTO);
        }

        return response;
    }


//    @Transactional(value = "transactionManager")
//    public RequisitionsResponseDTO getRequisitionByPoNumber(String poNumber) {
//
//        RequisitionsResponseDTO response = new RequisitionsResponseDTO();
//        Optional<Requisitions> requisitions = requisitionRepository.getRequisitionsByPoNumber(poNumber);
//
//        if(requisitions.isPresent()) {
//            Requisitions requisitions1 = requisitions.get();
//            BeanUtils.copyProperties(requisitions1,response);
//
//            return response;
//        }else {
//            return null;
//        }
//    }


    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO getRequisitionByPoNumber(String poNumber) {
        RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
        Optional<Requisitions> requisitionsOpt = requisitionRepository.getRequisitionsByPoNumber(poNumber);

        if(requisitionsOpt.isPresent()) {
            Requisitions requisition = requisitionsOpt.get();
            BeanUtils.copyProperties(requisition, responseDTO);

            // Fetch transactions
            List<PurchaseOrderTransactionsResponseDTO> transactions = purchaseOrderTransactionsService
                    .getByRequisitionId(requisition.getId());

            // Calculate totalAmount
            double totalAmount = transactions.stream()
                    .mapToDouble(transaction -> Double.parseDouble(transaction.getPoAmount()))
                    .sum();

            // Calculate poCount
            int poCount = transactions.size();

            // Calculate ZIMRA tax
            Double zimraTax = 0.0;
            if (!transactions.isEmpty()) {
                String poSupplier = transactions.get(0).getPoSupplier();
                zimraTax = calculateZimraTax(totalAmount, poSupplier);
            }

            // Set total amount, poCount, and zimraTax
            responseDTO.setPoTotal(String.valueOf(totalAmount));
            responseDTO.setPoCount(String.valueOf(poCount));
            responseDTO.setZimraTax(String.valueOf(zimraTax));

            // Fetch and set approver details with null checks
            responseDTO.setPoApproverName(requisition.getPoApprover() != null ? getUserFullName(requisition.getPoApprover()) : "");
            responseDTO.setCmsApproverName(requisition.getCmsApprover() != null ? getUserFullName(requisition.getCmsApprover()) : "");
            responseDTO.setTellerName(requisition.getTeller() != null ? getUserFullName(requisition.getTeller()) : "");
        } else {
            return null;
        }

        return responseDTO;
    }


    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO updateRequisition(String poNumber) {

        RequisitionsResponseDTO response = new RequisitionsResponseDTO();
        Optional<Requisitions> requisitions = requisitionRepository.getRequisitionsByPoNumber(poNumber);

        if(requisitions.isPresent()) {
            Requisitions requisitions1 = requisitions.get();
            if(requisitions1.getPoStatus().equals("open")){

                Expenditure expenditure = new Expenditure();

                List<PurchaseOrderTransactionsResponseDTO> purchaseOrderTransactions = purchaseOrderTransactionsService.getPurchaseOrderTransactionsByRequisitionId(requisitions1.getId());

                for(PurchaseOrderTransactionsResponseDTO purchaseOrderTransactionsResponseDTO : purchaseOrderTransactions){

                    expenditure.setCategory(purchaseOrderTransactionsResponseDTO.getPoCategory());
                    expenditure.setAmount(BigDecimal.valueOf(Float.valueOf(purchaseOrderTransactionsResponseDTO.getPoAmount())));
                    expenditure.setMonth(LocalDateTime.now().getMonth().toString());
                    expenditure.setYear(LocalDateTime.now().getYear());
                    expenditureRepository.save(expenditure);
                }

                requisitions1.setPoStatus("closed");
                requisitionRepository.save(requisitions1);

                BeanUtils.copyProperties(requisitions1,response);

            }else{
                BeanUtils.copyProperties(requisitions1,response);
            }

            return response;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public void deleteRequisition(String id) {
        requisitionRepository.deleteById(id);
    }
}
