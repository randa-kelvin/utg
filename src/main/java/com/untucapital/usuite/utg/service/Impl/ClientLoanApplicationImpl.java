package com.untucapital.usuite.utg.service.Impl;

import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.dto.client.Client;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.PageItems;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.exception.SettlementAccountNotFoundException;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.FormatterUtil;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClientLoanApplicationImpl implements ClientLoanApplication {

    private static final Logger log = LoggerFactory.getLogger(ClientLoanApplication.class);

    private final ClientRepository clientRepository;
    private final UserService userService;

    private final RestClient restClient;


    @Autowired
    public ClientLoanApplicationImpl(ClientRepository clientRepository, UserService userService, RestClient restClient) {
        this.clientRepository = clientRepository;
        this.userService = userService;
        this.restClient = restClient;
    }

    @Override
    public ClientLoan saveClientLoan(ClientLoan clientLoan) throws ParseException {
        log.info("Loan Application Request - {}", FormatterUtil.toJson(clientLoan));

        List<ClientLoan> loans = getClientLoanApplicationsByUserId(clientLoan.getUserId());
        if (loans.isEmpty()) {
            if (clientLoan.getFirstName() == null && clientLoan.getLastName() == null &&
                    clientLoan.getGender() == null && clientLoan.getDateOfBirth() == null &&
                    clientLoan.getPhoneNumber() == null && clientLoan.getBranchName() == null) {

                PageItems settlementAccount = restClient.getSavingsLoanAccountById(clientLoan.getUsername());
                if (settlementAccount.getClientId() == 0) {
                    throw new SettlementAccountNotFoundException("This Settlement Account : " + clientLoan.getUsername() + " does not exist");
                }

                Client musoniClient = restClient.getClientById(String.valueOf(settlementAccount.getClientId()));
                clientLoan.setFirstName(musoniClient.getFirstname());
                clientLoan.setLastName(musoniClient.getLastname());
                clientLoan.setGender(musoniClient.getGender().getName());
                clientLoan.setDateOfBirth(MusoniUtils.formatDate(musoniClient.getDateOfBirth()));
                clientLoan.setPhoneNumber(musoniClient.getMobileNo());
                clientLoan.setBranchName(musoniClient.getOfficeName());
            }

        } else {
            log.info("Loans list: {}", loans);
            boolean loanDuplicate = false;

            for (var loan : loans) {
                if (Objects.equals(loan.getLoanStatus(), "PENDING") &&
                        Objects.equals(loan.getPipelineStatus(), "client_application") &&
                        Objects.equals(loan.getLoanAmount(), clientLoan.getLoanAmount()) &&
                        Objects.equals(loan.getTenure(),clientLoan.getTenure())) {
                    loanDuplicate = true;
                    break;
                }
            }

            if (loanDuplicate) {
                log.error("This is a duplicate application: {}", clientLoan);
                throw new RuntimeException("Failed to save: This is a duplicate application");
            } else {
                log.info("Updated Loan Application - {}", FormatterUtil.toJson(clientLoan));
                return clientRepository.save(clientLoan);
            }
        }

        return null;
    }

    @Override
    public ClientLoan updateClientLoan(ClientLoan clientLoan) {
        return null;
    }


    @Override
    public ClientLoan sendLoanSuccess(String recipientName, String recipientEmail) {
        return null;
    }

    @Override
    public ClientLoan sendMeetingScheduleSuccess(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        return null;
    }

    @Override
    public ClientLoan sendFinalMeetingSuccess(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
        return null;
    }

    @Override
    public List<ClientLoan> getAllClientLoanApplication() {
        return clientRepository.findAll();
    }

    @Override
    public ClientLoan getClientLoanApplicationById(String id) {

        return clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("ClientLoan", "Id", id));
    }

    @Override
    public ClientLoan getClientLoanApplicationByMobile(String phoneNumber) {
        List<ClientLoan> clientLoans = clientRepository.findByPhoneNumberOrderByCreatedAtDesc(phoneNumber);

        log.info("Client loans: {}", clientLoans);

        // Return the first loan in the list if it exists
        return clientLoans.isEmpty() ? null : clientLoans.get(0);
    }



    @Override
    public List<ClientLoan> getClientLoanApplicationsByUserId(String userId) {
        userService.find(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<ClientLoan> clientLoans = clientRepository.findByUserIdOrderByCreatedAtDesc(userId);
        log.info("Client loans: {}", clientLoans);
        return clientLoans;
    }

    @Override
    public ClientLoan updateClientLoan(ClientLoan clientLoan, String id) {
        //check weather loan with id exist or not
        ClientLoan existingClientLoan = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ClientLoan", "Id", id));
        clientLoan.setId(existingClientLoan.getId());
        /*existingClientLoan.setFirstName(clientLoan.getFirstName());
        existingClientLoan.setStatus(clientLoan.getStatus());
*/
        //save existing client to DB
        clientRepository.save(clientLoan);
        return clientLoan;
    }

    @Override
    public void deleteClientLoan(String id) {
        //check whether a client loan exist in the database;
        clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ClientLoan", "Id", id));
        clientRepository.deleteById(id);

    }

    @Override
    public List<ClientLoan> getClientLoanApplicationsByLoanStatus(String loanStatus) {
        userService.find(loanStatus).orElseThrow(() ->
                new ResourceNotFoundException("Loan", "loan status", loanStatus));
        return clientRepository.findClientLoansByLoanStatusOrderByCreatedAtDesc(loanStatus);
    }

    @Override
    public List<ClientLoan> getClientLoanApplicationByBranchName(String branchName) {
        userService.find(branchName).orElseThrow(() ->
                new ResourceNotFoundException("Loan Applications", "BranchName", branchName));

        return clientRepository.findClientLoansByBranchNameOrderByCreatedAtDesc(branchName);
    }

    public List<ClientLoan> getAllClientLoansData() {

//        System.out.println(clientRepository.findAll());
//        List<ClientLoan> clientLoanList = clientRepository.findAll();
//        for (ClientLoan clientLoan : clientLoanList) {
//            System.out.println(clientLoan);
//        }


//        List jsonStr = getAllClientLoanApplication();

        // Create a Gson instance
//        Gson gson = new GsonBuilder().create();
//
//        JSONArray jsonArray = new JSONArray(jsonStr);
//        // Create a list to store the result
//        List<List<Object>> result = new ArrayList<>();
//        System.out.println("\n###################################");
//        System.out.println(jsonStr);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getInt(i);

//
//            // Extract the "createdAt" field
//            String createdAt = jsonObject.getString("createdAt");
//
//            // Extract the date and time components from "createdAt"
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
//            Date date = dateFormat.parse(createdAt);
//            long timestamp = date.getTime();
//
//            // Extract other fields as needed
//            // ...
//
//            // Create a list to store the extracted data for this object
//            List<Object> data = new ArrayList<>();
//
//            // Add the extracted fields to the list
//            data.add(timestamp);
//            // Add other fields as needed
//            // ...
//
//            // Add the list to the result
//            result.add(data);
//        }

        // Print the result
//        System.out.println(jsonStr);

        return clientRepository.findAll();
    }


    public List<ClientLoan> getRecentClientLoans() {
        Pageable pageable = PageRequest.of(0, 50); // Page number 0 (first page), page size 100
        return clientRepository.findAllByOrderByCreatedAtDesc(pageable).getContent();
    }

    @Override
    public List<ClientLoan> getActiveLoans(String userId) {

        List<ClientLoan> clientLoans = clientRepository.findClientLoansByUserIdOrderByCreatedAtDesc(userId);

        List<ClientLoan> clientLoanList = new ArrayList<>();
        for (ClientLoan clientLoan : clientLoans) {
            if (clientLoan.getLoanStatus().equalsIgnoreCase("ACCEPTED") && !(clientLoan.getPipelineStatus().equalsIgnoreCase("cc_final_meeting"))) {
                clientLoanList.add(clientLoan);
            } else if (clientLoan.getLoanStatus().equalsIgnoreCase("PENDING")) {
                clientLoanList.add(clientLoan);
            }
        }

        return clientLoanList;
    }

    @Override
    public List<ClientLoan> getLoansByUsername() {
        List<ClientLoan> clientLoans = clientRepository.findClientLoansByUsername();

        return clientLoans;
    }

}
