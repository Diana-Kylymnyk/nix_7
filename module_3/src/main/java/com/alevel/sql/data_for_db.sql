INSERT INTO module_3_db.users (id, email, first_name, second_name, phone_number) VALUES (1, 'petr@gmail.com', 'Petr', 'Petrov', '0681002233');
INSERT INTO module_3_db.users (id, email, first_name, second_name, phone_number) VALUES (2, 'ivan@gmail.com', 'Ivan', 'Ivanov', '0981002333');

INSERT INTO module_3_db.accounts (id, balance, user_id) VALUES (1, 5800, 1);
INSERT INTO module_3_db.accounts (id, balance, user_id) VALUES (2, 2500, 1);

INSERT INTO module_3_db.expense_categories (id, name) VALUES (1, 'Cosmetics');
INSERT INTO module_3_db.expense_categories (id, name) VALUES (2, 'Clothes');
INSERT INTO module_3_db.expense_categories (id, name) VALUES (3, 'Products');
INSERT INTO module_3_db.expense_categories (id, name) VALUES (4, 'Trips');

INSERT INTO module_3_db.income_categories (id, name) VALUES (1, 'Salary');
INSERT INTO module_3_db.income_categories (id, name) VALUES (2, 'Prize');
INSERT INTO module_3_db.income_categories (id, name) VALUES (3, 'Present');