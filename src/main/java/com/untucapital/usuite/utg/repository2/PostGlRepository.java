package com.untucapital.usuite.utg.repository2;

import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.entity.res.PostGlResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostGlRepository extends JpaRepository<PostGl, BigInteger> {
    List<PostGlResponseDTO> findByTxDate(Date txDate);

    List<PostGlResponseDTO> findByAccountLink(Integer AccountLink);

    @Query("SELECT p FROM PostGl p WHERE p.Reference = :reference")
    List<PostGl> findByReference(@Param("reference") String reference);

//    PostGl findPostGlByReference(String Reference);


    @Query("SELECT SUM(p.Debit) - SUM(p.Credit) " +
            "FROM PostGl p " +
            "WHERE p.accountLink = :accountLink")
    Float findAccountBalanceByAccountLink(@Param("accountLink") Integer accountLink);

}
