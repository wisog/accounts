INSERT INTO role (id, name) VALUES (1,'Admin');
INSERT INTO role (id, name) VALUES (2,'User');

INSERT INTO account (id, email, username, password, create_time, active, role_id ) VALUES (0, 'test@test.com', 'user1', '$2a$04$NWXEwV25vHE6xwk0fQkWGeTfB1oDqsFEc6Ngtesk9R47vvrZZMHnC', now(), 1, 2);