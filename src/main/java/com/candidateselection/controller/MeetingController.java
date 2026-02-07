package com.candidateselection.controller;

import com.candidateselection.model.MeetingState;
import com.candidateselection.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping
    public MeetingState getState() {
        return meetingService.getState();
    }

    @PostMapping("/candidate/{id}")
    public MeetingState setCandidate(@PathVariable Long id) {
        return meetingService.setCurrentCandidate(id);
    }

    @PostMapping("/vote/open")
    public MeetingState openVote() {
        return meetingService.setVoteOpen(true);
    }

    @PostMapping("/vote/close")
    public MeetingState closeVote() {
        return meetingService.setVoteOpen(false);
    }
}
