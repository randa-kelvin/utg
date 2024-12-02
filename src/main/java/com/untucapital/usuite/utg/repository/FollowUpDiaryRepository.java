package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.FollowUpDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowUpDiaryRepository extends JpaRepository<FollowUpDiary, String> {

    List<FollowUpDiary> findFollowUpDiaryByClientID(String clientID);
}
