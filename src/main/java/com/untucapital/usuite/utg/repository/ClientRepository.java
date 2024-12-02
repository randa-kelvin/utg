package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientLoan, String> {

    List<ClientLoan> findByUserIdOrderByCreatedAtDesc(String userId);
    List<ClientLoan> findByPhoneNumberOrderByCreatedAtDesc(String phoneNumber);
    List<ClientLoan> findClientLoansByBranchNameOrderByCreatedAtDesc(String branchName);

//    ClientLoan findClientLoanBy (String loanAndFileId);

//    List<ClientLoan> findClientLoansByBranchNameOrderByCreatedAtDesc(String branchName);

    List<ClientLoan> findClientLoansByLoanStatusOrderByCreatedAtDesc(String loanStatus);


    List<ClientLoan> findClientLoansByUserIdOrderByCreatedAtDesc(String userId);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchNameOrderByCreatedAtDesc(String loanStatus, String assignTo, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignedStatusAndBranchNameOrderByCreatedAtDesc(String loanStatus, String assignedStatus, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndBranchNameOrderByCreatedAtDesc(String loanStatus, String branchName, Pageable pageable);

    List<ClientLoan> findClientLoansByBocoSignatureAndBranchNameOrderByCreatedAtDesc(String bocoSignature, String branchName);

    List<ClientLoan> findClientLoansByBmSignatureAndBranchNameOrderByCreatedAtDesc(String bmSignature, String branchName);

    List<ClientLoan> findClientLoansByCaSignatureOrderByCreatedAtDesc(String caSignature);

    List<ClientLoan> findClientLoansByCmSignatureOrderByCreatedAtDesc(String cmSignature);

    List<ClientLoan> findClientLoansByFinSignatureOrderByCreatedAtDesc(String finSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndBranchNameAndPipelineStatusOrderByCreatedAtDesc(String loanStatus, String branchName, String pipelineStatus);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBocoSignatureAndBranchNameOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String bocoSignature, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndBranchNameOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String bmSignature, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCaSignatureOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String CaSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCmSignatureOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String CmSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndFinSignatureOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String FinSignature);

//    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatusNotContainsOrderByCreatedAtDesc(String loanStatus, String assignTo, String branchName, String assessmentStatus);

    List<ClientLoan> findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatusOrderByCreatedAtDesc(String loanStatus, String assignTo, String branchName, String assessmentStatus);

    ClientLoan findByLoanFileId(String loanAndFileId);

    List<ClientLoan> findClientLoanByLoanStatusAndPipelineStatusAndCreditCommitOrderByCreatedAtDesc(String loanStatus, String pipelineStatus, String creditCommit);

    List<ClientLoan> findClientLoanByLoanStatusAndPipelineStatusOrderByCreatedAtDesc(String loanStatus, String pipelineStatus);

    List<ClientLoan> findClientLoanByLoanStatusAndBranchNameAndPipelineStatusAndCreditCommitOrderByCreatedAtDesc(String loanStatus, String branchName, String pipelineStatus, String creditCommit);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBocoSignatureAndPipelineStatusAndBranchNameOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String bocoSignature, String pipelineStatus, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndPipelineStatusAndBranchNameOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String bmSignature, String pipelineStatus, String branchName);

    List<ClientLoan> findClientLoansByBocoSignatureAndCompletelyDoneAndBranchNameOrderByCreatedAtDesc(String bocoSignature, String completelyDone, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndBocoSignatureAndPipelineStatusAndBranchNameOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String bmSignature, String bocoSignature, String pipelineStatus, String branchName);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndCaSignatureOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String bmSignature, String caSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCaSignatureAndCmSignatureOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String caSignature, String cmSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndCmSignatureAndFinSignatureOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String cmSignature, String finSignature);

    List<ClientLoan> findClientLoansByLoanStatusAndProcessLoanStatusAndFinSignatureAndBoardSignatureOrderByCreatedAtDesc(String loanStatus, String processLoanStatus, String finSignature, String boardSignature);

    List<ClientLoan> findClientLoansByBoardSignatureOrderByCreatedAtDesc(String boardSignature);

//    List<ClientLoan> findByUserIdOrderByCreatedAtDesc(String userId);


//    List<ClientLoan> findByUserId(String userId);


    Page<ClientLoan> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = "SELECT cl.* FROM client_loans cl JOIN users u ON cl.user_id = u.id WHERE u.username REGEXP '^[0-9]+$'", nativeQuery = true)
    List<ClientLoan> findClientLoansByUsername();


}
