-- Insert roles if they don't exist
MERGE INTO roles (name, description) KEY(name) VALUES ('ROLE_ADMIN', 'Administrateur');
MERGE INTO roles (name, description) KEY(name) VALUES ('ROLE_USER', 'Utilisateur');
MERGE INTO roles (name, description) KEY(name) VALUES ('ROLE_MANAGER', 'Gestionnaire');

-- Insert default admin user if it doesn't exist
MERGE INTO users (email, username, full_name, password, enabled) KEY(email) VALUES (
    'admin@example.com',
    'admin',
    'Admin User',
    '$2a$10$aMzSRh8jUbJxGW0Vhx8abeZ1JL3FKJw.Zs8DCj9TgNplbKOjONKUa', -- password: admin123
    true
);

-- Link admin user to admin role
MERGE INTO user_roles (user_id, role_id) 
SELECT u.id, r.id FROM users u, roles r
WHERE u.email = 'admin@example.com' AND r.name = 'ROLE_ADMIN';
