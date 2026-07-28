# LinkedIn Lite - Capstone Project Problem Statement

## 1. Title

# LinkedIn Lite - Professional Networking Platform MVP

---

# 2. Domain

**Domain:** Professional Networking / Career Development Platform

LinkedIn Lite belongs to the social networking and career development domain.  
The application helps students, professionals, and recruiters connect with each other, share professional information, and discover career opportunities.

---

# 3. Who is the User? (User Types & Roles)

## 1. Job Seeker / Professional User

**Role:**
- Creates professional profile
- Adds skills, education, and experience
- Connects with other professionals
- Searches and applies for jobs
- Shares professional posts


## 2. Recruiter / Employer

**Role:**
- Creates company profile
- Posts job opportunities
- Searches candidate profiles
- Reviews applicant information


## 3. Admin

**Role:**
- Manages users
- Monitors platform activities
- Handles reports and security management

---

# 4. What Problem Are We Solving?

Many students and professionals struggle to build professional networks and showcase their skills effectively. Existing platforms like LinkedIn provide many advanced features but can be complex for beginners.

For example, a final-year student may have technical skills and projects but does not have an easy platform to create a professional identity and connect with recruiters.

LinkedIn Lite solves this problem by providing a simple networking platform where users can create profiles, showcase skills, connect with professionals, and discover job opportunities.

The platform provides essential career networking features in a lightweight and easy-to-use application.

---

# 5. Proposed Solution

LinkedIn Lite is a professional networking web application that provides the following features:

## User Authentication

- User registration
- Login/logout
- Secure password storage
- Role-based access


## Profile Management

Users can:

- Create professional profiles
- Add profile information
- Add skills
- Add education details
- Add work experience


## Networking System

Users can:

- Search professionals
- Send connection requests
- Accept/reject requests
- View connections


## Post Sharing Module

Users can:

- Create professional posts
- View feed
- Like posts
- Comment on posts


## Job Portal Module

Recruiters can:

- Create job postings
- View applicants

Users can:

- Search jobs
- Apply for jobs


## Premium Membership Module

Users can:

- Select premium plans
- Make payment
- Activate premium features

Premium features:

- Increased profile visibility
- Advanced job access
- Recruiter insights

---

# 6. Core Entities / Database Tables

The application uses the following database tables:

## 1. Users Table

Stores user account information.

Fields:

- user_id
- name
- email
- password
- role
- created_date


## 2. Profiles Table

Stores professional profile details.

Fields:

- profile_id
- user_id
- bio
- education
- experience
- skills


## 3. Posts Table

Stores user posts.

Fields:

- post_id
- user_id
- content
- created_time


## 4. Connections Table

Stores networking relationships.

Fields:

- connection_id
- sender_id
- receiver_id
- status


## 5. Jobs Table

Stores job information.

Fields:

- job_id
- recruiter_id
- job_title
- company_name
- description


## 6. Applications Table

Stores job applications.

Fields:

- application_id
- job_id
- user_id
- application_status


## 7. Payments Table

Stores premium subscription details.

Fields:

- payment_id
- user_id
- plan_name
- amount
- payment_status


## 8. Comments Table

Stores comments on posts.

Fields:

- comment_id
- post_id
- user_id
- comment_text

---

# 7. User Roles & Permissions

| Role | Permissions |
|------|-------------|
| User / Job Seeker | Create profile, create posts, connect with users, apply for jobs, purchase premium |
| Recruiter | Create company profile, post jobs, view candidates |
| Admin | Manage users, monitor activities, manage reports |

---

# 8. Success Criteria

The project will be considered successful if:

- A user can register and login within 1 minute.
- A user can create a professional profile successfully.
- Users can send and manage connection requests.
- Users can create and view professional posts.
- Recruiters can post job opportunities.
- Users can apply for available jobs.
- Premium payment flow can successfully activate premium membership.
- Application data is stored securely in the database.

---

# 9. Out of Scope

The following features will NOT be implemented in the MVP version:

- Real-time chat system
- Video calling
- AI-based job recommendation
- Resume AI analyzer
- Mobile application
- Complex payment gateway integration
- Cloud-scale deployment
- Advanced recommendation algorithms
- Social media integration


These features may be added in future versions.

---

# 10. Chosen Track

## Selected Track:

# Java (Spring Boot)

## Technology Stack:

### Frontend:
- HTML
- CSS
- JavaScript

### Backend:
- Java
- Spring Boot

### Database:
- MySQL

### Development Tools:
- VS Code
- GitHub
- Postman


---

# Project Summary

LinkedIn Lite is a lightweight professional networking platform designed as a Minimum Viable Product (MVP).

The application focuses on solving the problem of professional identity creation and career networking for students, professionals, and recruiters.

The project demonstrates concepts of:

- Java backend development
- Database management
- Web application development
- Authentication
- Role-based access control
- Payment module integration