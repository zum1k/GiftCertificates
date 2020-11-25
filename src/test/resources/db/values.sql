INSERT INTO tags(name) values ('name1'), ('name2'), ('name3'), ('name4'), ('name5'),
('name6'), ('name7'), ('name8'), ('name9'), ('name10');

INSERT INTO gifts(name, description, price, create_date, last_update_date, duration)
VALUES ('name1', 'description1', 39.99, '2020-11-15T21:31:45.317+03:00', '2020-11-15T21:31:45.317+03:00', 8);

INSERT INTO gifts(name, description, price, create_date, last_update_date, duration)
VALUES ('name2', 'description2', 34.99, '2020-11-15T21:31:45.317+03:00', '2020-11-15T21:31:45.317+03:00', 8);

INSERT INTO gifts(name, description, price, create_date, last_update_date, duration)
VALUES ('name3', 'description3', 49.99, '2020-11-15T21:31:45.317+03:00', '2020-11-15T21:31:45.317+03:00', 6);

INSERT INTO gifts(name, description, price, create_date, last_update_date, duration)
VALUES ('name4', 'description4', 59.99, '2020-11-15T21:31:45.317+03:00', '2020-11-15T21:31:45.317+03:00', 2);

INSERT INTO gifts(name, description, price, create_date, last_update_date, duration)
VALUES ('name5', 'description7', 14.99, '2020-11-15T21:31:45.317+03:00', '2020-11-15T21:31:45.317+03:00', 26);


INSERT INTO gift_certificate_tag(gift, tag)
VALUES (1, 1), (1, 2), (2, 2), (2, 4), (2, 6),(3, 4), (3, 6) ,(4, 1), (5, 2), (5, 4), (5, 6);