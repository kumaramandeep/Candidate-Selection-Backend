package com.candidateselection.config;

import com.candidateselection.entity.*;
import com.candidateselection.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Profile("!test") // Don't run in tests
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
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole("admin");
        admin.setFullName("Ranjit Singh (HR Manager)");
        userRepository.save(admin);

        User member1 = new User();
        member1.setId(2L);
        member1.setUsername("member1");
        member1.setPassword(passwordEncoder.encode("member123"));
        member1.setRole("member");
        member1.setFullName("Aman Kumar (Board Member)");
        userRepository.save(member1);

        User member2 = new User();
        member2.setId(3L);
        member2.setUsername("member2");
        member2.setPassword(passwordEncoder.encode("member223"));
        member2.setRole("member");
        member2.setFullName("Priya Sharma (Board Member)");
        userRepository.save(member2);

        // Candidates
        Candidate c1 = new Candidate();
        c1.setId(1L);
        c1.setFullName("Amit Kumar");
        c1.setPhotoPath("/assets/img/placeholder.jpg"); // Frontend asset
        c1.setExpertise("coding");
        c1.setLatestQualification("M.Tech (Computer Science)");
        c1.setLastCompany("TechNova Pvt Ltd");
        c1.setTotalExperienceYears(7);
        c1.setPhone("+91-90000-00001");
        c1.setEmail("amit.kumar@example.com");
        c1.setNationality("Indian");
        c1.setCurrentAddress("Bengaluru, Karnataka");
        c1.setPermanentAddress("Patna, Bihar");
        c1.setDob(LocalDate.of(1990, 5, 15));
        c1.setGender("Male");
        c1.setMaritalStatus("Single");

        CandidateQualification cq1 = new CandidateQualification();
        cq1.setDegree("M.Tech");
        cq1.setInstitution("IIT Delhi");
        cq1.setYear("2014");
        cq1.setGrade("9.2 CGPA");
        c1.addQualification(cq1);

        CandidateExperience ce1 = new CandidateExperience();
        ce1.setCompany("TechNova Pvt Ltd");
        ce1.setDesignation("Senior Developer");
        ce1.setDuration("2018-Present");
        ce1.setRole("Leading frontend team");
        c1.addExperience(ce1);

        CandidateCertification cc1 = new CandidateCertification();
        cc1.setName("AWS Certified Developer");
        cc1.setIssuer("Amazon");
        cc1.setYear("2021");
        c1.addCertification(cc1);

        candidateRepository.save(c1);

        // Add more candidates if needed...

        // Meeting State
        MeetingState ms = new MeetingState();
        ms.setId(1L);
        ms.setCurrentCandidateId(1L);
        ms.setVoteOpen(false);
        meetingStateRepository.save(ms);

        System.out.println("Data initialization complete.");
    }
}
