package com.candidateselection.repository;

import com.candidateselection.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    
    // Find next candidate with ID > currentId, limit 1
    Optional<Candidate> findFirstByIdGreaterThanOrderByIdAsc(Long id);

    // Find previous candidate with ID < currentId, limit 1
    Optional<Candidate> findFirstByIdLessThanOrderByIdDesc(Long id);
}
