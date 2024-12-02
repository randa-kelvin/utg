package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.dto.Loan;
import com.untucapital.usuite.utg.dto.LoansPipelineDTO;
import com.untucapital.usuite.utg.model.LoansPipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoansPipelineRepository extends JpaRepository<LoansPipeline, Long> {
    int countByLoanOfficerAndLoanStatus(String loanOfficer, String loanStatus);

    Optional<LoansPipeline> findLoansPipelineById(String id);

    @Query(nativeQuery = true, value = "SELECT * FROM `loanspipeline` ORDER BY `branch_name`, `loan_officer`")
    List<LoansPipeline> findAll();

    @Query(nativeQuery = true, value = "SELECT `branch_name`, `loan_officer`, SUM(CASE WHEN loan_status IN ('Assessment', 'Pending Disbursement', 'Prospect') THEN `sought_loan` ELSE 0 END) AS total_pipeline, SUM(CASE WHEN loan_status = 'Disbursement' THEN `sought_loan` ELSE 0 END) AS total_disbursed FROM loanspipeline GROUP BY `branch_name`,`loan_officer`;")
    List<Object []> getLoTotalPipelineAndDisbursements();

    @Query(nativeQuery = true, value = "SELECT `branch_name`, `loan_officer`, SUM(CASE WHEN loan_status IN ('Assessment', 'Pending Disbursement', 'Prospect') THEN `sought_loan` ELSE 0 END) AS total_pipeline, SUM(CASE WHEN loan_status = 'Disbursement' THEN `sought_loan` ELSE 0 END) AS total_disbursed FROM loanspipeline WHERE `branch_name` = ?1 GROUP BY `loan_officer`")
    List<Object[]> getLoTotalPipelineAndDisbursementsByBranch(String branchName);


    List<LoansPipeline> findLoansPipelineByLoanOfficer(String userId);

    List<LoansPipeline> findByBranchNameOrderByLoanOfficerAsc(String branchName);


    @Query(nativeQuery = true, value = "SELECT IFNULL(`branch_name`, 'Total') AS `branch_name`, " +
            "(SELECT COUNT(*) FROM loanspipeline lp WHERE lp.branch_name = main.branch_name) AS 'Caseloads', " +
            "SUM(CASE WHEN `loan_status` = 'Disbursement' THEN `sought_loan` ELSE 0 END) AS 'Disbursements', " +
            "SUM(CASE WHEN `loan_status` = 'Pending Disbursement' THEN `sought_loan` ELSE 0 END) AS 'Pending Disbursements', " +
            "SUM(CASE WHEN `loan_status` = 'Prospect' THEN `sought_loan` ELSE 0 END) AS 'Prospects', " +
            "SUM(CASE WHEN `loan_status` = 'Assessment' THEN `sought_loan` ELSE 0 END) AS 'Assessments', " +
            "SUM(CASE WHEN `loan_status` != 'Disbursement' THEN `sought_loan` ELSE 0 END) AS 'Total Pipeline', " +
            "SUM(CASE WHEN `repeat_client` = 'New' THEN 1 ELSE 0 END) AS 'New', " +
            "SUM(CASE WHEN `repeat_client` = 'Repeat' THEN 1 ELSE 0 END) AS 'Repeat' " +
            "FROM loanspipeline main " +
            "GROUP BY `branch_name` WITH ROLLUP")
    List<Object []> getLoanPipelineSummary();

    @Query(nativeQuery = true, value = "SELECT \n" +
            "    IFNULL(`branch_name`, 'Total') AS 'Branch Name', \n" +
            "    IFNULL(`loan_officer`, 'Total') AS 'Loan Officer',\n" +
            "    SUM(CASE WHEN `loan_status` = 'Disbursement' THEN 1 ELSE 0 END) AS 'Disbursed', \n" +
            "    SUM(CASE WHEN `loan_status` != 'Disbursement' THEN 1 ELSE 0 END) AS 'Pipeline Cases', \n" +
            "    COUNT(*) AS 'Total',\n" +
            "    AVG(`average_target`) AS 'Average Target',\n" +
            "    (COUNT(*) - AVG(`average_target`)) AS 'Variance'\n" +
            "FROM \n" +
            "    loanspipeline\n" +
            "WHERE \n" +
            "    `branch_name` IS NOT NULL\n" +
            "GROUP BY \n" +
            "    `branch_name`, \n" +
            "    `loan_officer`  \n" +
            "\n" +
            "UNION ALL\n" +
            "\n" +
            "SELECT \n" +
            "    'Total' AS 'Branch Name',\n" +
            "    NULL AS 'Loan Officer',\n" +
            "    SUM(CASE WHEN `loan_status` = 'Disbursement' THEN 1 ELSE 0 END) AS 'Disbursed', \n" +
            "    SUM(CASE WHEN `loan_status` != 'Disbursement' THEN 1 ELSE 0 END) AS 'Pipeline Cases', \n" +
            "    COUNT(*) AS 'Total',\n" +
            "    (SELECT SUM(`average_target`) FROM (SELECT DISTINCT `loan_officer`, `average_target` FROM loanspipeline WHERE `branch_name` IS NOT NULL) AS subquery) AS 'Average Target',\n" +
            "    (COUNT(*) - (SELECT SUM(`average_target`) FROM (SELECT DISTINCT `loan_officer`, `average_target` FROM loanspipeline WHERE `branch_name` IS NOT NULL) AS subquery)) AS 'Variance'\n" +
            "FROM \n" +
            "    loanspipeline\n" +
            "WHERE \n" +
            "    `branch_name` IS NOT NULL;\n")
    List<Object []> getLoanOfficerProductivity();

    @Query(nativeQuery = true, value = "SELECT \n" +
            "    IFNULL(`branch_name`, 'Total') AS 'Branch Name', \n" +
            "    IFNULL(`loan_officer`, 'Total') AS 'Loan Officer',\n" +
            "    SUM(CASE WHEN `loan_status` = 'Disbursement' THEN 1 ELSE 0 END) AS 'Disbursed', \n" +
            "    SUM(CASE WHEN `loan_status` != 'Disbursement' THEN 1 ELSE 0 END) AS 'Pipeline Cases', \n" +
            "    COUNT(*) AS 'Total',\n" +
            "    AVG(`average_target`) AS 'Average Target',\n" +
            "    (COUNT(*) - AVG(`average_target`)) AS 'Variance'\n" +
            "FROM \n" +
            "    loanspipeline\n" +
            "WHERE \n" +
            "    `branch_name` = :branchName \n" +
            "GROUP BY \n" +
            "    `branch_name`, \n" +
            "    `loan_officer`  \n" +
            "\n" +
            "UNION ALL\n" +
            "\n" +
            "SELECT \n" +
            "    'Total' AS 'Branch Name',\n" +
            "    NULL AS 'Loan Officer',\n" +
            "    SUM(CASE WHEN `loan_status` = 'Disbursement' THEN 1 ELSE 0 END) AS 'Disbursed', \n" +
            "    SUM(CASE WHEN `loan_status` != 'Disbursement' THEN 1 ELSE 0 END) AS 'Pipeline Cases', \n" +
            "    COUNT(*) AS 'Total',\n" +
            "    (SELECT SUM(`average_target`) FROM (SELECT DISTINCT `loan_officer`, `average_target` FROM loanspipeline WHERE `branch_name` = :branchNameSubquery) AS subquery) AS 'Average Target',\n" +
            "    (COUNT(*) - (SELECT SUM(`average_target`) FROM (SELECT DISTINCT `loan_officer`, `average_target` FROM loanspipeline WHERE `branch_name` = :branchNameSubquery) AS subquery)) AS 'Variance'\n" +
            "FROM \n" +
            "    loanspipeline\n" +
            "WHERE \n" +
            "    `branch_name` = :branchName;\n")
    List<Object[]> getLoanOfficerProductivityByBranch(@Param("branchName") String branch);





}
