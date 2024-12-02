package com.untucapital.usuite.utg.service.cms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.commons.AppConstants;
import com.untucapital.usuite.utg.dto.cms.ApproverRequest;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherInitiatorRequest;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherResponse;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherUpdateRequest;
import com.untucapital.usuite.utg.dto.cms.res.Response;
import com.untucapital.usuite.utg.dto.cms.res.TransactionPurposeResponseDTO;
import com.untucapital.usuite.utg.dto.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.dto.pastel.PastelTransReq;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.*;
import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.processor.TransactionVoucherProcessor;
import com.untucapital.usuite.utg.repository.BranchRepository;
import com.untucapital.usuite.utg.repository.cms.AuthorisationRepository;
import com.untucapital.usuite.utg.repository.cms.CmsVaultPermissionRepository;
import com.untucapital.usuite.utg.repository.cms.TransactionPurposeRepository;
import com.untucapital.usuite.utg.repository.cms.TransactionVoucherRepository;
import com.untucapital.usuite.utg.service.PostGlService;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.EmailSender;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransactionVoucherService {

    private final TransactionVoucherRepository transactionVoucherRepository;
    private final TransactionPurposeRepository transactionPurposeRepository;
    private final AuthorisationRepository authorisationRepository;
    private final CmsVaultPermissionRepository cmsVaultPermissionRepository;
    private final VaultService vaultService;
    private final BranchRepository branchRepository;
    private final UserService userService;
    private final TransactionPurposeService transactionPurposeService;
    private final EmailSender emailSender;
    private final TransactionVoucherProcessor transactionVoucherProcessor;
    private final SequenceService sequenceService;
    private final PostGlService postGlService;



    @Value("${pastel.password}")
    private String apiPassword;

    @Value("${pastel.username}")
    private String apiUsername;


    //Initiate Transaction
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse initiateTransaction(TransactionVoucherInitiatorRequest request) {

        TransactionVoucherResponse respons = new TransactionVoucherResponse();
        User firstApprover = userService.find(request.getFirstApprover()).orElseThrow();
        User secondApprover = userService.find(request.getSecondApprover()).orElseThrow();

        User user = userService.find(request.getInitiator()).orElseThrow();

        Response res = new Response();

        TransactionPurposeResponseDTO transactionPurpose = transactionPurposeService.getById(Integer.valueOf(request.getWithdrawalPurpose()));
        TransactionVoucher transactionVoucher = transactionVoucherProcessor.processTransactionVoucher(request);

        log.info("Transaction Test :{}",transactionVoucher);

        Optional<TransactionVoucher> transaction =  transactionVoucherRepository.findByAmountAndAmountInWordsAndInitiator_IdAndDenomination100AndDenomination50AndDenomination20AndDenomination10AndDenomination5AndDenomination2AndDenomination1(transactionVoucher.getAmount(),
                transactionVoucher.getAmountInWords(),
                transactionVoucher.getInitiator().getId(),
                transactionVoucher.getDenomination100(),
                transactionVoucher.getDenomination50(),
                transactionVoucher.getDenomination20(),
                transactionVoucher.getDenomination10(),
                transactionVoucher.getDenomination5(),
                transactionVoucher.getDenomination2(),
                transactionVoucher.getDenomination1()
    );

        TransactionVoucher transactionVoucher1 = new TransactionVoucher();
        if (transaction.isPresent() && !request.isDuplicate()){
            if (transaction.get().getCreatedAt().isBefore(LocalDateTime.now().minusHours(8))){
                transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);
            } else {

                BeanUtils.copyProperties(request,respons);
                respons.setResponseCode(409);
                respons.setResponseMsg("Transaction already exist");

                log.info("Trans voucher response: {}", respons);
                return respons;
            }

        } else {
            transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);
        }


        sendEmail(
                firstApprover.getFirstName() + " " + firstApprover.getLastName(),
                firstApprover.getContactDetail().getEmailAddress(),
                "Transaction Approval",
//                "You have a new transaction to approve (" + transactionVoucherProcessor.createApplicationId(transactionVoucher1.getApplicationDate(), transactionVoucher1.getId()) + "). The transactional purpose is " + transactionPurpose.getName() + ".",
                "You have a new transaction to approve - " + transactionVoucher1.getReference() + ". The transactional purpose is " + transactionVoucher1.getWithdrawalPurpose() + ".",
                user.getFirstName() + " " + user.getLastName()
        );


        respons = transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher1, transactionPurpose);

        if (res.getResponseCode() != 0 && res.getResponseMsg() != null) {
            respons.setResponseCode(res.getResponseCode());
            respons.setResponseMsg(res.getResponseMsg());
        }

        return respons;
    }

    //First Approver Transaction
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse firstApproveTransaction(ApproverRequest request) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();
        TransactionPurposeResponseDTO transactionPurpose = transactionPurposeService.getById(transactionVoucher.getWithdrawalPurpose());

        if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
            transactionVoucher.setFirstApprovalStatus(ApprovalStatus.APPROVED);

            sendEmail(
                    transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName(),
                    transactionVoucher.getSecondApprover().getContactDetail().getEmailAddress(),
                    "Transaction Approval",
//                    "You have a new transaction to approve (" + transactionVoucherProcessor.createApplicationId(transactionVoucher.getApplicationDate(), transactionVoucher.getId()) + "). The transactional purpose is " + transactionPurpose.getName() + ".",
                    "You have a new transaction to approve - " + transactionVoucher.getReference() + ". The transactional purpose is " + transactionVoucher.getWithdrawalPurpose()+ ".",
                    transactionVoucher.getFirstApprover().getFirstName() + " " + transactionVoucher.getFirstApprover().getLastName()
            );
        }

        if (request.getApprovalStatus().equalsIgnoreCase("REVISE")) {
            transactionVoucher.setFirstApprovalStatus(ApprovalStatus.REVISE);
            transactionVoucher.setFirstApprovalComment(request.getComment());

            sendEmail(
                    transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                    transactionVoucher.getInitiator().getContactDetail().getEmailAddress(),
                    "Revise Transaction",
//                    "Revise transaction (" + transactionVoucherProcessor.createApplicationId(transactionVoucher.getApplicationDate(), transactionVoucher.getId()) + "). The transactional purpose is " + transactionPurpose.getName() + " ." + transactionVoucher.getFirstApprovalComment(),
                    "Revise transaction - " + transactionVoucher.getReference() + " The transactional purpose is " + transactionVoucher.getWithdrawalPurpose() + " ." + transactionVoucher.getFirstApprovalComment(),
                    transactionVoucher.getFirstApprover().getFirstName() + " " + transactionVoucher.getFirstApprover().getLastName()
            );
        }

        transactionVoucher.setFirstApprovedAt(LocalDateTime.now());

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);


        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher1, transactionPurpose);
    }

    //Second Approver Transaction
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse secondApproveTransaction(ApproverRequest request) throws ParseException, JsonProcessingException {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();
        TransactionPurposeResponseDTO transactionPurpose = transactionPurposeService.getById(transactionVoucher.getWithdrawalPurpose());

        log.info("Transaction Purpose: {}", transactionPurpose);

        if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
            transactionVoucher.setSecondApprovalStatus(ApprovalStatus.APPROVED);

            PastelTransReq pastelTransReq = new PastelTransReq();

            if (transactionVoucher.getWithdrawalPurpose() != null) {
                String description = transactionPurpose.getName();
                LocalDate date = transactionVoucher.getApplicationDate();
                log.info("Loacal Date: {}", date);
                pastelTransReq.setTransactionDate(MusoniUtils.formatPastelDates(date));
                pastelTransReq.setDescription(description);
                pastelTransReq.setTransactionType(AppConstants.CASH);
                pastelTransReq.setAmount(transactionVoucher.getAmount().doubleValue());
                pastelTransReq.setCurrency(transactionVoucher.getCurrency());
                pastelTransReq.setReference(transactionVoucher.getReference());
                pastelTransReq.setAPIPassword(apiPassword);
                pastelTransReq.setAPIUsername(apiUsername);
                pastelTransReq.setFromAccount(transactionVoucher.getFromVault().getAccount());
                pastelTransReq.setToAccount(transactionVoucher.getToVault().getAccount());
                pastelTransReq.setExchangeRate(AppConstants.EXCHANGE_RATE);
            }

            // Call the service to post to GL and save the transaction
            TransactionInfo transactionInfo = postGlService.savePostGlFromCMS(pastelTransReq);

            log.info("Pastel Trans Req: {}", pastelTransReq);

            // Check if the transactionInfo has a non-null amount before saving the transaction voucher
            if (transactionInfo != null && transactionInfo.getAmount() != null) {
                transactionVoucher = transactionVoucherRepository.save(transactionVoucher);

                sendEmail(
                        transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                        transactionVoucher.getInitiator().getContactDetail().getEmailAddress(),
                        "Transaction Approved Successfully",
                        "Revise transaction - " + transactionVoucher.getReference() + ". The transactional purpose is " + transactionVoucher.getWithdrawalPurpose() + " ." + transactionVoucher.getFirstApprovalComment(),
                        transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName()
                );
                transactionVoucher.setSecondApprovedAt(LocalDateTime.now());

            } else {
                log.warn("Transaction not saved due to null connection response.");
            }
        }

        if (request.getApprovalStatus().equalsIgnoreCase("REVISE")) {
            transactionVoucher.setSecondApprovalStatus(ApprovalStatus.REVISE);
            transactionVoucher.setSecondApprovalComment(request.getComment());
            sendEmail(
                    transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                    transactionVoucher.getInitiator().getContactDetail().getEmailAddress(),
                    "Make Transaction adjustments",
                    "Make adjustments to the transaction with reference: " + transactionVoucher.getReference() + ". Comment for adjustments: " + transactionVoucher.getWithdrawalPurpose() + " ." + transactionVoucher.getFirstApprovalComment(),
                    transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName()
            );
        }

        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher, transactionPurpose);
    }


    //Get All Transactions
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactions() {
        List<TransactionVoucher> transactions = transactionVoucherRepository.findAllByOrderByCreatedAtDesc();
        return transactionVoucherResponseSerializerList(transactions);
    }

    //Get Transaction By id
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse getTransactionById(Integer id) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        TransactionPurposeResponseDTO transactionPurpose = transactionPurposeService.getById(transactionVoucher.getId());

        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher, transactionPurpose);
    }

    //Get All Transactions By Branch
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByBranch(String branchId) {
        Branches branch = branchRepository.findById(branchId).orElseThrow();
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByBranchOrderByCreatedAtDesc(branch);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By Vault
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByVault(Integer vaultId) {
        Vault vault = new Vault();
        VaultResponseDTO vaultResponse = vaultService.getVault(vaultId);
        BeanUtils.copyProperties(vaultResponse, vault);
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByFromVaultOrToVaultOrderByCreatedAtDesc(vault, vault);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By Initiator
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByInitiator(String initiator) {
        List<TransactionVoucher> transactionVouchers;
        User user = userService.find(initiator).orElseThrow(
                () -> new ResourceNotFoundException("Initiator", "id", initiator)
        );

        if (user.getBranch().equalsIgnoreCase("Head Office")) {

            transactionVouchers = transactionVoucherRepository.
                    findAllByInitiatorOrFirstApprovalStatusAndSecondApprovalStatusOrderByCreatedAtDesc(user, ApprovalStatus.APPROVED, ApprovalStatus.APPROVED);
        } else {
            transactionVouchers = transactionVoucherRepository.findAllByInitiatorOrderByCreatedAtDesc(user);
        }
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By First Approver
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsByFirstApprover(String firstApprover) {
        User user = userService.find(firstApprover).orElseThrow(
                () -> new ResourceNotFoundException("First Approver", "id", firstApprover)
        );
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllByFirstApproverOrderByCreatedAtDesc(user);
        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Get All Transactions By First Approver @Head Office or Second Approver @Branches
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> getAllTransactionsBySecondApprover(String approver) {

        User user = userService.find(approver).orElseThrow();
        List<TransactionVoucher> transactionVouchers = transactionVoucherRepository.findAllBySecondApproverOrderByCreatedAtDesc(user);

        return transactionVoucherResponseSerializerList(transactionVouchers);
    }

    //Initiator Update the Transaction If it is not yet approved or got rejected by the first approver
    @Transactional(value = "transactionManager")
    public TransactionVoucherResponse updateTransactionVoucher(TransactionVoucherUpdateRequest request) {

        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();

        transactionVoucher = transactionVoucherProcessor.processUpdatedTransactionVoucher(transactionVoucher, request);

        TransactionVoucher transactionVoucher1 = transactionVoucherRepository.save(transactionVoucher);

        TransactionPurposeResponseDTO transactionPurpose = transactionPurposeService.getById(transactionVoucher.getId());

        return transactionVoucherProcessor.transactionVoucherResponseSerializer(transactionVoucher1, transactionPurpose);
    }

    //Delete Transaction
    @Transactional(value = "transactionManager")
    public String deleteTransaction(Integer id) {
        TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(id).orElseThrow();

        if (transactionVoucher.getFirstApprovalStatus() == ApprovalStatus.APPROVED || transactionVoucher.getSecondApprovalStatus() == ApprovalStatus.APPROVED) {
            throw new RuntimeException("Transaction already approved by the first approver");
        }

        transactionVoucher.setBranch(null);
        transactionVoucher.setFirstApprover(null);
        transactionVoucher.setSecondApprover(null);
        transactionVoucher.setInitiator(null);
        transactionVoucher.setFromVault(null);
        transactionVoucher.setToVault(null);
        transactionVoucher.setWithdrawalPurpose(null);
        transactionVoucherRepository.delete(transactionVoucher);

        return String.format("Transaction with id %d deleted successfully", id);
    }

    //Get Authorisation by user id
    @Transactional(value = "transactionManager")
    public List<Authorisation> getAuthorisationByUserId(String userId) {
        return authorisationRepository.findAllByUserId(userId);
    }

    //get cms permission by user id
    @Transactional(value = "transactionManager")
    public List<CmsVaultPermission> getCmsVaultPermissionByUserId(String userId) {
        return cmsVaultPermissionRepository.findAllByUserid(userId);
    }

    //get first approvers by branchId
    @Transactional(value = "transactionManager")
    public List<User> findAllByBranchIdAndAuthLevel(String branchId, String authLevel) {
        List<String> authUserIds = new ArrayList<>();
        List<Authorisation> authorisations = authorisationRepository.findAllByBranchIdAndAuthLevel(branchId, authLevel);
        for (Authorisation authorisation :
                authorisations) {
            authUserIds.add(authorisation.getUserId());
        }

        return userService.findAllById(authUserIds);
    }

    //function to serialize the transaction voucher to transaction voucher response
    private List<TransactionVoucherResponse> transactionVoucherResponseSerializerList(List<TransactionVoucher> transactions) {
        List<TransactionVoucherResponse> transactionVoucherResponses = new ArrayList<>();

        for (TransactionVoucher transaction :
                transactions) {

            TransactionPurposeResponseDTO transactionPurpose = transactionPurposeService.getById(transaction.getId());

            TransactionVoucherResponse transactionVoucherResponse = transactionVoucherProcessor.transactionVoucherResponseSerializer(transaction, transactionPurpose);
            transactionVoucherResponses.add(transactionVoucherResponse);
        }

        return transactionVoucherResponses;
    }


    //Function To Send Email
    private void sendEmail(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        String emailText = emailSender.approvalMessage(recipientName, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
    }

    //    Bulk First Approver
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> bulkFirstApproveTransaction(List<ApproverRequest> requests) {
        List<TransactionVoucher> transactionVouchers = new ArrayList<>();

        for (ApproverRequest request: requests){
            TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();
            if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
                transactionVoucher.setFirstApprovalStatus(ApprovalStatus.APPROVED);
            }
            if (request.getApprovalStatus().equalsIgnoreCase("REVISE")) {
                transactionVoucher.setFirstApprovalStatus(ApprovalStatus.REVISE);
                transactionVoucher.setFirstApprovalComment(request.getComment());
            }

            transactionVoucher.setFirstApprovedAt(LocalDateTime.now());
            transactionVouchers.add(transactionVoucher);
        }


        if (!transactionVouchers.isEmpty() && !requests.isEmpty()){
            TransactionVoucher transactionVoucher = transactionVouchers.get(0);
            String referenceNumbers = joinReferenceNumbers(transactionVouchers);

            if (requests.get(0).getApprovalStatus().equalsIgnoreCase("APPROVED")) {
                sendEmail(
                        transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName(),
                        transactionVoucher.getSecondApprover().getContactDetail().getEmailAddress(),
                        "Transaction Approved",
                        "You have a new transaction to approve - " + referenceNumbers + ". The transactional purpose is " + transactionVoucher.getWithdrawalPurpose() + ".",
                        transactionVoucher.getFirstApprover().getFirstName() + " " + transactionVoucher.getFirstApprover().getLastName()
                );
            }

            if (requests.get(0).getApprovalStatus().equalsIgnoreCase("REVISE")) {
                sendEmail(
                        transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                        transactionVoucher.getInitiator().getContactDetail().getEmailAddress(),
                        "Make Transaction adjustments",
                        "Make adjustments to the transaction with reference : " + referenceNumbers + ". Comment for adjustments : " + transactionVoucher.getWithdrawalPurpose() + " ." + transactionVoucher.getFirstApprovalComment(),
                        transactionVoucher.getFirstApprover().getFirstName() + " " + transactionVoucher.getFirstApprover().getLastName()
                );
            }
        }

        List<TransactionVoucher> transactionVouchersList = transactionVoucherRepository.saveAll(transactionVouchers);

        return transactionVoucherProcessor.transactionVouchersResponseSerializer(transactionVouchersList);
    }

    //    Bulk Second Approver
    @Transactional(value = "transactionManager")
    public List<TransactionVoucherResponse> bulkSecondApproveTransaction(List<ApproverRequest> requests) throws JsonProcessingException, ParseException {
        List<TransactionVoucher> transactionVouchers = new ArrayList<>();
        List<PastelTransReq> pastelTransReqs = new ArrayList<>();

        for (ApproverRequest request: requests){
            TransactionVoucher transactionVoucher = transactionVoucherRepository.findById(request.getId()).orElseThrow();


//            Check if the first approval status is approved and the second approval status is pending
            if(transactionVoucher.getFirstApprovalStatus() == ApprovalStatus.APPROVED && transactionVoucher.getSecondApprovalStatus() == ApprovalStatus.PENDING){
                if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
                    transactionVoucher.setSecondApprovalStatus(ApprovalStatus.APPROVED);

                    if (transactionVoucher.getWithdrawalPurpose() != null){


                        PastelTransReq pastelTransReq = new PastelTransReq();


                        TransactionPurpose transactionPurpose = transactionPurposeRepository.getById(transactionVoucher.getWithdrawalPurpose());

                        String description = transactionPurpose.getName();
                        LocalDate date = transactionVoucher.getApplicationDate();
                        log.info("Loacal Date: {}", date);
                        pastelTransReq.setTransactionDate(MusoniUtils.formatPastelDates(date));
                        pastelTransReq.setDescription(description);
                        pastelTransReq.setTransactionType(AppConstants.CASH);
                        pastelTransReq.setAmount(transactionVoucher.getAmount().doubleValue());
                        pastelTransReq.setCurrency(transactionVoucher.getCurrency());
                        pastelTransReq.setReference(transactionVoucher.getReference());
                        pastelTransReq.setAPIPassword(apiPassword);
                        pastelTransReq.setAPIUsername(apiUsername);
                        pastelTransReq.setFromAccount(transactionVoucher.getFromVault().getAccount());
                        pastelTransReq.setToAccount(transactionVoucher.getToVault().getAccount());
                        pastelTransReq.setExchangeRate(AppConstants.EXCHANGE_RATE);

                        pastelTransReqs.add(pastelTransReq);


                    }
                }



                if (request.getApprovalStatus().equalsIgnoreCase("REVISE")) {
                    transactionVoucher.setSecondApprovalStatus(ApprovalStatus.REVISE);
                    transactionVoucher.setSecondApprovalComment(request.getComment());
                }
            }

//            Check if the first approval status is pending and the second approval status is pending
            if(transactionVoucher.getFirstApprovalStatus() == ApprovalStatus.PENDING && transactionVoucher.getSecondApprovalStatus() == ApprovalStatus.PENDING){
                if (request.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
                    transactionVoucher.setFirstApprovalStatus(ApprovalStatus.APPROVED);
                }

                if (request.getApprovalStatus().equalsIgnoreCase("REVISE")) {
                    transactionVoucher.setFirstApprovalStatus(ApprovalStatus.REVISE);
                    transactionVoucher.setFirstApprovalComment(request.getComment());
                }
            }

            transactionVoucher.setSecondApprovedAt(LocalDateTime.now());
            transactionVouchers.add(transactionVoucher);

        }

        if (!transactionVouchers.isEmpty() && !requests.isEmpty()){
            TransactionVoucher transactionVoucher = transactionVouchers.get(0);
            String referenceNumbers = joinReferenceNumbers(transactionVouchers);

            if (requests.get(0).getApprovalStatus().equalsIgnoreCase("APPROVED")) {
                sendEmail(
                        transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                        transactionVoucher.getInitiator().getContactDetail().getEmailAddress(),
                        "Transaction Approved Successfully",
                        "The following transaction with Reference : " + referenceNumbers + " approved successfully. Purpose for transaction is: " + transactionVoucher.getWithdrawalPurpose() + " .",
                        transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName()
                );
            }

            if (requests.get(0).getApprovalStatus().equalsIgnoreCase("REVISE")) {
                sendEmail(
                        transactionVoucher.getInitiator().getFirstName() + " " + transactionVoucher.getInitiator().getLastName(),
                        transactionVoucher.getInitiator().getContactDetail().getEmailAddress(),
                        "Make Transaction adjustments",
                        "Make adjustments to the transaction with reference : " + referenceNumbers + ".",
                        transactionVoucher.getSecondApprover().getFirstName() + " " + transactionVoucher.getSecondApprover().getLastName()
                );
            }



        }

        List<TransactionVoucher> transactionVoucherList = transactionVoucherRepository.saveAll(transactionVouchers);

        postGlService.saveBulkPostGlFromCMS(pastelTransReqs);

        return transactionVoucherProcessor.transactionVouchersResponseSerializer(transactionVoucherList);
    }

    private String joinReferenceNumbers(List<TransactionVoucher> transactions ) {

        List<String> referenceNumbers =  transactions.stream()
                .map(TransactionVoucher::getReference)
                .collect(Collectors.toList());

        int size = referenceNumbers.size();
        if (size == 0) {
            return "";
        } else if (size == 1) {
            return referenceNumbers.get(0);
        } else if (size == 2) {
            return String.join(" and ", referenceNumbers);
        } else {
            String lastTwoNumbers = String.join(" and ", referenceNumbers.subList(size - 2, size));
            List<String> remainingNumbers = referenceNumbers.subList(0, size - 2);
            return String.join(", ", remainingNumbers) + ", " + lastTwoNumbers;
        }
    }
}
