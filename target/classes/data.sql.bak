-- Users
INSERT INTO users (id, username, password, role, full_name) VALUES 
(1, 'admin', '$2a$10$X7.G11x6y33.S33d3d3d3e3f3g3h3i3j3k3l3m3n3o3p3q3r3s', 'admin', 'Ranjit Singh (HR Manager)');
INSERT INTO users (id, username, password, role, full_name) VALUES 
(2, 'member1', '$2a$10$X7.G11x6y33.S33d3d3d3e3f3g3h3i3j3k3l3m3n3o3p3q3r3s', 'member', 'Aman Kumar (Board Member)');
INSERT INTO users (id, username, password, role, full_name) VALUES 
(3, 'member2', '$2a$10$X7.G11x6y33.S33d3d3d3e3f3g3h3i3j3k3l3m3n3o3p3q3r3s', 'member', 'Priya Sharma (Board Member)');

-- Candidates
INSERT INTO candidates (id, full_name, photo_path, expertise, latest_qualification, last_company, total_experience_years, phone, email, nationality, current_address, permanent_address, dob, gender, marital_status) VALUES
(1, 'Amit Kumar', '/assets/img/placeholder.jpg', 'coding', 'M.Tech (Computer Science)', 'TechNova Pvt Ltd', 7, '+91-90000-00001', 'amit.kumar@example.com', 'Indian', 'Bengaluru, Karnataka', 'Patna, Bihar', '1990-05-15', 'Male', 'Single');

INSERT INTO candidates (id, full_name, photo_path, expertise, latest_qualification, last_company, total_experience_years, phone, email, nationality, current_address, permanent_address, dob, gender, marital_status) VALUES
(2, 'Sneha Gupta', '/assets/img/placeholder.jpg', 'project_management', 'MBA (IT Management)', 'InnoSystems Inc.', 10, '+91-90000-00002', 'sneha.gupta@example.com', 'Indian', 'Mumbai, Maharashtra', 'Lucknow, UP', '1987-08-22', 'Female', 'Married');

INSERT INTO candidates (id, full_name, photo_path, expertise, latest_qualification, last_company, total_experience_years, phone, email, nationality, current_address, permanent_address, dob, gender, marital_status) VALUES
(3, 'Rahul Verma', '/assets/img/placeholder.jpg', 'system_design', 'B.Tech (Electronics)', 'CyberSolutions', 5, '+91-90000-00003', 'rahul.verma@example.com', 'Indian', 'Hyderabad, Telangana', 'Ranchi, Jharkhand', '1992-12-10', 'Male', 'Single');

-- Candidate Qualifications
INSERT INTO candidate_qualifications (candidate_id, degree, institution, year, grade) VALUES (1, 'M.Tech', 'IIT Delhi', '2014', '9.2 CGPA');
INSERT INTO candidate_qualifications (candidate_id, degree, institution, year, grade) VALUES (1, 'B.Tech', 'NIT Trichy', '2012', '8.8 CGPA');

-- Candidate Experiences
INSERT INTO candidate_experiences (candidate_id, company, designation, duration, role) VALUES (1, 'TechNova Pvt Ltd', 'Senior Developer', '2018-Present', 'Leading frontend team');
INSERT INTO candidate_experiences (candidate_id, company, designation, duration, role) VALUES (1, 'WebSys Solutions', 'Software Engineer', '2014-2018', 'Full stack development');

-- Candidate Certifications
INSERT INTO candidate_certifications (candidate_id, name, issuer, year) VALUES (1, 'AWS Certified Developer', 'Amazon', '2021');
INSERT INTO candidate_certifications (candidate_id, name, issuer, year) VALUES (1, 'React Nanodegree', 'Udacity', '2019');

-- Meeting State
INSERT INTO meeting_state (id, current_candidate_id, vote_open) VALUES (1, 1, false);
