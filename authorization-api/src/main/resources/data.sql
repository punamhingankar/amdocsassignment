DROP TABLE IF EXISTS Users;
 
CREATE TABLE Users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  User_Name VARCHAR(250) NOT NULL,
  Password VARCHAR(250) NOT NULL,
  Status VARCHAR(250) DEFAULT false,
  Token VARCHAR(250) DEFAULT false
);
 
INSERT INTO Users (User_Name, Password, status,Token) VALUES ('amdoc', 'amdoc', false, null);