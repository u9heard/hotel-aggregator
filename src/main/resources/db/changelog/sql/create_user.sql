CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    role_level INT NOT NULL DEFAULT 0
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
    ON DELETE SET NULL
);

INSERT INTO roles(role_name, role_level) VALUES
('Admin', 10),
('User', 1);