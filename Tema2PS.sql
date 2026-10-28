drop database if exists ps_tema2;
create database if not exists ps_tema2;
use ps_tema2;

-- Table for flights (zboruri)
CREATE TABLE zboruri (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plecare VARCHAR(255) NOT NULL,
    destinatie VARCHAR(255) NOT NULL,
    nrLocuri INT NOT NULL
);

-- Table for users (utilizatori)
CREATE TABLE utilizatori (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    tip INT NOT NULL
);

-- Table for passengers (pasageri)
CREATE TABLE passengers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL
);

-- Table for tickets (bilete)
CREATE TABLE bilete (
    nrbilet INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL,
    nrzbor INT NOT NULL,
    FOREIGN KEY (nrzbor) REFERENCES zboruri(id)
);