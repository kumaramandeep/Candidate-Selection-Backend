package com.candidateselection.service;

import com.candidateselection.model.Candidate;
import com.candidateselection.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
