package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.model.cms.Sequence;
import com.untucapital.usuite.utg.repository.cms.SequenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.untucapital.usuite.utg.commons.AppConstants.SEQUENCE_NAME;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SequenceService {



    private final SequenceRepository sequenceRepository;

    @Transactional
    public synchronized long getNextTransactionId() {
        Sequence sequence = sequenceRepository.findById(SEQUENCE_NAME)
                .orElseGet(() -> {
                    Sequence newSequence = new Sequence();
                    newSequence.setName(SEQUENCE_NAME);
                    newSequence.setNextValue(1);
                    return newSequence;
                });

        long nextValue = sequence.getNextValue();
        sequence.setNextValue(nextValue + 1);
        sequenceRepository.save(sequence);
        return nextValue;
    }
}
