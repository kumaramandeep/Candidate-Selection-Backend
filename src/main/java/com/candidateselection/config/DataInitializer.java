package com.candidateselection.config;

import com.candidateselection.model.*;
import com.candidateselection.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final MeetingStateRepository meetingStateRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            System.out.println("Data already initialized. Skipping.");
            return;
        }

        System.out.println("Initializing data...");

        // Users
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole("admin");
        admin.setFullName("Ranjit Singh (HR Manager)");
        userRepository.save(admin);

        User member1 = new User();
        member1.setUsername("member1");
        member1.setPassword(passwordEncoder.encode("member123"));
        member1.setRole("member");
        member1.setFullName("Aman Kumar (Board Member)");
        userRepository.save(member1);

        User member2 = new User();
        member2.setUsername("member2");
        member2.setPassword(passwordEncoder.encode("member223"));
        member2.setRole("member");
        member2.setFullName("Priya Sharma (Board Member)");
        userRepository.save(member2);

        // Candidates
        Candidate c1 = new Candidate();
        c1.setFullName("Amit Kumar");
        c1.setPhotoPath("/assets/img/placeholder.jpg");
        c1.setExpertise("coding");
        c1.setLatestQualification("M.Tech (Computer Science)");
        c1.setLastCompany("TechNova Pvt Ltd");
        c1.setTotalExperienceYears(7);
        c1.setPhone("+91-90000-00001");
        c1.setEmail("amit.kumar@example.com");
        c1.setNationality("Indian");
        c1.setCurrentAddress("Bengaluru, Karnataka");
        c1.setPermanentAddress("Patna, Bihar");
        c1.setDob("1990-05-15");
        c1.setGender("Male");
        c1.setMaritalStatus("Single");

        // Qualifications
        Candidate.Qualification cq1 = new Candidate.Qualification();
        cq1.setDegree("M.Tech");
        cq1.setInstitution("IIT Delhi");
        cq1.setYear("2014");
        cq1.setGrade("9.2 CGPA");

        c1.setQualifications(new ArrayList<>());
        c1.getQualifications().add(cq1);

        // Experiences
        Candidate.Experience ce1 = new Candidate.Experience();
        ce1.setCompany("TechNova Pvt Ltd");
        ce1.setDesignation("Senior Developer");
        ce1.setDuration("2018-Present");
        ce1.setRole("Leading frontend team");

        c1.setExperiences(new ArrayList<>());
        c1.getExperiences().add(ce1);

        // Certifications
        Candidate.Certification cc1 = new Candidate.Certification();
        cc1.setName("AWS Certified Developer");
        cc1.setIssuer("Amazon");
        cc1.setYear("2021");

        c1.setCertifications(new ArrayList<>());
        c1.getCertifications().add(cc1);

        candidateRepository.save(c1);

        // Meeting State
        MeetingState ms = new MeetingState();
        ms.setCurrentCandidateId(c1.getId());
        ms.setVoteOpen(false);
        meetingStateRepository.save(ms);

        System.out.println("Data initialization complete.");
    }
}
