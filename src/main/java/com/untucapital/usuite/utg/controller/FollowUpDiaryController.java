package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.FollowUpDiaryRequestDTO;
import com.untucapital.usuite.utg.dto.response.FollowUpDiaryResponseDTO;
import com.untucapital.usuite.utg.service.FollowUpDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/followUpDiary")
public class FollowUpDiaryController {

    private final FollowUpDiaryService followUpDiaryService;

    @Autowired
    public FollowUpDiaryController(FollowUpDiaryService followUpDiaryService) {
        this.followUpDiaryService = followUpDiaryService;
    }

    @PostMapping
    public ResponseEntity<FollowUpDiaryResponseDTO> createFollowUpDiary(@RequestBody FollowUpDiaryRequestDTO followUpDiary) {
        FollowUpDiaryResponseDTO createdFollowUpDiary = followUpDiaryService.createFollowUpDiary(followUpDiary);
        return new ResponseEntity<>(createdFollowUpDiary, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowUpDiaryResponseDTO> updateFollowUpDiary(
            @PathVariable("id") String id, @RequestBody FollowUpDiaryRequestDTO followUpDiary) {
        FollowUpDiaryResponseDTO updatedFollowUpDiary = followUpDiaryService.updateFollowUpDiary(id, followUpDiary);
        if (updatedFollowUpDiary != null) {
            return new ResponseEntity<>(updatedFollowUpDiary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowUpDiaryResponseDTO> getFollowUpDiaryById(@PathVariable("id") String id) {
        FollowUpDiaryResponseDTO followUpDiary = followUpDiaryService.getFollowUpDiaryById(id);
        if (followUpDiary != null) {
            return new ResponseEntity<>(followUpDiary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/byClientId/{clientid}")
    public ResponseEntity<List<FollowUpDiaryResponseDTO>> getFollowUpDiaryByClientid(@PathVariable("clientid") String clientid) {
        // FollowUpDiary followUpDiary = (FollowUpDiary) followUpDiaryService.getFollowUpDiaryByClientid(clientid);
        return new ResponseEntity<List<FollowUpDiaryResponseDTO>>(followUpDiaryService.getFollowUpDiaryByClientid(clientid), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<FollowUpDiaryResponseDTO>> getAllFollowUpDiaries() {
        List<FollowUpDiaryResponseDTO> followUpDiaryList = followUpDiaryService.getAllFollowUpDiaries();
        return new ResponseEntity<>(followUpDiaryList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollowUpDiary(@PathVariable("id") String id) {
        followUpDiaryService.deleteFollowUpDiary(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
