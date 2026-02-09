package com.candidateselection.controller;

import com.candidateselection.model.Candidate;
import com.candidateselection.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAllCandidates(
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) List<Integer> timers) {
        if (branch != null && !branch.isEmpty()) {
            return candidateService.getByBranchAndTimers(branch, timers);
        }
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable Long id) {
        return candidateService.getCandidate(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Candidate createCandidate(@RequestBody Candidate candidate) {
        return candidateService.createCandidate(candidate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        return candidateService.getCandidate(id)
                .map(existing -> {
                    candidate.setId(id);
                    return ResponseEntity.ok(candidateService.saveCandidate(candidate));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all branches
    @GetMapping("/branches")
    public List<String> getAllBranches() {
        return candidateService.getAllBranches();
    }

    // Get statistics (branch-wise reviewed counts)
    @GetMapping("/statistics")
    public Map<String, Map<String, Long>> getStatistics() {
        return candidateService.getStatistics();
    }

    // Mark candidate as reviewed
    @PostMapping("/{id}/reviewed")
    public ResponseEntity<Void> markAsReviewed(@PathVariable Long id) {
        candidateService.markAsReviewed(id);
        return ResponseEntity.ok().build();
    }

    // Photo upload (simplified for dev)
    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            return ResponseEntity.ok("/uploads/" + fileName);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/next")
    public ResponseEntity<Long> getNextId(@PathVariable Long id) {
        Long nextId = candidateService.getNextId(id);
        return nextId != null ? ResponseEntity.ok(nextId) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/prev")
    public ResponseEntity<Long> getPrevId(@PathVariable Long id) {
        Long prevId = candidateService.getPrevId(id);
        return prevId != null ? ResponseEntity.ok(prevId) : ResponseEntity.notFound().build();
    }
}
