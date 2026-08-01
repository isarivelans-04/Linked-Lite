create database linkedlite;
use linkedlite;
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    headline VARCHAR(150),
    about TEXT,
    location VARCHAR(100),
    profile_image VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE skills (
    skill_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    skill_name VARCHAR(100) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE posts (
    post_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE comments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comment TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE post_likes (
    like_id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE comment_likes (
    like_id INT PRIMARY KEY AUTO_INCREMENT,
    comment_id INT NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (comment_id) REFERENCES comments(comment_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE connections (
    connection_id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    status ENUM('PENDING','ACCEPTED','REJECTED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (sender_id) REFERENCES users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES users(user_id)
);
CREATE TABLE jobs (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    recruiter_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    company VARCHAR(150) NOT NULL,
    location VARCHAR(100),
    description TEXT,
    salary DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (recruiter_id) REFERENCES users(user_id)
);
CREATE TABLE job_applications (
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT NOT NULL,
    user_id INT NOT NULL,
    resume_url VARCHAR(255),
    status ENUM('APPLIED','SHORTLISTED','REJECTED','HIRED') DEFAULT 'APPLIED',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (job_id) REFERENCES jobs(job_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE subscriptions (
    subscription_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL UNIQUE,
    plan ENUM('MONTHLY','YEARLY') NOT NULL,
    start_date DATE,
    end_date DATE,
    status ENUM('ACTIVE','EXPIRED') DEFAULT 'ACTIVE',

    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    subscription_id INT NOT NULL,
    user_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_method ENUM('UPI','CARD','NET_BANKING','WALLET'),
    payment_status ENUM('SUCCESS','FAILED','PENDING') DEFAULT 'PENDING',
    transaction_id VARCHAR(100) UNIQUE,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (subscription_id) REFERENCES subscriptions(subscription_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);