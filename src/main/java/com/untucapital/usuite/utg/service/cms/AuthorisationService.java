package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.dto.cms.req.AuthorisationRequestDTO;
import com.untucapital.usuite.utg.dto.cms.res.AuthorisationResponseDTO;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.Authorisation;
import com.untucapital.usuite.utg.repository.cms.AuthorisationRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;


@Service
@javax.transaction.Transactional
public class AuthorisationService extends AbstractService<Authorisation> {

    private static final Logger log = LoggerFactory.getLogger(AuthorisationService.class);


    private  final AuthorisationRepository authorisationRepository;


    public AuthorisationService(AuthorisationRepository authorisationRepository) {
        this.authorisationRepository = authorisationRepository;
    }

    @Transactional(value = "transactionManager")
    public void  saveAuthorisation(AuthorisationRequestDTO request){

        Authorisation authorisation = new Authorisation();
        BeanUtils.copyProperties(request,authorisation);
        authorisationRepository.save(authorisation);
    }

    @Override
    @Transactional(value = "transactionManager")
    public JpaRepository<Authorisation, String> getRepository(){
        return authorisationRepository;
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<User> getUserByRole(String name) {
        return null;
    }

    @Transactional(value = "transactionManager")
    public void deleteAuthorisation(String id) {
        authorisationRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public AuthorisationResponseDTO getAuthorisationById(String id) {

        AuthorisationResponseDTO authorisationResponse = new AuthorisationResponseDTO();
        Authorisation authorisation= authorisationRepository.findAuthorisationById(id);
        BeanUtils.copyProperties(authorisation, authorisationResponse);

        return authorisationResponse;
    }

    @Transactional(value = "transactionManager")
    public List<AuthorisationResponseDTO> getAuthorisationByBranchId(String branchId){

        List<AuthorisationResponseDTO> response = new ArrayList<AuthorisationResponseDTO>();
        List<Authorisation> authorisationList = authorisationRepository.findAuthorisationByBranchId(branchId);

        for (Authorisation authorisation : authorisationList){

            AuthorisationResponseDTO authorisationResponseDTO = new AuthorisationResponseDTO();
            BeanUtils.copyProperties(authorisation, authorisationResponseDTO);

            response.add(authorisationResponseDTO);

        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<AuthorisationResponseDTO> getAuthorisationByBranchIdAndAuthLevel(String branchId, String authLevel){

        List<AuthorisationResponseDTO> response = new ArrayList<AuthorisationResponseDTO>();
        List<Authorisation> authorisationList= authorisationRepository.findAuthorisationByBranchIdAndAuthLevel(branchId, authLevel);

        for (Authorisation authorisation : authorisationList){

             AuthorisationResponseDTO authorisationResponseDTO = new AuthorisationResponseDTO();
            BeanUtils.copyProperties(authorisation, authorisationResponseDTO);

            response.add(authorisationResponseDTO);

        }

        return response;
    }

}




