package com.untucapital.usuite.utg.controller;//package com.untucapital.usuite.utg.controller;
//
//import com.untucapital.usuite.utg.model.DirectCost;
//import com.untucapital.usuite.utg.model.ProposedCollateralSecurity;
//import com.untucapital.usuite.utg.service.ProposedCollateralSecurityService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/proposed_collateral_security")
//public class ProposedCollateralSecurityController {
//
//    @Autowired
//    ProposedCollateralSecurityService proposedCollateralSecurityService;
//
//    private static final Logger log = LoggerFactory.getLogger(ProposedCollateralSecurityController.class);
//
//    @GetMapping("/get_proposed_collateral_security/{loanId}")
//    public List<ProposedCollateralSecurity> getByLoanId(@PathVariable("loanId") String loanId) {
//        return proposedCollateralSecurityService.getProposedCollateralSecurityLoanId(loanId);
//    }
//
//    @PostMapping("/add_collateral_security")
//    public void add(@RequestBody ProposedCollateralSecurity proposedCollateralSecurity) {
//        proposedCollateralSecurityService.save(proposedCollateralSecurity);
//    }
//
//    @DeleteMapping("/delete_collateral_security/{id}")
//    public void delete(@PathVariable String id) {
//        proposedCollateralSecurityService.delete(id);
//    }
//
//}
