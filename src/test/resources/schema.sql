CREATE TABLE categorys (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   category_name VARCHAR(255) NOT NULL,
   reg_date TIMESTAMP NOT NULL,
   update_date TIMESTAMP NOT NULL
);

CREATE TABLE status_type (
     id INT NOT NULL PRIMARY KEY,
     status_type_name VARCHAR(50) NOT NULL,
     reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE status (
    id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    status_type_id INT NOT NULL,
    status_name VARCHAR(50) NOT NULL,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT status_ibfk_1 FOREIGN KEY (status_type_id) REFERENCES status_type(id)
);


CREATE INDEX status_type_id_idx ON status (status_type_id);


