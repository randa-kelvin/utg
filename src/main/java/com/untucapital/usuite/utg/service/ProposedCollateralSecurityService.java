package com.untucapital.usuite.utg.service;//package com.untucapital.usuite.utg.service;
//
//import com.untucapital.usuite.utg.model.ProposedCollateralSecurity;
//import com.untucapital.usuite.utg.model.SourceOfFunds;
//import com.untucapital.usuite.utg.repository.ProposedCollateralSecurityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Transactional
//@Service
//public class ProposedCollateralSecurityService {
//
//    @Autowired
//    ProposedCollateralSecurityRepository proposedCollateralSecurityRepository;
//
//    public void save(ProposedCollateralSecurity proposedCollateralSecurity){
//        proposedCollateralSecurityRepository.save(proposedCollateralSecurity);
//    }
//
//    public List<ProposedCollateralSecurity> getProposedCollateralSecurityLoanId(String loanId){
//        return proposedCollateralSecurityRepository.findByLoanId();
//    }
//
//    public void delete(String id){
//        proposedCollateralSecurityRepository.deleteById(id);
//    }
//}
