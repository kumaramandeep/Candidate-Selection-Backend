package com.candidateselection.service;

import com.candidateselection.model.MeetingState;
import com.candidateselection.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyMeetingUpdate(MeetingState state) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("currentCandidateId", state.getCurrentCandidateId());
        payload.put("voteOpen", state.isVoteOpen());
        messagingTemplate.convertAndSend("/topic/meeting", payload);
    }

    public void notifyVoteSubmitted(Vote vote) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("candidateId", vote.getCandidateId());
        payload.put("userId", vote.getUserId());
        payload.put("marks", vote.getMarks());
        messagingTemplate.convertAndSend("/topic/votes", payload);
    }
}
