CREATE DATABASE vascomm_backend;

USE vascomm_backend;

CREATE TABLE users
(
  user_id          VARCHAR(50)  NOT NULL,
  email            VARCHAR(100) NOT NULL,
  password         VARCHAR(100) NOT NULL,
  name             VARCHAR(100) NOT NULL,
  access_token     VARCHAR(300),
  refresh_token    VARCHAR(300),
  token_expired_at BIGINT,
  PRIMARY KEY (user_id),
  UNIQUE (access_token),
  UNIQUE (refresh_token),
  UNIQUE (email)
) ENGINE InnoDB;

DROP TABLE users;
SELECT *
FROM users;

DESC users;

DELETE
FROM users;
