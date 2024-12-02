package com.untucapital.usuite.utg.service;


import com.untucapital.usuite.utg.dto.request.CommentsRequestDTO;
import com.untucapital.usuite.utg.dto.response.CommentsResponseDTO;
import com.untucapital.usuite.utg.model.Comments;
import com.untucapital.usuite.utg.repository.CommentsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void  saveComments(CommentsRequestDTO request) {

        Comments comments = new Comments();
        BeanUtils.copyProperties(request, comments);
        commentsRepository.save(comments);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<CommentsResponseDTO> getByLoanId(String loanId) {

        List<CommentsResponseDTO> responseDTOList = new ArrayList<CommentsResponseDTO>();
        List<Comments> commentsList =commentsRepository.findbyLoanId(loanId);

        for(Comments comments:commentsList){
            CommentsResponseDTO comment = new CommentsResponseDTO();
            BeanUtils.copyProperties(comments, comment);

            responseDTOList.add(comment);
        }
        return responseDTOList;
    }
}
