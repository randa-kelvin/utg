package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Meetings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingsRepository extends JpaRepository<Meetings, String> {

    List<Meetings> findMeetingsByLoanId(String loanId);

    Meetings findMeetingsById(String id);
}
