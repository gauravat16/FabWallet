CREATE TABLE USER(

id VARCHAR(255) PRIMARY KEY,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL
); 

ALTER TABLE USER ADD UNIQUE INDEX index_name (id);


CREATE TABLE WALLET(

id BIGINT AUTO_INCREMENT PRIMARY KEY,
user_id VARCHAR(255) NOT NULL,
FOREIGN KEY (user_id) REFERENCES USER(id),
balance DECIMAL(15,2) NOT NULL
); 

CREATE TABLE TRANSACTION(

txn_id BIGINT AUTO_INCREMENT PRIMARY KEY,
txn_type ENUM("CREDIT","DEBIT") NOT NULL,
txn_total_amount DECIMAL(15,2) NOT NULL,
txn_amount DECIMAL(15,2) NOT NULL,
wallet_id BIGINT NOT NULL,
FOREIGN KEY (wallet_id) REFERENCES WALLET(id)
); 


