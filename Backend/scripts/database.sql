CREATE TABLE thebeermanagement.clients (
  client_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  business_name VARCHAR(100) NOT NULL,
  cuit BIGINT NOT NULL,
  phone_number LONG NOT NULL,
  email VARCHAR(200) NOT NULL,
  address VARCHAR(200) NOT NULL
)
ENGINE=InnoDB;	