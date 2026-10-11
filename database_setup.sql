CREATE DATABASE IF NOT EXISTS vehicle_service_center CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE vehicle_service_center;

CREATE TABLE IF NOT EXISTS customers (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 email VARCHAR(150) NOT NULL UNIQUE,
 phone VARCHAR(20) NOT NULL,
 created_at DATETIME(6) NOT NULL,
 updated_at DATETIME(6) NOT NULL
);
CREATE TABLE IF NOT EXISTS vehicles (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 customer_id BIGINT NOT NULL,
 registration_number VARCHAR(30) NOT NULL UNIQUE,
 make VARCHAR(80) NOT NULL,
 model VARCHAR(80) NOT NULL,
 vehicle_year INT NOT NULL,
 created_at DATETIME(6) NOT NULL,
 CONSTRAINT fk_vehicle_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);
CREATE TABLE IF NOT EXISTS mechanics (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 phone VARCHAR(20) NOT NULL,
 specialization VARCHAR(100) NOT NULL,
 available BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS parts (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(120) NOT NULL,
 part_number VARCHAR(60) NOT NULL UNIQUE,
 unit_price DECIMAL(12,2) NOT NULL,
 stock_quantity INT NOT NULL
);
CREATE TABLE IF NOT EXISTS service_requests (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 vehicle_id BIGINT NOT NULL,
 mechanic_id BIGINT NULL,
 description VARCHAR(1000) NOT NULL,
 status VARCHAR(40) NOT NULL,
 scheduled_at DATETIME(6) NOT NULL,
 notes VARCHAR(2000),
 created_at DATETIME(6) NOT NULL,
 updated_at DATETIME(6) NOT NULL,
 CONSTRAINT fk_request_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
 CONSTRAINT fk_request_mechanic FOREIGN KEY (mechanic_id) REFERENCES mechanics(id)
);
CREATE TABLE IF NOT EXISTS service_request_parts (
 service_request_id BIGINT NOT NULL,
 part_id BIGINT NOT NULL,
 quantity INT NOT NULL,
 PRIMARY KEY (service_request_id, part_id),
 CONSTRAINT fk_srp_request FOREIGN KEY (service_request_id) REFERENCES service_requests(id),
 CONSTRAINT fk_srp_part FOREIGN KEY (part_id) REFERENCES parts(id)
);
CREATE TABLE IF NOT EXISTS invoices (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 service_request_id BIGINT NOT NULL UNIQUE,
 labor_cost DECIMAL(12,2) NOT NULL,
 parts_cost DECIMAL(12,2) NOT NULL,
 total_amount DECIMAL(12,2) NOT NULL,
 issued_at DATETIME(6) NOT NULL,
 CONSTRAINT fk_invoice_request FOREIGN KEY (service_request_id) REFERENCES service_requests(id)
);
