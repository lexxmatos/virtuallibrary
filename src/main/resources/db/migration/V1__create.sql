  CREATE TABLE USER (id INT(10) AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(100) NOT NULL,
  EMAIL VARCHAR(100) NOT NULL,
  `DATE` DATE NOT NULL,
  ACTION VARCHAR(20) NOT NULL);

CREATE TABLE BOOK (id INT(10) AUTO_INCREMENT PRIMARY KEY,
  TITLE VARCHAR(100) NOT NULL,
  CATEGORY VARCHAR(100) NOT NULL,
  REMOVE VARCHAR(100) NOT NULL);
  
CREATE TABLE COPIES (id INT(10) AUTO_INCREMENT PRIMARY KEY,
  AVAILABLE VARCHAR(100) NOT NULL,
  ACTION VARCHAR(100) NOT NULL,
  BOOK_ID INT(10) NOT NULL);  
