package com.untucapital.usuite.utg.treasuryManagement.repository;

import com.untucapital.usuite.utg.treasuryManagement.model.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**************************************
 ** @project utg
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/01/25                   
 **************************************
 */

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Integer> {

}
