CREATE DATABASE vascomm_backend;

USE vascomm_backend;

CREATE TABLE m_users
(
  user_id          VARCHAR(50) PRIMARY KEY,
  email            VARCHAR(100) NOT NULL,
  password         VARCHAR(100) NOT NULL,
  name             VARCHAR(100) NOT NULL,
  access_token     VARCHAR(300),
  refresh_token    VARCHAR(300),
  token_expired_at BIGINT,
  role             ENUM ('ADMIN', 'USER') DEFAULT 'USER',
  created_at       TIMESTAMP              DEFAULT CURRENT_TIMESTAMP,
  updated_at       TIMESTAMP              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE (access_token),
  UNIQUE (refresh_token),
  UNIQUE (email)
);

create table m_products
(
  product_id   varchar(50) primary key,
  product_name varchar(100),
  price        int,
  stock        int,
  created_at   timestamp default current_timestamp,
  created_by   varchar(50),
  updated_at   timestamp default current_timestamp on update current_timestamp,
  updated_by   varchar(50),
  deleted_at   timestamp default null,
  deleted_by   varchar(50),
  constraint fk_created_by foreign key (created_by) references m_users (user_id) on update cascade on delete cascade,
  constraint fk_updated_by foreign key (updated_by) references m_users (user_id) on update cascade on delete cascade,
  constraint fk_deleted_by foreign key (deleted_by) references m_users (user_id) on update cascade on delete cascade
);


DROP TABLE m_users;
SELECT *
FROM m_users;

DESC m_users;

DELETE
FROM m_users;
