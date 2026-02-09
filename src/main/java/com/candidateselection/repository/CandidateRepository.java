package com.candidateselection.repository;

import com.candidateselection.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // Find next candidate with ID > currentId, limit 1
    Optional<Candidate> findFirstByIdGreaterThanOrderByIdAsc(Long id);

    // Find previous candidate with ID < currentId, limit 1
    Optional<Candidate> findFirstByIdLessThanOrderByIdDesc(Long id);

    // Filter by branch
    List<Candidate> findByBranchOrderByEmployeeIdAsc(String branch);

    // Filter by branch and timer(s)
    List<Candidate> findByBranchAndTimerInOrderByEmployeeIdAsc(String branch, List<Integer> timers);

    // Count by branch
    long countByBranch(String branch);

    // Count reviewed by branch
    long countByBranchAndReviewedTrue(String branch);

    // Get all distinct branches
    @Query("SELECT DISTINCT c.branch FROM Candidate c WHERE c.branch IS NOT NULL")
    List<String> findAllBranches();

    // Find by employee ID (for navigation in filtered list)
    Optional<Candidate> findByEmployeeId(String employeeId);
}
