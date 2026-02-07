package com.candidateselection.service;

import com.candidateselection.model.MeetingState;
import com.candidateselection.repository.MeetingStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeetingService {

    @Autowired
    private MeetingStateRepository meetingStateRepository;

    @Autowired
    private WebSocketService webSocketService;

    public MeetingState getState() {
        return meetingStateRepository.findById(1L).orElseGet(() -> {
            MeetingState newState = new MeetingState();
            newState.setId(1L);
            newState.setCurrentCandidateId(null);
            newState.setVoteOpen(false);
            return meetingStateRepository.save(newState);
        });
    }

    @Transactional
    public MeetingState setCurrentCandidate(Long candidateId) {
        MeetingState state = getState();
        state.setCurrentCandidateId(candidateId);
        state.setVoteOpen(false); // Close voting when changing candidate
        MeetingState savedState = meetingStateRepository.save(state);
        
        webSocketService.notifyMeetingUpdate(savedState);
        return savedState;
    }

    @Transactional
    public MeetingState setVoteOpen(boolean isOpen) {
        MeetingState state = getState();
        state.setVoteOpen(isOpen);
        MeetingState savedState = meetingStateRepository.save(state);
        
        webSocketService.notifyMeetingUpdate(savedState);
        return savedState;
    }
}
