package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.controller.AbstractController;
import com.untucapital.usuite.utg.dto.cms.req.AuthorisationRequestDTO;
import com.untucapital.usuite.utg.dto.cms.res.AuthorisationResponseDTO;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.cms.Authorisation;
import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import com.untucapital.usuite.utg.repository.cms.AuthorisationRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.cms.AuthorisationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "cms/cms_authorisation")
public class AuthorisationController extends AbstractController<Authorisation> {
    @Autowired
    AuthorisationRepository authorisationRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthorisationController.class);

    private final AuthorisationService authorisationService;

    public AuthorisationController(AuthorisationService authorisationService) {
        this.authorisationService = authorisationService;
    }

    //build save branch REST API
    @PostMapping("/addAuthorisation")
    public void add(@RequestBody AuthorisationRequestDTO authorisation) {
        authorisationService.saveAuthorisation(authorisation);
    }

    @GetMapping("getAuthorisationById/{id}")
    public ResponseEntity<AuthorisationResponseDTO> getAuthorisation(@PathVariable("id") String id) {
        AuthorisationResponseDTO authorisation = authorisationService.getAuthorisationById(id);
        if (authorisation != null) {
            return new ResponseEntity<>(authorisation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateauthorisation(@PathVariable String id, @RequestBody Authorisation authorisation) {

        Authorisation updateauthorisation = authorisationRepository.findAuthorisationById(id);

        if (updateauthorisation != null) {

            updateauthorisation.setAuthLevel(authorisation.getAuthLevel());
            updateauthorisation.setBranchId(authorisation.getBranchId());
            updateauthorisation.setUserId(authorisation.getUserId());

            authorisationRepository.save(updateauthorisation);
            return new ResponseEntity<>("Auth successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Auth not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        authorisationService.deleteAuthorisation(id);
    }


    @Override
    protected AbstractService<Authorisation> getService() {
        return authorisationService;
    }

    @GetMapping("branch/{id}")
    public List<AuthorisationResponseDTO> getAuthorisationByBranchId(@PathVariable String id) {
        return authorisationService.getAuthorisationByBranchId(id);
    }

    @GetMapping("authLevel/{branch}/{authLevel}")
    public List<AuthorisationResponseDTO> getAuthorisationByBranchIdAndAuthLevel(@PathVariable String branch, @PathVariable String authLevel) {
        return authorisationService.getAuthorisationByBranchIdAndAuthLevel(branch, authLevel);
    }
}
