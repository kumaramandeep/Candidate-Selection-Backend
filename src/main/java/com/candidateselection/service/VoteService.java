package com.candidateselection.service;

import com.candidateselection.model.Vote;
import com.candidateselection.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private WebSocketService webSocketService;

    @Transactional
    public Vote upsertVote(Long candidateId, Long userId, Integer marks) {
        Optional<Vote> existingVote = voteRepository.findByCandidateIdAndUserId(candidateId, userId);
        Vote vote;
        if (existingVote.isPresent()) {
            vote = existingVote.get();
            vote.setMarks(marks);
        } else {
            vote = new Vote();
            vote.setCandidateId(candidateId);
            vote.setUserId(userId);
            vote.setMarks(marks);
        }
        Vote savedVote = voteRepository.save(vote);
        
        // Notify clients via WebSocket
        webSocketService.notifyVoteSubmitted(savedVote);
        
        return savedVote;
    }

    public List<Vote> getVotesForCandidate(Long candidateId) {
        return voteRepository.findByCandidateId(candidateId);
    }

    @Transactional
    public void deleteVotesForCandidate(Long candidateId) {
        voteRepository.deleteByCandidateId(candidateId);
    }
}
