-- Run this before starting the project

CREATE DATABASE IF NOT EXISTS loan_risk;
USE loan_risk;

CREATE TABLE IF NOT EXISTS borrowers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    income DOUBLE NOT NULL,
    debt DOUBLE NOT NULL,
    employment_status VARCHAR(50) NOT NULL,
    requested_amount DOUBLE NOT NULL,
    score INT,                         
    risk_level VARCHAR(10),            
    eligible BOOLEAN,                  
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'officer') DEFAULT 'officer'
);

CREATE TABLE IF NOT EXISTS risk_rules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    metric VARCHAR(50) NOT NULL,        
    weight DECIMAL(5,2) DEFAULT 0.30,  
    min_threshold INT DEFAULT 0,
    max_threshold INT DEFAULT 100
);

INSERT INTO borrowers (name, income, debt, employment_status, requested_amount, score, risk_level, eligible)
VALUES ('Test User', 30000, 10000, 'Regular', 20000, 70, 'Low', TRUE);

INSERT INTO users (username, password, role)
VALUES ('admin', 'admin123', 'admin'), ('officer1', 'officer123', 'officer');

INSERT INTO risk_rules (metric, weight, min_threshold, max_threshold) 
VALUES ('dti_ratio', 0.40, 0, 100), ('employment', 0.60, 0, 100);


