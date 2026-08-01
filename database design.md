# Database Design

## Project Name
LinkedIn Lite

## Database
MySQL 8

---

# Overview

The LinkedIn Lite application uses a relational database to manage users, profiles, posts, interactions, job applications, premium subscriptions, and payments.

The database is normalized to reduce redundancy and maintain data integrity using Primary Keys and Foreign Keys.

---

# Tables

## 1. Users

Stores user account and profile information.

| Column | Data Type | Description |
|---------|-----------|-------------|
| user_id | INT (PK) | Unique User ID |
| full_name | VARCHAR(100) | User's Full Name |
| email | VARCHAR(255) UNIQUE | Email Address |
| password | VARCHAR(255) | Encrypted Password |
| phone | VARCHAR(15) | Phone Number |
| headline | VARCHAR(150) | Professional Headline |
| about | TEXT | User Bio |
| location | VARCHAR(100) | User Location |
| profile_image | VARCHAR(255) | Profile Image URL |
| created_at | TIMESTAMP | Account Creation Time |

---

## 2. Skills

Stores user skills.

| Column | Data Type |
|---------|-----------|
| skill_id | INT (PK) |
| user_id | INT (FK) |
| skill_name | VARCHAR(100) |

Relationship:
- One User → Many Skills

---

## 3. Posts

Stores posts created by users.

| Column | Data Type |
|---------|-----------|
| post_id | INT (PK) |
| user_id | INT (FK) |
| content | TEXT |
| image_url | VARCHAR(255) |
| created_at | TIMESTAMP |

Relationship:
- One User → Many Posts

---

## 4. Comments

Stores comments for posts.

| Column | Data Type |
|---------|-----------|
| comment_id | INT (PK) |
| post_id | INT (FK) |
| user_id | INT (FK) |
| comment | TEXT |
| created_at | TIMESTAMP |

Relationship:
- One Post → Many Comments
- One User → Many Comments

---

## 5. Likes

Stores likes on posts.

| Column | Data Type |
|---------|-----------|
| like_id | INT (PK) |
| post_id | INT (FK) |
| user_id | INT (FK) |
| created_at | TIMESTAMP |

Relationship:
- One User → Many Likes
- One Post → Many Likes

---

## 6. Connections

Stores connection requests between users.

| Column | Data Type |
|---------|-----------|
| connection_id | INT (PK) |
| sender_id | INT (FK) |
| receiver_id | INT (FK) |
| status | ENUM('PENDING','ACCEPTED','REJECTED') |
| created_at | TIMESTAMP |

Relationship:
- User ↔ User (Many-to-Many)

---

## 7. Jobs

Stores job postings.

| Column | Data Type |
|---------|-----------|
| job_id | INT (PK) |
| recruiter_id | INT (FK) |
| title | VARCHAR(150) |
| company | VARCHAR(150) |
| location | VARCHAR(100) |
| description | TEXT |
| salary | DECIMAL(10,2) |
| created_at | TIMESTAMP |

Relationship:
- One Recruiter → Many Jobs

---

## 8. Job Applications

Stores job applications submitted by users.

| Column | Data Type |
|---------|-----------|
| application_id | INT (PK) |
| job_id | INT (FK) |
| user_id | INT (FK) |
| resume_url | VARCHAR(255) |
| status | ENUM('APPLIED','SHORTLISTED','REJECTED','HIRED') |
| applied_at | TIMESTAMP |

Relationship:
- One User → Many Applications
- One Job → Many Applications

---

## 9. Subscriptions

Stores premium subscription details.

| Column | Data Type |
|---------|-----------|
| subscription_id | INT (PK) |
| user_id | INT (FK) |
| plan | ENUM('MONTHLY','YEARLY') |
| start_date | DATE |
| end_date | DATE |
| status | ENUM('ACTIVE','EXPIRED') |

Relationship:
- One User → One Subscription

---

## 10. Payments

Stores payment information.

| Column | Data Type |
|---------|-----------|
| payment_id | INT (PK) |
| subscription_id | INT (FK) |
| user_id | INT (FK) |
| amount | DECIMAL(10,2) |
| payment_method | VARCHAR(50) |
| payment_status | ENUM('SUCCESS','FAILED','PENDING') |
| transaction_id | VARCHAR(100) |
| payment_date | TIMESTAMP |

Relationship:
- One Subscription → Many Payments

---

# Entity Relationships

- Users → Skills (1:N)
- Users → Posts (1:N)
- Users → Comments (1:N)
- Users → Likes (1:N)
- Users → Jobs (1:N)
- Users → Job Applications (1:N)
- Users ↔ Users (Connections) (M:N)
- Posts → Comments (1:N)
- Posts → Likes (1:N)
- Jobs → Job Applications (1:N)
- Users → Subscriptions (1:1)
- Subscriptions → Payments (1:N)

---

# Database Normalization

The database follows normalization principles:

- First Normal Form (1NF)
- Second Normal Form (2NF)
- Third Normal Form (3NF)

This minimizes data redundancy and improves consistency.

---

# Primary Keys

Every table contains a Primary Key to uniquely identify each record.

Example:

- user_id
- post_id
- comment_id
- job_id

---

# Foreign Keys

Foreign Keys establish relationships between tables.

Examples:

- posts.user_id → users.user_id
- comments.post_id → posts.post_id
- comments.user_id → users.user_id
- skills.user_id → users.user_id
- payments.subscription_id → subscriptions.subscription_id

---

# Conclusion

The LinkedIn Lite database is designed using a relational model with normalized tables and proper relationships. This design supports user management, networking, content sharing, job applications, premium subscriptions, and payment processing while ensuring data integrity and scalability.