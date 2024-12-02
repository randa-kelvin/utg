package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.model.Targets;
import com.untucapital.usuite.utg.repository.TargetsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class TargetsService {

    @Autowired
    private TargetsRepository tagetsRepository;

    public List<Targets> getAllTargets() {
        return tagetsRepository.findAll();
    }

//    public List<Targets> getTargetById(String id) {
//        return (List<Targets>) tagetsRepository.findTargetsById(id);
//
//    }

    public Targets getTargetById(String id) {
        return tagetsRepository.findTargetsById(id);
    }


    public Targets getTarget(String id) {
        return tagetsRepository.getById(id);
    }

    public void saveTargets(Targets targets) {
        tagetsRepository.save(targets);
    }

    public void deleteTargets(String id) {
        tagetsRepository.deleteById(id);
    }
}

