CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO USERS (id, username, password, email, role) VALUES
(gen_random_uuid(), 'testuser', 'testpassword', 'testuser@gmail.com', 'ADMIN');

INSERT INTO BOARDS (id, name, description, owner_id) VALUES
(gen_random_uuid(), 'Mobile App', 'Mobile-related features', SELECT id from USERS where email = 'testuser@gmail.com');

INSERT INTO BOARDS (id, name, description, owner_id) VALUES
(gen_random_uuid(), 'Web Platform', 'Frontend features', SELECT id from USERS where email = 'testuser@gmail.com');

INSERT INTO BOARDS (id, name, description, owner_id) VALUES
(gen_random_uuid(), 'Admin Dashboard', 'Backoffice tools', SELECT id from USERS where email = 'testuser@gmail.com');

