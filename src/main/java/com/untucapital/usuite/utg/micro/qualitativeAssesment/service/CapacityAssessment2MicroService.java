package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.CapacityAssessment2MicroRepository;
import com.untucapital.usuite.utg.model.CapacityAssessment2Micro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapacityAssessment2MicroService {
    @Autowired
    private final CapacityAssessment2MicroRepository capacityAssessment2MicroRepository;

    public CapacityAssessment2MicroService(CapacityAssessment2MicroRepository capacityAssessment2MicroRepository) {
        this.capacityAssessment2MicroRepository = capacityAssessment2MicroRepository;
    }

    public void save(CapacityAssessment2Micro capacityAssessment2Micro) {
        capacityAssessment2MicroRepository.save(capacityAssessment2Micro);
    }

    public void deleteById(String id) {
        capacityAssessment2MicroRepository.deleteById(id);
    }

    public List<CapacityAssessment2Micro> findAllByLoanId(String id) {
        return capacityAssessment2MicroRepository.findAllByLoanId(id);
    }
}
