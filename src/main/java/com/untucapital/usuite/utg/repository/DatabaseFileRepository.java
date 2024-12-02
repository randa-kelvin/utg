package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {

    List<DatabaseFile> findByUserId(String userId);

    List<DatabaseFile> findByUserIdAndFileDescriptionNotContainsAndFileDescriptionNotContainsAndFileDescriptionNotContains(String userId, String appraisal, String selfie, String nationalId);

    List<DatabaseFile> findByUserIdAndLoanIdAndFileDescriptionNotContainsAndFileDescriptionNotContains(String userId, String loanId, String appraisal, String assessmentFile);

    List<DatabaseFile> findByUserIdAndLoanIdAndFileDescription (String userId, String loanId, String fileDescription);

    DatabaseFile getFileById(String fileID);

//    DatabaseFile findByFileId(String id);

}