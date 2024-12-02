package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.model.CapacityAssessmentMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.CapacityAssessmentMicroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CapacityAssessmentMicroService {
    @Autowired
    private final CapacityAssessmentMicroRepository capacityAssessmentMicroRepository;

    public CapacityAssessmentMicroService(CapacityAssessmentMicroRepository capacityAssessmentMicroRepository) {
        this.capacityAssessmentMicroRepository = capacityAssessmentMicroRepository;
    }

    public void save(CapacityAssessmentMicro capacityAssessmentMicro) {
        capacityAssessmentMicroRepository.save(capacityAssessmentMicro);
    }

    public void deleteById(String id) {
        capacityAssessmentMicroRepository.deleteById(id);
    }

    public List<CapacityAssessmentMicro> findAllByLoanId(String id) {
        return capacityAssessmentMicroRepository.findAllByLoanId(id);
    }
}
