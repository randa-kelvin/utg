package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.MeetingsRequestDTO;
import com.untucapital.usuite.utg.dto.response.MeetingsResponseDTO;
import com.untucapital.usuite.utg.model.Meetings;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.MeetingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MeetingsService extends AbstractService<Meetings>{

    private static final Logger log = LoggerFactory.getLogger(MeetingsService.class);

    private final MeetingsRepository meetingsRepository;


    public MeetingsService(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveMeetings(MeetingsRequestDTO request) {

        Meetings meetings = new Meetings();
        BeanUtils.copyProperties(request,meetings);
        meetingsRepository.save(meetings);
    }

    //Get collateral by Id
    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public MeetingsResponseDTO getCollateralById( String id) {

        MeetingsResponseDTO result = new MeetingsResponseDTO();
        Meetings meetings =meetingsRepository.findMeetingsById(id);
        BeanUtils.copyProperties(meetings, result);

        return result;
    }

    //Get collateral by loanId
    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<MeetingsResponseDTO> getCollateralByLoanId(String loanId) {

        List<MeetingsResponseDTO> responseDTOs = new ArrayList<>();
        List<Meetings> meetingsList = meetingsRepository.findMeetingsByLoanId(loanId);

        for (Meetings meetings : meetingsList){

            MeetingsResponseDTO report = new MeetingsResponseDTO();
            BeanUtils.copyProperties(meetings, report);

            responseDTOs.add(report);
        }

        return responseDTOs;
    }


    @Override
    protected JpaRepository<Meetings, String> getRepository() {
        return meetingsRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }



}
