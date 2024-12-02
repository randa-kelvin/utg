package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.ClientCharacterAssessmentRepository;
import com.untucapital.usuite.utg.model.ClientCharacterAssessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCharacterAssessmentService {
    @Autowired
    private final ClientCharacterAssessmentRepository clientCharacterAssessmentRepository;

    public ClientCharacterAssessmentService(ClientCharacterAssessmentRepository clientCharacterAssessmentRepository) {
        this.clientCharacterAssessmentRepository = clientCharacterAssessmentRepository;
    }

    public void save(ClientCharacterAssessment clientCharacterAssessment) {
        clientCharacterAssessmentRepository.save(clientCharacterAssessment);
    }

    public void deleteById(String id) {
        clientCharacterAssessmentRepository.deleteById(id);
    }

    public List<ClientCharacterAssessment> findAllByLoanId(String id) {
        return clientCharacterAssessmentRepository.findAllByLoanId(id);
    }
}
