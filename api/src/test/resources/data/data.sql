INSERT INTO project (id, name, description) VALUES (-1,'Project 1','Description 1');
INSERT INTO project (id, name, description) VALUES (-2,'Project 2','Description 2');
INSERT INTO project (id, name, description) VALUES (-3,'Project 3','Description 3');

INSERT INTO user (id, name, surname, email, password) VALUES (-1, 'User1', 'Surname', 'email', 'password');
INSERT INTO user (id, name, surname, email, password) VALUES (-2, 'User2', 'Surname', 'email2', 'password');

INSERT INTO user_has_project (project_id, user_id, is_confirmed, role) VALUES(-1, -1, 1, 0);
INSERT INTO user_has_project (project_id, user_id, is_confirmed) VALUES(-2, -1, 1);
INSERT INTO user_has_project (project_id, user_id, is_confirmed) VALUES(-2, -2, 0);

INSERT INTO status (id, name) VALUES (-1, "Status 1");
INSERT INTO status (id, name) VALUES (-2, "Status 2");

INSERT INTO task (id, name, project_id, user_id, status_id) VALUES (-2, "Name 1", -1, -1, -1);