package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientLoan;

import java.text.ParseException;
import java.util.List;

public interface ClientLoanApplication {

    ClientLoan saveClientLoan(ClientLoan clientLoan) throws ParseException;

    ClientLoan updateClientLoan(ClientLoan clientLoan);

    ClientLoan sendLoanSuccess(String recipientName, String recipientEmail);

    ClientLoan sendMeetingScheduleSuccess(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName);

    ClientLoan sendFinalMeetingSuccess(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName);

    List<ClientLoan> getAllClientLoanApplication();

    ClientLoan getClientLoanApplicationById(String id);

    ClientLoan getClientLoanApplicationByMobile(String phoneNumber);

    List<ClientLoan> getClientLoanApplicationsByUserId(String userId);

    ClientLoan updateClientLoan(ClientLoan clientLoan, String id);

    void deleteClientLoan(String id);

    List<ClientLoan> getClientLoanApplicationsByLoanStatus(String loanStatus);

    List<ClientLoan> getClientLoanApplicationByBranchName(String branchName);

    List<ClientLoan> getAllClientLoansData();

    List<ClientLoan> getRecentClientLoans();

    List<ClientLoan> getActiveLoans(String userId);

    List<ClientLoan> getLoansByUsername();

}
