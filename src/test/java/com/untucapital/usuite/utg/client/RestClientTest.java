//package com.untucapital.usuite.utg.client;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.untucapital.usuite.utg.dto.loans.SingleLoan;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//class RestClientTest {
//
//    @Mock
//    private RestTemplateBuilder restTemplateBuilder;
//
//    @InjectMocks
//    private RestClient restClient;
//
//    @Before
//    public void setUp() {
//        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
//        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
//                .thenReturn(ResponseEntity.ok("{" +
//                        "    \"id\": 1,\n" +
//                        "    \"accountNo\": \"000000001\",\n" +
//                        "    \"externalId\": \"14000250\",\n" +
//                        "    \"status\": {\n" +
//                        "        \"id\": 600,\n" +
//                        "        \"code\": \"loanStatusType.closed.obligations.met\",\n" +
//                        "        \"value\": \"Closed (obligations met)\",\n" +
//                        "        \"pendingApproval\": false,\n" +
//                        "        \"waitingForDisbursal\": false,\n" +
//                        "        \"active\": false,\n" +
//                        "        \"restructured\": false,\n" +
//                        "        \"closedObligationsMet\": true,\n" +
//                        "        \"closedWrittenOff\": false,\n" +
//                        "        \"closedRescheduled\": false,\n" +
//                        "        \"closed\": true,\n" +
//                        "        \"overpaid\": false\n" +
//                        "    },\n" +
//                        "    \"clientId\": 12305,\n" +
//                        "    \"clientAccountNo\": \"0013-0103-12305\",\n" +
//                        "    \"clientName\": \"Teecherz Furnishers (Private) Limited .\",\n" +
//                        "    \"clientOfficeId\": 13,\n" +
//                        "    \"loanProductId\": 15,\n" +
//                        "    \"loanProductName\": \"SME Loan\",\n" +
//                        "    \"loanProductDescription\": \"Financing for small to medium enterprises\",\n" +
//                        "    \"isLoanProductLinkedToFloatingRate\": false,\n" +
//                        "    \"loanOfficerId\": 103,\n" +
//                        "    \"loanOfficerName\": \"Portfolio, SSB\",\n" +
//                        "    \"loanType\": {\n" +
//                        "        \"id\": 1,\n" +
//                        "        \"code\": \"accountType.individual\",\n" +
//                        "        \"value\": \"Individual\"\n" +
//                        "    },\n" +
//                        "    \"currency\": {\n" +
//                        "        \"code\": \"USD\",\n" +
//                        "        \"name\": \"US Dollar\",\n" +
//                        "        \"decimalPlaces\": 2,\n" +
//                        "        \"inMultiplesOf\": 1,\n" +
//                        "        \"displaySymbol\": \"$\",\n" +
//                        "        \"nameCode\": \"currency.USD\",\n" +
//                        "        \"displayLabel\": \"US Dollar ($)\"\n" +
//                        "    },\n" +
//                        "    \"principal\": 50000.000000,\n" +
//                        "    \"approvedPrincipal\": 50000.000000,\n" +
//                        "    \"proposedPrincipal\": 50000.000000,\n" +
//                        "    \"termFrequency\": 1,\n" +
//                        "    \"termPeriodFrequencyType\": {\n" +
//                        "        \"id\": 2,\n" +
//                        "        \"code\": \"termFrequency.periodFrequencyType.months\",\n" +
//                        "        \"value\": \"Months\"\n" +
//                        "    },\n" +
//                        "    \"numberOfRepayments\": 1,\n" +
//                        "    \"repaymentEvery\": 1,\n" +
//                        "    \"repaymentFrequencyType\": {\n" +
//                        "        \"id\": 2,\n" +
//                        "        \"code\": \"repaymentFrequency.periodFrequencyType.months\",\n" +
//                        "        \"value\": \"Months\"\n" +
//                        "    },\n" +
//                        "    \"interestRatePerPeriod\": 3.750000,\n" +
//                        "    \"interestRateFrequencyType\": {\n" +
//                        "        \"id\": 2,\n" +
//                        "        \"code\": \"interestRateFrequency.periodFrequencyType.months\",\n" +
//                        "        \"value\": \"Per month\"\n" +
//                        "    },\n" +
//                        "    \"annualInterestRate\": 45.000000,\n" +
//                        "    \"isFloatingInterestRate\": false,\n" +
//                        "    \"amortizationType\": {\n" +
//                        "        \"id\": 1,\n" +
//                        "        \"code\": \"amortizationType.equal.installments\",\n" +
//                        "        \"value\": \"Equal installments\"\n" +
//                        "    },\n" +
//                        "    \"interestType\": {\n" +
//                        "        \"id\": 0,\n" +
//                        "        \"code\": \"interestType.declining.balance\",\n" +
//                        "        \"value\": \"Declining Balance\"\n" +
//                        "    },\n" +
//                        "    \"interestCalculationPeriodType\": {\n" +
//                        "        \"id\": 1,\n" +
//                        "        \"code\": \"interestCalculationPeriodType.same.as.repayment.period\",\n" +
//                        "        \"value\": \"Same as repayment period\"\n" +
//                        "    },\n" +
//                        "    \"allowPartialPeriodInterestCalculation\": false,\n" +
//                        "    \"transactionProcessingStrategyId\": 1,\n" +
//                        "    \"transactionProcessingStrategyName\": \"Penalties, Fees, Interest, Principal order\",\n" +
//                        "    \"expectedFirstRepaymentOnDate\": [2017, 8, 16],\n" +
//                        "    \"syncDisbursementWithMeeting\": false,\n" +
//                        "    \"timeline\": {\n" +
//                        "        \"submittedOnDate\": [2017, 6, 28],\n" +
//                        "        \"submittedByUsername\": \"musoni\",\n" +
//                        "        \"submittedByFirstname\": \"App\",\n" +
//                        "        \"submittedByLastname\": \"Administrator\",\n" +
//                        "        \"approvedOnDate\": [2017, 6, 28],\n" +
//                        "        \"approvedByUsername\": \"musoni\",\n" +
//                        "        \"approvedByFirstname\": \"App\",\n" +
//                        "        \"approvedByLastname\": \"Administrator\",\n" +
//                        "        \"expectedDisbursementDate\": [2017, 6, 28],\n" +
//                        "        \"actualDisbursementDate\": [2017, 6, 28],\n" +
//                        "        \"disbursedByUsername\": \"musoni\",\n" +
//                        "        \"disbursedByFirstname\": \"App\",\n" +
//                        "        \"disbursedByLastname\": \"Administrator\",\n" +
//                        "        \"closedOnDate\": [2017, 8, 16],\n" +
//                        "        \"expectedMaturityDate\": [2017, 8, 16]\n" +
//                        "    },\n" +
//                        "    \"summary\": {\n" +
//                        "        \"currency\": {\n" +
//                        "            \"code\": \"USD\",\n" +
//                        "            \"name\": \"US Dollar\",\n" +
//                        "            \"decimalPlaces\": 2,\n" +
//                        "            \"inMultiplesOf\": 1,\n" +
//                        "            \"displaySymbol\": \"$\",\n" +
//                        "            \"nameCode\": \"currency.USD\",\n" +
//                        "            \"displayLabel\": \"US Dollar ($)\"\n" +
//                        "        },\n" +
//                        "        \"principalDisbursed\": 50000.000000,\n" +
//                        "        \"principalPaid\": 50000.000000,\n" +
//                        "        \"principalWrittenOff\": 0.000000,\n" +
//                        "        \"principalOutstanding\": 0.000000,\n" +
//                        "        \"principalOverdue\": 0,\n" +
//                        "        \"interestCharged\": 1875.000000,\n" +
//                        "        \"interestPaid\": 1875.000000,\n" +
//                        "        \"interestWaived\": 0.000000,\n" +
//                        "        \"interestWrittenOff\": 0.000000,\n" +
//                        "        \"interestOutstanding\": 0.000000,\n" +
//                        "        \"interestOverdue\": 0,\n" +
//                        "        \"feeChargesCharged\": 0.000000,\n" +
//                        "        \"feeChargesDueAtDisbursementCharged\": 0.000000,\n" +
//                        "        \"feeChargesPaid\": 0.000000,\n" +
//                        "        \"feeChargesWaived\": 0.000000,\n" +
//                        "        \"feeChargesWrittenOff\": 0.000000,\n" +
//                        "        \"feeChargesOutstanding\": 0.000000,\n" +
//                        "        \"feeChargesOverdue\": 0,\n" +
//                        "        \"penaltyChargesCharged\": 0.000000,\n" +
//                        "        \"penaltyChargesPaid\": 0.000000,\n" +
//                        "        \"penaltyChargesWaived\": 0.000000,\n" +
//                        "        \"penaltyChargesWrittenOff\": 0.000000,\n" +
//                        "        \"penaltyChargesOutstanding\": 0.000000,\n" +
//                        "        \"penaltyChargesOverdue\": 0,\n" +
//                        "        \"totalExpectedRepayment\": 51875.000000,\n" +
//                        "        \"totalRepayment\": 51875.000000,\n" +
//                        "        \"totalExpectedCostOfLoan\": 1875.000000,\n" +
//                        "        \"totalCostOfLoan\": 1875.000000,\n" +
//                        "        \"totalWaived\": 0.000000,\n" +
//                        "        \"totalWrittenOff\": 0.000000,\n" +
//                        "        \"totalRecovered\": 0,\n" +
//                        "        \"totalOutstanding\": 0.000000,\n" +
//                        "        \"totalOverdue\": 0\n" +
//                        "    },\n" +
//                        "    \"feeChargesAtDisbursementCharged\": 0.000000,\n" +
//                        "    \"loanCounter\": 19,\n" +
//                        "    \"loanProductCounter\": 4,\n" +
//                        "    \"multiDisburseLoan\": false,\n" +
//                        "    \"canDefineInstallmentAmount\": false,\n" +
//                        "    \"canDisburse\": false,\n" +
//                        "    \"canUseForTopup\": true,\n" +
//                        "    \"isTopup\": false,\n" +
//                        "    \"closureLoanId\": 0,\n" +
//                        "    \"inArrears\": false,\n" +
//                        "    \"isNPA\": false,\n" +
//                        "    \"overdueCharges\": [],\n" +
//                        "    \"daysInMonthType\": {\n" +
//                        "        \"id\": 30,\n" +
//                        "        \"code\": \"DaysInMonthType.days360\",\n" +
//                        "        \"value\": \"30 Days\"\n" +
//                        "    },\n" +
//                        "    \"daysInYearType\": {\n" +
//                        "        \"id\": 360,\n" +
//                        "        \"code\": \"DaysInYearType.days360\",\n" +
//                        "        \"value\": \"360 Days\"\n" +
//                        "    },\n" +
//                        "    \"isInterestRecalculationEnabled\": false,\n" +
//                        "    \"createStandingInstructionAtDisbursement\": false,\n" +
//                        "    \"paidInAdvance\": {\n" +
//                        "        \"paidInAdvance\": 0\n" +
//                        "    },\n" +
//                        "    \"isVariableInstallmentsAllowed\": false,\n" +
//                        "    \"minimumGap\": 0,\n" +
//                        "    \"maximumGap\": 0,\n" +
//                        "    \"internalRateOfReturn\": 45.0000000000000000000,\n" +
//                        "    \"effectiveInterestRate\": 0E-19,\n" +
//                        "    \"repayPrincipalEvery\": 1,\n" +
//                        "    \"repayInterestEvery\": 1,\n" +
//                        "    \"isInDuplum\": false,\n" +
//                        "    \"originChannel\": \"NONE\",\n" +
//                        "    \"officeId\": 13,\n" +
//                        "    \"officeName\": \"SSB Branch\",\n" +
//                        "    \"stopApplyingPenalty\": false,\n" +
//                        "    \"recalculateInterestOnArrears\": false,\n" +
//                        "    \"syncInterestChargedFromDateWithActualDisbursementDate\": false,\n" +
//                        "    \"spreadGracePeriodInterestOverRemainingPeriods\": false\n" +
//                        "}"));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        restClient = new RestClient(restTemplateBuilder, restTemplate, objectMapper, restTemplate1);
//    }
//
//    @Test
//    void httpHeaders() {
//    }
//
//    @Test
//    void getLoans() {
//    }
//
//    @Test
//    void getTransactions() {
//    }
//
//    @Test
//    void getAllUsers() {
//    }
//
//    @Test
//    void getRepaymentSchedule() {
//    }
//
//    @Test
//    void getClientById() {
//    }
//
//    @Test
//    void getLoanByTimeStamp() {
//    }
//
//    @Test
//    void getLoansByDisbursementDate() {
//    }
//
//    @Test
//    void getLoanId() {
//        String loanId = "1";
//        SingleLoan loan = restClient.getLoanId(loanId);
//
//        // Assert that the loan is not null and has the expected id
//        Assertions.assertEquals(1, loan.getId());
//    }
//
//    @Test
//    void retrieveAllLoans() {
//    }
//
//    @Test
//    void getAllLoans() {
//    }
//
//    @Test
//    void getTimestampedLoanAcc() {
//    }
//
//    @Test
//    void getByLoanIdAndTransactionId() {
//    }
//}