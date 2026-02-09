package com.candidateselection.config;

import com.candidateselection.model.*;
import com.candidateselection.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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

        // ========== USERS ==========

        // Admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("aman"));
        admin.setRole("admin");
        admin.setFullName("Aman");
        userRepository.save(admin);

        // Members
        User member1 = new User();
        member1.setUsername("member1");
        member1.setPassword(passwordEncoder.encode("deepak"));
        member1.setRole("member");
        member1.setFullName("Deepak Sharma");
        userRepository.save(member1);

        User member2 = new User();
        member2.setUsername("member2");
        member2.setPassword(passwordEncoder.encode("ranjit"));
        member2.setRole("member");
        member2.setFullName("Ranjit Singh");
        userRepository.save(member2);

        User member3 = new User();
        member3.setUsername("member3");
        member3.setPassword(passwordEncoder.encode("rahul"));
        member3.setRole("member");
        member3.setFullName("Rahul Uttam");
        userRepository.save(member3);

        // ========== CANDIDATES - MET BRANCH ==========

        Candidate c1 = createCandidate(
                "31058", "MET", 1, "WG CDR", "Vikram Sharma",
                "35 WG", "MET", "01 SEP 2025", "30 JUN 2043", "CAT A");
        candidateRepository.save(c1);

        Candidate c2 = createCandidate(
                "41000", "MET", 1, "WG CDR", "Ranjit Singh",
                "12 WG", "MET", "15 MAR 2024", "28 FEB 2042", "CAT A");
        candidateRepository.save(c2);

        Candidate c3 = createCandidate(
                "51234", "MET", 2, "SQN LDR", "Amit Kumar",
                "22 WG", "MET", "20 JUL 2023", "15 DEC 2045", "CAT B");
        candidateRepository.save(c3);

        Candidate c4 = createCandidate(
                "61089", "MET", 2, "GP CAPT", "Priya Sharma",
                "Air HQ", "MET", "10 JAN 2022", "31 MAR 2040", "CAT A");
        candidateRepository.save(c4);

        // ========== CANDIDATES - FLYING BRANCH ==========

        Candidate c5 = createCandidate(
                "21058", "FLYING", 1, "WG CDR", "Suresh Menon",
                "7 SQN", "PILOT", "05 APR 2024", "20 SEP 2044", "CAT A");
        candidateRepository.save(c5);

        Candidate c6 = createCandidate(
                "32000", "FLYING", 1, "SQN LDR", "Kavita Rao",
                "17 SQN", "NAV", "12 NOV 2023", "18 JUL 2046", "CAT B");
        candidateRepository.save(c6);

        Candidate c7 = createCandidate(
                "43567", "FLYING", 3, "WG CDR", "Rahul Nair",
                "45 SQN", "PILOT", "08 FEB 2025", "25 DEC 2041", "CAT A");
        candidateRepository.save(c7);

        // ========== CANDIDATES - ADMIN BRANCH ==========

        Candidate c8 = createCandidate(
                "52345", "ADMIN", 1, "SQN LDR", "Deepa Verma",
                "Air HQ", "ACCTS", "01 JUN 2024", "30 APR 2048", "CAT A");
        candidateRepository.save(c8);

        Candidate c9 = createCandidate(
                "63456", "ADMIN", 2, "WG CDR", "Mohan Das",
                "MC Pune", "ADMIN", "15 AUG 2023", "14 AUG 2042", "CAT B");
        candidateRepository.save(c9);

        // ========== MEETING STATE ==========
        MeetingState ms = new MeetingState();
        ms.setCurrentCandidateId(c1.getId());
        ms.setVoteOpen(false);
        meetingStateRepository.save(ms);

        System.out.println("Data initialization complete - added 4 users and 9 candidates.");
    }

    private Candidate createCandidate(
            String employeeId, String branch, Integer timer, String rank, String fullName,
            String unit, String appt, String dop, String dor, String category) {
        Candidate c = new Candidate();
        c.setEmployeeId(employeeId);
        c.setBranch(branch);
        c.setTimer(timer);
        c.setRank(rank);
        c.setFullName(fullName);
        c.setUnit(unit);
        c.setAppt(appt);
        c.setDop(dop);
        c.setDor(dor);
        c.setCategory(category);
        c.setPhotoPath("/assets/img/placeholder.svg");
        c.setPhoto2Path("/assets/img/placeholder.svg");
        c.setReviewed(false);
        return c;
    }
}
