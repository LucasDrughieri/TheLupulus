CREATE TABLE thebeermanagement.clients (
  client_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  business_name VARCHAR(100) NOT NULL,
  cuit BIGINT NOT NULL,
  phone_number LONG NOT NULL,
  email VARCHAR(200) NOT NULL,
  address VARCHAR(200) NOT NULL
)
ENGINE=InnoDB;	

CREATE TABLE thebeermanagement.users (
  user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  client_id BIGINT,
  nickname VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(100),
  email VARCHAR(200) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES clients(client_id)
)
ENGINE=InnoDB;