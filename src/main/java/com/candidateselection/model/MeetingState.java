package com.candidateselection.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "meeting_state")
@Data
public class MeetingState {
    @Id
    private Long id = 1L; // Singleton row

    private Long currentCandidateId;
    private boolean voteOpen;
}
