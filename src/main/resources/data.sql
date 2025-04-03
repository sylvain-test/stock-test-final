-- Insérer des données dans ROLES
INSERT INTO ROLES (ID, NAME, DESCRIPTION) VALUES 
(1, 'ADMIN', 'Administrator with full access'),
(2, 'MANAGER', 'Store manager with elevated privileges'),
(3, 'EMPLOYEE', 'Regular employee with basic access');

-- Insérer des données dans USERS
INSERT INTO USERS (ID, USERNAME, PASSWORD, EMAIL, FULL_NAME, ENABLED) VALUES 
(1, 'admin', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'admin@example.com', 'Admin User', true),
(2, 'manager', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'manager@example.com', 'Manager User', true),
(3, 'employee', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'employee@example.com', 'Employee User', true);

-- Insérer des données dans USER_ROLES
INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insérer des données dans CATEGORIES
INSERT INTO CATEGORIES (ID, NAME, DESCRIPTION, CREATED_AT, UPDATED_AT) VALUES 
(1, 'Electronics', 'Electronic devices and accessories', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
(2, 'Clothing', 'Apparel and fashion items', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
(3, 'Home & Kitchen', 'Home appliances and kitchenware', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
(4, 'Books', 'Books and publications', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insérer des données dans SUPPLIER
INSERT INTO SUPPLIER (ID, NAME, EMAIL, ADDRESS) VALUES 
(1, 'Tech Supplies Inc.', 'contact@techsupplies.com', '123 Tech Street, Silicon Valley'),
(2, 'Fashion Wholesale Ltd.', 'info@fashionwholesale.com', '456 Fashion Avenue, New York'),
(3, 'Home Essentials Co.', 'support@homeessentials.com', '789 Home Boulevard, Chicago');

-- Insérer des données dans PRODUCTS
INSERT INTO PRODUCTS (ID, NAME, DESCRIPTION, SKU, PURCHASE_PRICE, SELLING_PRICE, STOCK_QUANTITY, MIN_STOCK_LEVEL, MAX_STOCK_LEVEL, SUPPLIER_ID, CREATION_DATE) VALUES 
(1, 'Laptop Pro X1', 'High-performance laptop for professionals', 'TECH-LAPTOP-001', 800.00, 1200.00, 50, 10, 100, 1, CURRENT_TIMESTAMP()),
(2, 'Smartphone Y2', 'Latest smartphone with advanced features', 'TECH-PHONE-002', 400.00, 699.99, 75, 15, 150, 1, CURRENT_TIMESTAMP()),
(3, 'Men''s Casual Shirt', 'Comfortable cotton casual shirt', 'CLOTH-SHIRT-001', 15.00, 29.99, 100, 20, 200, 2, CURRENT_TIMESTAMP()),
(4, 'Women''s Dress', 'Elegant evening dress', 'CLOTH-DRESS-002', 25.00, 49.99, 80, 15, 150, 2, CURRENT_TIMESTAMP()),
(5, 'Coffee Maker', 'Automatic coffee maker with timer', 'HOME-COFFEE-001', 45.00, 89.99, 30, 5, 50, 3, CURRENT_TIMESTAMP()),
(6, 'Novel: The Adventure', 'Bestselling adventure novel', 'BOOK-NOVEL-001', 8.00, 15.99, 120, 25, 250, 3, CURRENT_TIMESTAMP());

-- Insérer des données dans PRODUCT_CATEGORIES
INSERT INTO PRODUCT_CATEGORIES (PRODUCT_ID, CATEGORY_ID) VALUES 
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 4);
