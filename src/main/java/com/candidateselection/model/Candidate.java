package com.candidateselection.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "candidates")
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String photoPath;
    private String expertise;
    private String latestQualification;
    private String lastCompany;
    private Integer totalExperienceYears;

    // Additional Personal Info
    private String phone;
    private String email;
    private String nationality;
    private String currentAddress;
    private String permanentAddress;
    private String dob;
    private String gender;
    private String maritalStatus;

    @ElementCollection
    @CollectionTable(name = "candidate_qualifications", joinColumns = @JoinColumn(name = "candidate_id"))
    private List<Qualification> qualifications;

    @ElementCollection
    @CollectionTable(name = "candidate_experiences", joinColumns = @JoinColumn(name = "candidate_id"))
    private List<Experience> experiences;

    @ElementCollection
    @CollectionTable(name = "candidate_certifications", joinColumns = @JoinColumn(name = "candidate_id"))
    private List<Certification> certifications;

    @Embeddable
    @Data
    public static class Qualification {
        private String degree;
        private String institution;
        private String graduationYear;
        private String grade;
    }

    @Embeddable
    @Data
    public static class Experience {
        private String company;
        private String designation;
        private String duration;
        private String role;
    }

    @Embeddable
    @Data
    public static class Certification {
        private String name;
        private String issuer;
        private String certificationYear;
    }
}
