-- role
INSERT INTO inv.in_role(id, role)
VALUES ('e0438fd6-0f95-419b-8e04-3207d3e941f0', 'DEFAULT');

INSERT INTO inv.in_role(id, role)
VALUES ('e0438fd6-0f95-419b-8e04-3207d3e941f1', 'ADMIN');

-- user
--- insert
INSERT INTO inv.in_user(id, email, password, username, role_id)
VALUES ('e0438fd6-0f95-419b-8e04-3207d3e941f0', 'admin@gmail.com', 'admin', 'admin', 'e0438fd6-0f95-419b-8e04-3207d3e941f0');

--- update
UPDATE inv.in_user
SET role_id = 'e0438fd6-0f95-419b-8e04-3207d3e941f1'
WHERE username = 'admin';