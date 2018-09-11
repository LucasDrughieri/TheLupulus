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
  client_id BIGINT NOT NULL,
  nickname VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(100),
  FOREIGN KEY (client_id) REFERENCES clients(client_id)
)
ENGINE=InnoDB;

CREATE TABLE thebeermanagement.orders (
  order_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  date DATETIME NOT NULL,
  status VARCHAR(100) NOT NULL,
  amount NUMERIC(1000,2),
  visible TINYINT(1) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
)
ENGINE=InnoDB;

CREATE TABLE thebeermanagement.orders (
  order_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  date DATETIME NOT NULL,
  status VARCHAR(100) NOT NULL,
  amount NUMERIC(10,2),
  visible TINYINT(1) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
)
ENGINE=InnoDB;