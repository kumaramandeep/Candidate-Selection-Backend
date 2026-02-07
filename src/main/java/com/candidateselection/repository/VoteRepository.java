package com.candidateselection.repository;

import com.candidateselection.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    
    List<Vote> findByCandidateId(Long candidateId);
    
    Optional<Vote> findByCandidateIdAndUserId(Long candidateId, Long userId);
    
    void deleteByCandidateId(Long candidateId);
}
