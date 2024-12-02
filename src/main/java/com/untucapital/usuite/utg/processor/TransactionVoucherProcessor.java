package com.untucapital.usuite.utg.processor;

import com.untucapital.usuite.utg.dto.cms.*;
import com.untucapital.usuite.utg.dto.cms.res.TransactionPurposeResponseDTO;
import com.untucapital.usuite.utg.dto.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.TransactionPurpose;
import com.untucapital.usuite.utg.model.cms.TransactionVoucher;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.repository.cms.TransactionPurposeRepository;
import com.untucapital.usuite.utg.repository.cms.TransactionVoucherRepository;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.service.cms.SequenceService;
import com.untucapital.usuite.utg.service.cms.TransactionPurposeService;
import com.untucapital.usuite.utg.service.cms.VaultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class TransactionVoucherProcessor {

    private final VaultService vaultService;
    private final UserService userService;
    private final TransactionPurposeService transactionPurposeService;
    private final SequenceService sequenceService;

    private final TransactionVoucherRepository transactionVoucherRepository;
    private final TransactionPurposeRepository transactionPurposeRepository;

//    private Long getReferenceNumber() {
//        TransactionVoucher transactionVoucher = transactionVoucherRepository.findFirstByOrderByCreatedAtDesc();
//        if (transactionVoucher.getReferenceNumber()==null){
//            return 0L;
//        }
//
//        int index = transactionVoucher.getReferenceNumber().lastIndexOf("/");
//        return Long.parseLong(transactionVoucher.getReferenceNumber().substring(index + 1))+1;
//    }


    public TransactionVoucher processTransactionVoucher(TransactionVoucherInitiatorRequest request){

        Vault fromVault = new Vault();
        Vault toVault = new Vault();
        User user = userService.find(request.getInitiator()).orElseThrow();

        User firstApprover = userService.find(request.getFirstApprover()).orElseThrow();
        User secondApprover = userService.find(request.getSecondApprover()).orElseThrow();

        VaultResponseDTO vaultFrom = vaultService.getVault(Integer.valueOf(request.getFromVault()));
        log.debug("Vault From :{}",vaultFrom);
        BeanUtils.copyProperties(vaultFrom, fromVault);
        log.debug("Vault From :{}",fromVault);
        VaultResponseDTO vaultTo = vaultService.getVault(Integer.valueOf(request.getToVault()));
        log.debug("Vault To :{}",vaultTo);
        BeanUtils.copyProperties(vaultTo, toVault);
        log.debug("Vault To :{}",toVault);

        String transactionCode = String.format("%s%05d", request.getVaultCode(), sequenceService.getNextTransactionId());


        TransactionPurpose transactionPurpose = new TransactionPurpose();
//        TransactionPurposeResponseDTO transactionPurposeResponseDTO = transactionPurposeService.getById(Integer.valueOf(request.getWithdrawalPurpose()));
//        BeanUtils.copyProperties(transactionPurposeResponseDTO, transactionPurpose);

        TransactionPurpose purpose = transactionPurposeRepository.getById(Integer.valueOf(request.getWithdrawalPurpose()));

        Branches branch = fromVault.getBranch();



        return TransactionVoucher.builder()
                .initiator(user)
                .applicationDate(request.getApplicationDate())
                .amount(request.getAmount())
                .fromVault(fromVault)
                .reference(transactionCode)
                .toVault(toVault)
                .amountInWords(request.getAmountInWords())
                .withdrawalPurpose(Integer.parseInt(request.getWithdrawalPurpose()))
                .currency(request.getCurrency())
                .denomination100(request.getDenomination100())
                .denomination50(request.getDenomination50())
                .denomination20(request.getDenomination20())
                .denomination10(request.getDenomination10())
                .denomination5(request.getDenomination5())
                .denomination2(request.getDenomination2())
                .denomination1(request.getDenomination1())
                .denominationCents(request.getDenominationCents())
                .branch(branch)
                .firstApprover(firstApprover)
                .firstApprovalStatus(ApprovalStatus.PENDING)
                .secondApprover(secondApprover)
                .secondApprovalStatus(ApprovalStatus.PENDING)
                .build();
    }

    public TransactionVoucher processUpdatedTransactionVoucher(TransactionVoucher transactionVoucher,TransactionVoucherUpdateRequest request) {

        Vault fromVault = new Vault();
        Vault toVault = new Vault();

        Integer fromVaultOldId = transactionVoucher.getFromVault().getId();
        Integer toVaultOldId = transactionVoucher.getToVault().getId();
        Integer oldTransactionId = transactionVoucher.getWithdrawalPurpose();

        if (request.getFromVault() != null) {
            fromVaultOldId = request.getFromVault();
        }

        if (request.getToVault() != null) {
            toVaultOldId = request.getToVault();
        }

        if (request.getWithdrawalPurpose() != null) {
            oldTransactionId = request.getWithdrawalPurpose();
        }

        VaultResponseDTO vaultFrom = vaultService.getVault(fromVaultOldId);
        BeanUtils.copyProperties(vaultFrom, fromVault);
        VaultResponseDTO vaultTo = vaultService.getVault(toVaultOldId);
        BeanUtils.copyProperties(vaultTo, toVault);

        TransactionPurpose transactionPurpose = new TransactionPurpose();
        TransactionPurpose purpose = transactionPurposeRepository.getById(oldTransactionId);

        TransactionPurposeResponseDTO transactionPurposeResponseDTO = transactionPurposeService.getById(oldTransactionId);
        BeanUtils.copyProperties(transactionPurposeResponseDTO, transactionPurpose);

        if (transactionVoucher.getFirstApprovalStatus() == ApprovalStatus.APPROVED && transactionVoucher.getSecondApprovalStatus() == ApprovalStatus.APPROVED) {
            throw new RuntimeException("Transaction already Approved.");
        }

        if (request.getAmount() != null && !request.getAmount().equals(transactionVoucher.getAmount())) {
            transactionVoucher.setAmount(request.getAmount());
        }

        if (request.getFromVault() != null && fromVault != transactionVoucher.getFromVault()) {
            transactionVoucher.setFromVault(fromVault);
        }

        if (request.getToVault() != null && toVault != transactionVoucher.getToVault()) {
            transactionVoucher.setToVault(toVault);
        }

        if (request.getAmountInWords() != null && !request.getAmountInWords().equalsIgnoreCase(transactionVoucher.getAmountInWords())) {
            transactionVoucher.setAmountInWords(request.getAmountInWords());
        }

        if (request.getWithdrawalPurpose() != null && transactionPurpose.getId() != transactionVoucher.getWithdrawalPurpose()) {
            transactionVoucher.setWithdrawalPurpose(transactionPurpose.getId());
        }

            if (request.getApplicationDate() != null && !request.getApplicationDate().equalsIgnoreCase(String.valueOf(transactionVoucher.getApplicationDate()))) {
                transactionVoucher.setApplicationDate(LocalDate.parse(request.getApplicationDate()));
            }


            if (request.getCurrency() != null && !request.getCurrency().equalsIgnoreCase(transactionVoucher.getCurrency())) {
                transactionVoucher.setCurrency(request.getCurrency());
            }

            if (request.getDenomination100() != null && !request.getDenomination100().equals(transactionVoucher.getDenomination100())) {
                transactionVoucher.setDenomination100(request.getDenomination100());
            }

            if (request.getDenomination50() != null && !request.getDenomination50().equals(transactionVoucher.getDenomination50())) {
                transactionVoucher.setDenomination50(request.getDenomination50());
            }

            if (request.getDenomination20() != null && !request.getDenomination20().equals(transactionVoucher.getDenomination20())) {
                transactionVoucher.setDenomination20(request.getDenomination20());
            }

            if (request.getDenomination10() != null && !request.getDenomination10().equals(transactionVoucher.getDenomination10())) {
                transactionVoucher.setDenomination10(request.getDenomination10());
            }

            if (request.getDenomination5() != null && !request.getDenomination5().equals(transactionVoucher.getDenomination5())) {
                transactionVoucher.setDenomination5(request.getDenomination5());
            }

            if (request.getDenomination2() != null && !request.getDenomination2().equals(transactionVoucher.getDenomination2())) {
                transactionVoucher.setDenomination2(request.getDenomination2());
            }

            if (request.getDenomination1() != null && !request.getDenomination1().equals(transactionVoucher.getDenomination1())) {
                transactionVoucher.setDenomination1(request.getDenomination1());
            }

            if (request.getDenominationCents() != null && !request.getDenominationCents().equals(transactionVoucher.getDenominationCents())) {
                transactionVoucher.setDenomination1(request.getDenominationCents());
            }

            transactionVoucher.setFirstApprovalStatus(ApprovalStatus.PENDING);
            transactionVoucher.setSecondApprovalStatus(ApprovalStatus.PENDING);

            return transactionVoucher;

    }
    public TransactionVoucherResponse transactionVoucherResponseSerializer(TransactionVoucher transaction, TransactionPurposeResponseDTO transactionPurpose) {

        UserDTO initiator = UserDTO.builder()
                .id(transaction.getInitiator().getId())
                .firstName(transaction.getInitiator().getFirstName())
                .lastName(transaction.getInitiator().getLastName())
                .build();

        UserDTO firstApprover = UserDTO.builder()
                .id(transaction.getFirstApprover().getId())
                .firstName(transaction.getFirstApprover().getFirstName())
                .lastName(transaction.getFirstApprover().getLastName())
                .build();

        UserDTO secondApprover = UserDTO.builder()
                .id(transaction.getSecondApprover().getId())
                .firstName(transaction.getSecondApprover().getFirstName())
                .lastName(transaction.getSecondApprover().getLastName())
                .build();

        BranchDTO branch = BranchDTO.builder()
                .id(transaction.getBranch().getId())
                .name(transaction.getBranch().getBranchName())
                .build();

        VaultDTO fromVault = VaultDTO.builder()
                .id(transaction.getFromVault().getId())
                .account(transaction.getFromVault().getAccount())
                .type(transaction.getFromVault().getType())
                .name(transaction.getFromVault().getName())
                .build();

        VaultDTO toVault = VaultDTO.builder()
                .id(transaction.getToVault().getId())
                .account(transaction.getToVault().getAccount())
                .type(transaction.getToVault().getType())
                .name(transaction.getToVault().getName())
                .build();



        TransactionVoucherResponse response = new TransactionVoucherResponse();
        response.setId(transaction.getId());
//        response.setApplicationNo(createApplicationId(transaction.getApplicationDate(), transaction.getId()));
        response.setInitiator(initiator);
        response.setApplicationDate(transaction.getApplicationDate());
        response.setFirstApprover(firstApprover);
        response.setFirstApprovedAt(dateFormatter(transaction.getFirstApprovedAt()));
        response.setFirstApprovalStatus(transaction.getFirstApprovalStatus());
        response.setFirstApprovalComment(transaction.getFirstApprovalComment());
        response.setSecondApprover(secondApprover);
        response.setSecondApprovedAt(dateFormatter(transaction.getSecondApprovedAt()));
        response.setSecondApprovalStatus(transaction.getSecondApprovalStatus());
        response.setSecondApprovalComment(transaction.getSecondApprovalComment());
        response.setAmountInWords(transaction.getAmountInWords());
        response.setCurrency(transaction.getCurrency());
        response.setAmount(transaction.getAmount());
        response.setDenomination1(transaction.getDenomination1());
        response.setDenomination2(transaction.getDenomination2());
        response.setDenomination5(transaction.getDenomination5());
        response.setDenomination10(transaction.getDenomination10());
        response.setDenomination20(transaction.getDenomination20());
        response.setDenomination50(transaction.getDenomination50());
        response.setDenomination100(transaction.getDenomination100());
        response.setDenominationCents(transaction.getDenominationCents());
        response.setWithdrawalPurpose(String.valueOf(transaction.getWithdrawalPurpose()));
        response.setBranch(branch);
        response.setFromVault(fromVault);
        response.setToVault(toVault);
        response.setReference(transaction.getReference());

        return response;
}

        public List<TransactionVoucherResponse> transactionVouchersResponseSerializer(List<TransactionVoucher> transactionVouchers) {


//        return transactionVouchers.stream()
//                .map(this::transactionVoucherResponseSerializer)
//                .collect(Collectors.toList());

        List<TransactionVoucherResponse> transactionVoucherResponses = new ArrayList<>();
        for (TransactionVoucher transaction: transactionVouchers ){

            UserDTO initiator = UserDTO.builder()
                    .id(transaction.getInitiator().getId())
                    .firstName(transaction.getInitiator().getFirstName())
                    .lastName(transaction.getInitiator().getLastName())
                    .build();

            UserDTO firstApprover = UserDTO.builder()
                    .id(transaction.getFirstApprover().getId())
                    .firstName(transaction.getFirstApprover().getFirstName())
                    .lastName(transaction.getFirstApprover().getLastName())
                    .build();

            UserDTO secondApprover = UserDTO.builder()
                    .id(transaction.getSecondApprover().getId())
                    .firstName(transaction.getSecondApprover().getFirstName())
                    .lastName(transaction.getSecondApprover().getLastName())
                    .build();

            BranchDTO branch = BranchDTO.builder()
                    .id(transaction.getBranch().getId())
                    .name(transaction.getBranch().getBranchName())
                    .build();

            VaultDTO fromVault = VaultDTO.builder()
                    .id(transaction.getFromVault().getId())
                    .account(transaction.getFromVault().getAccount())
                    .type(transaction.getFromVault().getType())
                    .name(transaction.getFromVault().getName())
                    .build();

            VaultDTO toVault = VaultDTO.builder()
                    .id(transaction.getToVault().getId())
                    .account(transaction.getToVault().getAccount())
                    .type(transaction.getToVault().getType())
                    .name(transaction.getToVault().getName())
                    .build();


            TransactionVoucherResponse transactionVoucherResponse = new TransactionVoucherResponse();

            transactionVoucherResponse.setId(transaction.getId());
            transactionVoucherResponse.setInitiator(initiator);
            transactionVoucherResponse.setApplicationDate(transaction.getApplicationDate());
            transactionVoucherResponse.setFirstApprover(firstApprover);
            transactionVoucherResponse.setFirstApprovedAt(dateFormatter(transaction.getFirstApprovedAt()));
            transactionVoucherResponse.setFirstApprovalStatus(transaction.getFirstApprovalStatus());
            transactionVoucherResponse.setFirstApprovalComment(transaction.getFirstApprovalComment());
            transactionVoucherResponse.setSecondApprover(secondApprover);
            transactionVoucherResponse.setSecondApprovedAt(dateFormatter(transaction.getSecondApprovedAt()));
            transactionVoucherResponse.setSecondApprovalStatus(transaction.getSecondApprovalStatus());
            transactionVoucherResponse.setSecondApprovalComment(transaction.getSecondApprovalComment());
            transactionVoucherResponse.setAmountInWords(transaction.getAmountInWords());
            transactionVoucherResponse.setCurrency(transaction.getCurrency());
            transactionVoucherResponse.setAmount(transaction.getAmount());
            transactionVoucherResponse.setDenomination1(transaction.getDenomination1());
            transactionVoucherResponse.setDenomination2(transaction.getDenomination2());
            transactionVoucherResponse.setDenomination5(transaction.getDenomination5());
            transactionVoucherResponse.setDenomination10(transaction.getDenomination10());
            transactionVoucherResponse.setDenomination20(transaction.getDenomination20());
            transactionVoucherResponse.setDenomination50(transaction.getDenomination50());
            transactionVoucherResponse.setDenomination100(transaction.getDenomination100());
            transactionVoucherResponse.setDenominationCents(transaction.getDenominationCents());
            transactionVoucherResponse.setWithdrawalPurpose(String.valueOf(transaction.getWithdrawalPurpose()));
            transactionVoucherResponse.setBranch(branch);
            transactionVoucherResponse.setFromVault(fromVault);
            transactionVoucherResponse.setToVault(toVault);
            transactionVoucherResponse.setReference(transaction.getReference());

//            return transactionVoucherResponse;


//            BeanUtils.copyProperties(tr, transactionVoucherResponse);
            transactionVoucherResponses.add(transactionVoucherResponse);
        }
        return transactionVoucherResponses;
    }


    private String dateFormatter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (HH:mm)");

        if (date == null) {
            return null;
        }

        return date.format(formatter);
    }

    public String createDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
