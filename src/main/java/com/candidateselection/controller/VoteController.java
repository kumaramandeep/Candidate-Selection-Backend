package com.candidateselection.controller;

import com.candidateselection.model.Vote;
import com.candidateselection.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public Vote submitVote(@RequestBody Vote vote) {
        return voteService.upsertVote(vote.getCandidateId(), vote.getUserId(), vote.getMarks());
    }

    @GetMapping("/candidate/{candidateId}")
    public List<Vote> getVotes(@PathVariable Long candidateId) {
        return voteService.getVotesForCandidate(candidateId);
    }
    
    @DeleteMapping("/candidate/{candidateId}")
    public ResponseEntity<?> resetVotes(@PathVariable Long candidateId) {
        voteService.deleteVotesForCandidate(candidateId);
        return ResponseEntity.ok().build();
    }
}
