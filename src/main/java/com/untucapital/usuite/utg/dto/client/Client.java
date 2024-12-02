package com.untucapital.usuite.utg.dto.client;

import com.untucapital.usuite.utg.dto.Timeline;
import com.untucapital.usuite.utg.model.transactions.AuditData;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Client {
    private int id;
    private String accountNo;
    private String externalId;
    private Status status;
    private SubStatus subStatus;
    private boolean active;
    private List<Integer> activationDate;
    private String firstname;
    private String lastname;
    private String displayName;
    private String mobileNo;
    private int[] dateOfBirth;
    private RejectionReason rejectionReason;
    private Gender gender;
    private ClientType clientType;
    private ClientClassification clientClassification;
    private int officeId;
    private String officeName;
    private int staffId;
    private String staffName;
    private Timeline timeline;
    private List<Object> groups;
    private List<Object> clientUsers;
    private ClientNonPersonDetails clientNonPersonDetails;
    private boolean isStaff;
    private String countryIsoCode;
    private List<Object> tags;
    private List<ClientLoanOfficerAssignmentHistoryData> clientLoanOfficerAssignmentHistoryData;
    private String originChannel;
    private AuditData auditData;
}
