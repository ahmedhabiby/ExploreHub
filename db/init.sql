-- =========================
-- ROLES
-- =========================
INSERT INTO roles (id, name) VALUES
                                 (1, 'ROLE_USER'),
                                 (2, 'ROLE_ADMIN');

-- =========================
-- USERS
-- password هنا مشفرة (مثال BCrypt)
-- =========================
INSERT INTO users (id, username, password, email, enabled) VALUES
                                                               (
                                                                   1,
                                                                   'ahmed',
                                                                   '$2a$10$7s6n3JtE5Qx7WmPp8xkXVe8Qw7x7l2K0c0FQz1d9o5pG2G8c2Qy4K',
                                                                   'ahmed@gmail.com',
                                                                   true
                                                               ),
                                                               (
                                                                   2,
                                                                   'admin',
                                                                   '$2a$10$7s6n3JtE5Qx7WmPp8xkXVe8Qw7x7l2K0c0FQz1d9o5pG2G8c2Qy4K',
                                                                   'admin@gmail.com',
                                                                   true
                                                               );

-- =========================
-- USERS_ROLES (RELATION)
-- =========================
INSERT INTO users_roles (user_id, role_id) VALUES


                                               (1, 1), -- ahmed → ROLE_USER
                                               (2, 2); -- admin → ROLE_ADMIN
