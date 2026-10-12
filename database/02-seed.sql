USE commercial_it_bootcamp;

INSERT INTO categories (id, name, slug, description) VALUES
(1, 'Computers', 'computers', 'Laptops, desktops and development machines'),
(2, 'Networking', 'networking', 'Home and small-office networking equipment'),
(3, 'Accessories', 'accessories', 'Keyboards, mice, docks and peripherals'),
(4, 'Books', 'books', 'Technology and architecture reference books'),
(5, 'Cloud Lab Credits', 'cloud-lab-credits', 'Imaginary credits used only to explain cloud consumption models')
ON DUPLICATE KEY UPDATE name = VALUES(name);

INSERT INTO products (id, category_id, sku, name, description, price, stock_quantity, active) VALUES
(1, 1, 'LAP-DEV-001', 'Developer Laptop 14', 'Sample developer workstation for training demonstrations.', 1499.00, 18, TRUE),
(2, 1, 'LAP-DEV-002', 'Developer Laptop 16', 'Larger sample workstation suitable for local containers and IDE work.', 1899.00, 12, TRUE),
(3, 2, 'NET-RTR-001', 'Wi-Fi 7 Lab Router', 'Sample router used to explain IP networking and DNS labs.', 249.00, 35, TRUE),
(4, 2, 'NET-SW-001', '8-Port Managed Switch', 'Sample managed switch for networking demonstrations.', 129.00, 42, TRUE),
(5, 3, 'ACC-KBD-001', 'Mechanical Keyboard', 'Wired mechanical keyboard.', 89.00, 70, TRUE),
(6, 3, 'ACC-MSE-001', 'Ergonomic Mouse', 'Ergonomic wireless mouse.', 69.00, 60, TRUE),
(7, 3, 'ACC-DCK-001', 'USB-C Developer Dock', 'Dock with display, ethernet and USB connectivity.', 179.00, 28, TRUE),
(8, 4, 'BOOK-ARCH-001', 'Enterprise Architecture Field Guide', 'Imaginary course reference title for architecture exercises.', 54.00, 100, TRUE),
(9, 4, 'BOOK-SEC-001', 'Identity and Access Management Field Guide', 'Imaginary course reference title for IAM exercises.', 49.00, 100, TRUE),
(10, 4, 'BOOK-DATA-001', 'Data Engineering Field Guide', 'Imaginary course reference title for data exercises.', 52.00, 100, TRUE),
(11, 5, 'CLD-LAB-100', 'Cloud Lab Credits - 100', 'Fictional training SKU; no real cloud credit is sold.', 25.00, 9999, TRUE),
(12, 5, 'CLD-LAB-500', 'Cloud Lab Credits - 500', 'Fictional training SKU; no real cloud credit is sold.', 100.00, 9999, TRUE)
ON DUPLICATE KEY UPDATE name = VALUES(name), price = VALUES(price), stock_quantity = VALUES(stock_quantity), active = VALUES(active);

INSERT INTO customers (id, email, first_name, last_name, phone) VALUES
(1, 'alex.johnson@example.test', 'Alex', 'Johnson', '+1-555-0101'),
(2, 'priya.shah@example.test', 'Priya', 'Shah', '+1-555-0102'),
(3, 'jordan.lee@example.test', 'Jordan', 'Lee', '+1-555-0103'),
(4, 'maria.garcia@example.test', 'Maria', 'Garcia', '+1-555-0104')
ON DUPLICATE KEY UPDATE first_name = VALUES(first_name), last_name = VALUES(last_name), phone = VALUES(phone);

INSERT INTO addresses (id, customer_id, address_type, line1, line2, city, state_province, postal_code, country_code, is_default) VALUES
(1, 1, 'SHIPPING', '100 Training Way', NULL, 'Atlanta', 'GA', '30301', 'US', TRUE),
(2, 2, 'SHIPPING', '200 Architecture Ave', 'Apt 12', 'Chicago', 'IL', '60601', 'US', TRUE),
(3, 3, 'SHIPPING', '300 Cloud Lane', NULL, 'Austin', 'TX', '78701', 'US', TRUE),
(4, 4, 'SHIPPING', '400 Security Blvd', NULL, 'Seattle', 'WA', '98101', 'US', TRUE)
ON DUPLICATE KEY UPDATE city = VALUES(city), state_province = VALUES(state_province);

INSERT INTO customer_orders (id, order_number, customer_id, status, subtotal, tax_amount, shipping_amount, total_amount, placed_at) VALUES
(1, 'ORD-DEMO-1001', 1, 'PAID', 178.00, 12.46, 0.00, 190.46, '2026-08-20 14:30:00'),
(2, 'ORD-DEMO-1002', 2, 'FULFILLING', 155.00, 10.85, 0.00, 165.85, '2026-08-22 10:15:00')
ON DUPLICATE KEY UPDATE status = VALUES(status), total_amount = VALUES(total_amount);

INSERT INTO order_items (id, order_id, product_id, quantity, unit_price, line_total) VALUES
(1, 1, 5, 2, 89.00, 178.00),
(2, 2, 9, 1, 49.00, 49.00),
(3, 2, 10, 1, 52.00, 52.00),
(4, 2, 8, 1, 54.00, 54.00)
ON DUPLICATE KEY UPDATE quantity = VALUES(quantity), line_total = VALUES(line_total);
