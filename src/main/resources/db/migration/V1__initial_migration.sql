CREATE TABLE addresses (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  street varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  zip varchar(255) NOT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT addresses_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) NOT NULL,
);



