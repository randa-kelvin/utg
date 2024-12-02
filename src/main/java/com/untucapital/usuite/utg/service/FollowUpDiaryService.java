package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.FollowUpDiaryRequestDTO;
import com.untucapital.usuite.utg.dto.response.FollowUpDiaryResponseDTO;
import com.untucapital.usuite.utg.model.FollowUpDiary;
import com.untucapital.usuite.utg.repository.FollowUpDiaryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowUpDiaryService {

    private final FollowUpDiaryRepository followUpDiaryRepository;

    @Autowired
    public FollowUpDiaryService(FollowUpDiaryRepository followUpDiaryRepository) {
        this.followUpDiaryRepository = followUpDiaryRepository;
    }

    @Transactional(value = "transactionManager")
    public FollowUpDiaryResponseDTO createFollowUpDiary(FollowUpDiaryRequestDTO request) {

        FollowUpDiaryResponseDTO response = new FollowUpDiaryResponseDTO();
        FollowUpDiary followUpDiary = new FollowUpDiary();
        BeanUtils.copyProperties(request, followUpDiary);
        FollowUpDiary follow = followUpDiaryRepository.save(followUpDiary);
        BeanUtils.copyProperties(follow,response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public FollowUpDiaryResponseDTO updateFollowUpDiary(String id, FollowUpDiaryRequestDTO request) {

        FollowUpDiaryResponseDTO response = new FollowUpDiaryResponseDTO();
        Optional<FollowUpDiary> optionalFollowUpDiary = followUpDiaryRepository.findById(id);
        if (optionalFollowUpDiary.isPresent()) {
            FollowUpDiary existingFollowUpDiary = optionalFollowUpDiary.get();
            existingFollowUpDiary.setClientName(request.getClientName());
            existingFollowUpDiary.setClientContactNumber(request.getClientContactNumber());
            existingFollowUpDiary.setClientContactEmail(request.getClientContactEmail());
            existingFollowUpDiary.setClientBusinessAddress(request.getClientBusinessAddress());
            existingFollowUpDiary.setFollowUpComments(request.getFollowUpComments());
            existingFollowUpDiary.setContacted(request.getContacted());
            existingFollowUpDiary.setFollowUpStatus(request.getFollowUpStatus());
            existingFollowUpDiary.setLoanOfficerName(request.getLoanOfficerName());

            FollowUpDiary followUpDiary= followUpDiaryRepository.save(existingFollowUpDiary);
            BeanUtils.copyProperties(followUpDiary,response);

            return response;
        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public FollowUpDiaryResponseDTO getFollowUpDiaryById(String id) {

        FollowUpDiaryResponseDTO response = new FollowUpDiaryResponseDTO();
        Optional<FollowUpDiary> optionalFollowUpDiary = followUpDiaryRepository.findById(id);

        if(optionalFollowUpDiary.isPresent()) {
            FollowUpDiary followUpDiary= optionalFollowUpDiary.get();
            BeanUtils.copyProperties(followUpDiary, response);
        }else {
            return null;
        }
        return response;
    }

    @Transactional(value = "transactionManager")
    public List<FollowUpDiaryResponseDTO> getFollowUpDiaryByClientid(String clientID) {
        List<FollowUpDiary> optionalFollowUpDiary = followUpDiaryRepository.findFollowUpDiaryByClientID(clientID);
        List<FollowUpDiaryResponseDTO> response = new ArrayList<FollowUpDiaryResponseDTO>();

        for(FollowUpDiary followUpDiary: optionalFollowUpDiary){

            FollowUpDiaryResponseDTO responseDTO = new FollowUpDiaryResponseDTO();
            BeanUtils.copyProperties(followUpDiary, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<FollowUpDiaryResponseDTO> getAllFollowUpDiaries() {

        List<FollowUpDiaryResponseDTO> response = new ArrayList<FollowUpDiaryResponseDTO>();
        List<FollowUpDiary> followUpDiaryList= followUpDiaryRepository.findAll();

        for(FollowUpDiary followUpDiary: followUpDiaryList){

            FollowUpDiaryResponseDTO responseDTO = new FollowUpDiaryResponseDTO();
            BeanUtils.copyProperties(followUpDiary, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteFollowUpDiary(String id) {
        followUpDiaryRepository.deleteById(id);
    }
}
