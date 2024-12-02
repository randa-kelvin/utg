package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientLoan;
import org.springframework.stereotype.Service;

import static com.untucapital.usuite.utg.client.RestClient.generateUniqueId;

@Service
public class ClientLoanService {

    public ClientLoan processClientLoan(ClientLoan clientLoan) {
        if (clientLoan.getPipelineStatus() == null || clientLoan.getPipelineStatus().trim().isEmpty()) {
            clientLoan.setPipelineStatus("client_application");
        }

        if (clientLoan.getBranchName() == null || clientLoan.getBranchName().trim().isEmpty()) {
            String assignedBranch = assignBranchBasedOnCity(clientLoan.getCity());
            clientLoan.setBranchName(assignedBranch);
        }

        if (clientLoan.getAssignTo() == null || clientLoan.getAssignTo().trim().isEmpty()) {
            clientLoan.setAssignTo("Unassigned");
        }

        if (clientLoan.getLoanFileId() == null || clientLoan.getLoanFileId().trim().isEmpty()) {
            clientLoan.setLoanFileId(generateUniqueId("4587"));
        }

        // Assign default loan status
        if (clientLoan.getLoanStatus() == null || clientLoan.getLoanStatus().trim().isEmpty()) {
            clientLoan.setLoanStatus("PENDING");
        }

        // Assign default process loan status
        if (clientLoan.getProcessLoanStatus() == null || clientLoan.getProcessLoanStatus().trim().isEmpty()) {
            clientLoan.setProcessLoanStatus("uncomplete");
        }

        // Assign default signatures
        if (clientLoan.getBocoSignature() == null || clientLoan.getBocoSignature().trim().isEmpty()) {
            clientLoan.setBocoSignature("Unsigned");
        }

        if (clientLoan.getBmSignature() == null || clientLoan.getBmSignature().trim().isEmpty()) {
            clientLoan.setBmSignature("Unsigned");
        }

        if (clientLoan.getCaSignature() == null || clientLoan.getCaSignature().trim().isEmpty()) {
            clientLoan.setCaSignature("Unsigned");
        }

        if (clientLoan.getCmSignature() == null || clientLoan.getCmSignature().trim().isEmpty()) {
            clientLoan.setCmSignature("Unsigned");
        }

        if (clientLoan.getFinSignature() == null || clientLoan.getFinSignature().trim().isEmpty()) {
            clientLoan.setFinSignature("Unsigned");
        }

        return clientLoan;
    }

    private String assignBranchBasedOnCity(String city) {
        // Branch assignment based on proximity
        switch (city.toLowerCase()) {
            case "harare":
            case "marondera":
            case "ruwa":
            case "chitungwiza":
            case "norton":
                return "Harare";

            case "bulawayo":
            case "gwanda":
            case "plumtree":
                return "Bulawayo";

            case "gweru":
            case "kwekwe":
            case "redcliff":
            case "shurugwi":
                return "Gweru";

            case "gokwe":
            case "lupane":
            case "binga":
            case "chegutu":
            case "kadoma":
                return "Gokwe";

            default:
                return "Harare"; // Default branch if the city is not listed
        }
    }
}

