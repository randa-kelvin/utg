package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.cms.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Repository
public interface VaultRepository extends JpaRepository<Vault, Integer> {

//    Optional<Vault> findVaultByBranch_BranchNameAndType(String branchName, String type);
    Optional<Vault> findByNameContainingAndType(String branchName, String type);

    Optional<Vault> findByAccount(String account);



    List<Vault> findVaultByBranch_BranchName(String branch);
    List<Vault> findByBranch(Branches branch);

    Optional<Vault> findByType(String type);



    @Query("SELECT SUM(v.currentAmount) " +
            "FROM Vault v " +
            "WHERE v.account = :account")
    BigDecimal findAccountBalanceByAccount(@Param("account") String account);

    @Query("SELECT v.account, SUM(v.currentAmount) " +
            "FROM Vault v " +
            "GROUP BY v.account")
    List<Object[]> findAccountBalances();

    @Query("SELECT SUM(v.currentAmount) " + "FROM Vault v " )
    BigDecimal findTotalAccountBalances();
}
