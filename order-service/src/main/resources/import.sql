INSERT INTO orders (customer_id, status, order_date) VALUES (2, 'COMPLETED', '2025-01-12');
INSERT INTO orders (customer_id, status, order_date) VALUES (1, 'PENDING', '2025-01-11');
INSERT INTO orders (customer_id, status, order_date) VALUES (4, 'COMPLETED', '2025-01-11');
INSERT INTO orders (customer_id, status, order_date) VALUES (3, 'CANCELED', '2025-01-09');
INSERT INTO orders (customer_id, status, order_date) VALUES (2, 'COMPLETED', '2025-01-05');

INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (1, 16, 2, 25.5);
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (3, 7, 5, 10);
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (3, 4, 1, 56.35);
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (5, 7, 4, 6.7);
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (2, 9, 1, 30);
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (1, 2, 3, 9);
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (3, 10, 2, 24.6);
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (4, 6, 3, 14.75);