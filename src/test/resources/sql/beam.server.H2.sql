CREATE SCHEMA IF NOT EXISTS BEAM_SERVER_TEST;

CREATE TABLE IF NOT EXISTS PAGES (
  page_id 	INTEGER 	NOT NULL AUTO_INCREMENT,
  page_name 	VARCHAR(300) 	NOT NULL,
  page_url 	VARCHAR(500) 	NOT NULL,
  page_order 	INTEGER 	NOT NULL,
  dir_id 	INTEGER 	NOT NULL,
  PRIMARY KEY (page_id));

CREATE TABLE IF NOT EXISTS USERS (
  user_id 	INTEGER 	NOT NULL AUTO_INCREMENT,
  user_name 	VARCHAR(200) 	NOT NULL,
  user_email 	VARCHAR(200) 	NOT NULL,
  user_surname 	VARCHAR(200) 	NOT NULL,
  user_nickname VARCHAR(200) 	NOT NULL,
  user_role 	VARCHAR(10) 	NOT NULL,
  user_password VARCHAR(45) 	NOT NULL,
  PRIMARY KEY (user_id));

CREATE TABLE IF NOT EXISTS DIRS (
  dir_id 	INTEGER 	NOT NULL AUTO_INCREMENT,
  user_id 	INTEGER 	NOT NULL,
  dir_name 	VARCHAR(300) 	NOT NULL,
  dir_order 	INTEGER 	NOT NULL,
  dir_place 	VARCHAR(30) 	NOT NULL,
  PRIMARY KEY (dir_id));

CREATE TABLE IF NOT EXISTS KEYS (
  key_synthetic_id 	INTEGER 	NOT NULL AUTO_INCREMENT,
  key_natural_id 	VARCHAR(20) 	NOT NULL,
  key_algorithm 	VARCHAR(45) 	NOT NULL,
  key_binary 		VARBINARY(1000) NOT NULL,
  PRIMARY KEY (key_synthetic_id));