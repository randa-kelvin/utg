package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.exception.UntuSuiteException;
import com.untucapital.usuite.utg.integration.fcbintservice.FCBIntegrationService;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.fcb.Response;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.repository.FCBResponseRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCheckService {

    private static final Logger log = LoggerFactory.getLogger(CreditCheckService.class);

    private final FCBIntegrationService fcbIntegrationService;
    private final ClientRepository clientRepository;
    private final FCBResponseRepository fcbResponseRepository;


    public CreditCheckService(FCBIntegrationService fcbIntegrationService, ClientRepository clientRepository, FCBResponseRepository fcbResponseRepository, ClientLoanApplication clientLoanApplication) {
        this.fcbIntegrationService = fcbIntegrationService;
        this.clientRepository = clientRepository;
        this.fcbResponseRepository = fcbResponseRepository;
    }

    @Transactional(value = "transactionManager")
    public ClientLoan fetchFCBCreditStatusById(String id) throws ParseException {
        Optional<ClientLoan> clientLoan = clientRepository.findById(id); // Use your existing method to fetch by loanId
        if (clientLoan == null) {
            throw new ResourceNotFoundException("ClientLoan", "Id", id);
        }
        if (clientLoan.isPresent()) {
            return fetchFCBCreditStatus(clientLoan.get()); // Update clientLoan with FCB credit status
//        clientLoanApplication.saveClientLoan(clientLoan); // Assuming you have a method to save clientLoan back

        }
        return null;

    }

    @Transactional(value = "transactionManager")
    public ClientLoan fetchFCBCreditStatus(ClientLoan clientLoan) {
        log.info("Fetching FCB Credit Status for Client: {}, ID:{}", clientLoan.getFirstName() + clientLoan.getLastName(), clientLoan.getIdNumber());
        Response creditResponse = null;
        //clientLoan.setLoanStatus("PENDING");
    try {
         creditResponse = fcbIntegrationService.performSearch(clientLoan)
                .orElseThrow(() -> new UntuSuiteException("Credit check failed for the Loan Application"));
    }catch (Exception e){
        log.info("failed to get fcb infomation: {}", e.getMessage());
    }
    log.info("FCB Response:{}", creditResponse);
        Response savedResponse = fcbResponseRepository.save(creditResponse);


        String creditStatus = "UNKNOWN";
        Integer creditScore = 0;
        if (creditResponse.getReport() == null){

        } else {
            creditStatus = creditResponse.getReport().get(0).getStatus();
            creditScore = creditResponse.getReport().get(0).getScore();
        }

        clientLoan.setFcbResponse(savedResponse);
        clientLoan.setFcbStatus(creditStatus);
        clientLoan.setFcbScore(creditScore);

        //if (creditStatus.equals("GREEN") || creditStatus.equals("GOOD")) {
        //  clientLoan.setLoanStatus("ACCEPTED");
        //} else if (creditStatus.equals("FAIR") || creditStatus.equals("ADVERSE") || creditStatus.equals("PEP")) {
        //clientLoan.setLoanStatus("REJECTED");
        // }
        return clientLoan;
    }

    //    @Scheduled(initialDelayString = "${fixed-delay.ms}", fixedDelayString = "${fixed-delay.ms}")
    @Transactional(value = "transactionManager")
    public void pollCreditChecks() {
        log.debug("Polling Inconclusive Credit Checks");
        long startTime = System.currentTimeMillis();

        final List<ClientLoan> clientLoans = clientRepository.findAll();

        List<ClientLoan> pendingClientCreditChecks = clientLoans
                .stream()
                .filter(cl -> "PENDING".equals(cl.getLoanStatus()))
                .collect(Collectors.toList());

        pendingClientCreditChecks.forEach(this::fetchFCBCreditStatus);

        long finishTime = System.currentTimeMillis();
        if (!pendingClientCreditChecks.isEmpty())
            log.debug("Completed Poll of Inconclusive Checks: {}. Poll took {}ms", LocalDateTime.now(), finishTime - startTime);
    }
}
