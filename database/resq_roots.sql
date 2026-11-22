-- Create Database
CREATE DATABASE resq_roots;

USE resq_roots;


-- 1. Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'OPERATOR', 'RESPONSE_TEAM') NOT NULL
);


-- 2. Emergencies Table
CREATE TABLE emergencies (
    emergency_id INT AUTO_INCREMENT PRIMARY KEY,
    person_name VARCHAR(100) NOT NULL,
    contact VARCHAR(15) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description TEXT,
    emergency_type ENUM(
        'MEDICAL',
        'ROAD_ACCIDENT',
        'FIRE',
        'NATURAL_DISASTER'
    ) NOT NULL,
    severity ENUM('CRITICAL', 'HIGH', 'MEDIUM', 'LOW') NOT NULL,
    status ENUM(
        'REGISTERED',
        'WAITING',
        'ASSIGNED',
        'IN_PROGRESS',
        'COMPLETED',
        'CANCELLED'
    ) DEFAULT 'REGISTERED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- 3. Resources Table
CREATE TABLE resources (
    resource_id INT AUTO_INCREMENT PRIMARY KEY,
    resource_name VARCHAR(100) NOT NULL,
    resource_type ENUM(
        'AMBULANCE',
        'FIRE_ENGINE',
        'RESCUE_VEHICLE'
    ) NOT NULL,
    location VARCHAR(255) NOT NULL,
    status ENUM(
        'AVAILABLE',
        'BUSY',
        'MAINTENANCE'
    ) DEFAULT 'AVAILABLE'
);


-- 4. Allocations Table
CREATE TABLE allocations (
    allocation_id INT AUTO_INCREMENT PRIMARY KEY,
    emergency_id INT NOT NULL,
    resource_id INT NOT NULL,
    allocated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (emergency_id)
        REFERENCES emergencies(emergency_id),

    FOREIGN KEY (resource_id)
        REFERENCES resources(resource_id)
);