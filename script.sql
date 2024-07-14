CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT
);

CREATE TABLE records (
                         id SERIAL PRIMARY KEY,
                         user_id INT NOT NULL,
                         project_id INT NOT NULL,
                         start_time TIMESTAMP NOT NULL,
                         end_time TIMESTAMP NOT NULL,
                         description TEXT,
                         FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                         FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE SET NULL
);

CREATE INDEX idx_records_user_id ON records (user_id);
CREATE INDEX idx_records_project_id ON records (project_id);

INSERT INTO USERS (username, password, role) VALUES ('admin','$2a$10$.RTCRIMIn8N7W9MzB/oo3eR4F6d6PFyckzhTMadUzyoqAfXbq7GU2','ROLE_ADMIN')