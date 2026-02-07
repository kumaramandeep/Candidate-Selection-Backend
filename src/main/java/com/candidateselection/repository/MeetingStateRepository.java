package com.candidateselection.repository;

import com.candidateselection.model.MeetingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingStateRepository extends JpaRepository<MeetingState, Long> {
}
