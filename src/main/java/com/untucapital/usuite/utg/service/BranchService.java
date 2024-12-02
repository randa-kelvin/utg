package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.BranchesRequestDTO;
import com.untucapital.usuite.utg.dto.response.BranchesResponseDTO;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@javax.transaction.Transactional
@Service
public class BranchService extends AbstractService<Branches> {

    private static final Logger log = LoggerFactory.getLogger(BranchService.class);

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Transactional(value = "transactionManager")
    public void saveBranches(BranchesRequestDTO requestDTO) {
        Branches branches = new Branches();
        BeanUtils.copyProperties(requestDTO, branches);
        branchRepository.save(branches);
    }

    @Override
    @Transactional(value = "transactionManager")
    public JpaRepository<Branches, String> getRepository() {


        return branchRepository;
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<User> getUserByRole(String name) {
        return null;
    }

    @Transactional(value = "transactionManager")
    public void deleteBranch(String id) {
        branchRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<BranchesResponseDTO> getAllBranches() {

        List<BranchesResponseDTO> responseDTOList = new ArrayList<BranchesResponseDTO>();
        List<Branches> branchesList = branchRepository.findAll();

        for (Branches branches : branchesList) {
            BranchesResponseDTO branchesResponseDTO = new BranchesResponseDTO();
            BeanUtils.copyProperties(branches, branchesResponseDTO);

            responseDTOList.add(branchesResponseDTO);
        }

        return responseDTOList;
    }

    @Transactional(value = "transactionManager")
    public BranchesResponseDTO getBranchesById(String id) {
        BranchesResponseDTO responseDTO = new BranchesResponseDTO();

        Branches branches = branchRepository.findBranchesById(id);
        BeanUtils.copyProperties(branches, responseDTO);

        return responseDTO;

    }

    @Transactional(value = "transactionManager")
    public BranchesResponseDTO getBranchByName(String name) {

        BranchesResponseDTO responseDTO = new BranchesResponseDTO();

        Branches branches = branchRepository.findBranchesByBranchName(name);
        BeanUtils.copyProperties(branches, responseDTO);

        return responseDTO;
    }
}
