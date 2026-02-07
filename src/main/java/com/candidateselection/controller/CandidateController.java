package com.candidateselection.controller;

import com.candidateselection.model.Candidate;
import com.candidateselection.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAllCandidates() {
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
    
    // Helper to upload photo (simplified for dev)
    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            // In a real app, save to S3 or dedicated storage
            // For now, just return a path
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
