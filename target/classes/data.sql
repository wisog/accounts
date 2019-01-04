INSERT INTO role (id, name) VALUES (1,'Admin');
INSERT INTO role (id, name) VALUES (2,'User');

INSERT INTO account (id, email, username, password, create_time, active, role_id ) VALUES (0, 'test@test.com', 'admin', '53cr3t', now(), 1, 1);