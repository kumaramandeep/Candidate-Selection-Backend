package com.candidateselection.service;

import com.candidateselection.model.Candidate;
import com.candidateselection.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidate(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Long getNextId(Long currentId) {
        return candidateRepository.findFirstByIdGreaterThanOrderByIdAsc(currentId)
                .map(Candidate::getId)
                .orElse(null);
    }

    public Long getPrevId(Long currentId) {
        return candidateRepository.findFirstByIdLessThanOrderByIdDesc(currentId)
                .map(Candidate::getId)
                .orElse(null);
    }

    // ===== NEW METHODS FOR V2 =====

    public List<Candidate> getByBranch(String branch) {
        return candidateRepository.findByBranchOrderByEmployeeIdAsc(branch);
    }

    public List<Candidate> getByBranchAndTimers(String branch, List<Integer> timers) {
        if (timers == null || timers.isEmpty()) {
            return getByBranch(branch);
        }
        return candidateRepository.findByBranchAndTimerInOrderByEmployeeIdAsc(branch, timers);
    }

    public List<String> getAllBranches() {
        return candidateRepository.findAllBranches();
    }

    public Map<String, Map<String, Long>> getStatistics() {
        Map<String, Map<String, Long>> stats = new LinkedHashMap<>();

        List<String> branches = candidateRepository.findAllBranches();
        for (String branch : branches) {
            Map<String, Long> branchStats = new HashMap<>();
            branchStats.put("total", candidateRepository.countByBranch(branch));
            branchStats.put("reviewed", candidateRepository.countByBranchAndReviewedTrue(branch));
            stats.put(branch, branchStats);
        }

        return stats;
    }

    public void markAsReviewed(Long id) {
        candidateRepository.findById(id).ifPresent(c -> {
            c.setReviewed(true);
            candidateRepository.save(c);
        });
    }

    // Navigation within filtered list
    public Long getNextIdInList(List<Candidate> candidates, Long currentId) {
        for (int i = 0; i < candidates.size() - 1; i++) {
            if (candidates.get(i).getId().equals(currentId)) {
                return candidates.get(i + 1).getId();
            }
        }
        return null;
    }

    public Long getPrevIdInList(List<Candidate> candidates, Long currentId) {
        for (int i = 1; i < candidates.size(); i++) {
            if (candidates.get(i).getId().equals(currentId)) {
                return candidates.get(i - 1).getId();
            }
        }
        return null;
    }
}
