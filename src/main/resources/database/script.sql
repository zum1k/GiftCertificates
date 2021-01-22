CREATE DEFINER=`root`@`localhost` PROCEDURE `generate_data`()
BEGIN
DECLARE i INT DEFAULT 1;
   WHILE i < 1001 DO
      INSERT INTO tags(name, create_date, last_update_date) VALUES
      (CONCAT('name',convert(i, char(50))),'2020-11-15T21:31:45.317+03:00','2020-11-15T21:31:45.317+03:00');
      SET i = i + 1;
   END WHILE;
   SET i = 0;
   WHILE i < 10001 DO
     INSERT INTO gifts(name, description, price, create_date, last_update_date, duration) VALUES
     (CONCAT('name',convert(i, char(50))), CONCAT('description',convert(i, char(50))), rand()*(1800-10)+10, '2020-11-15T21:31:45.317+03:00', '2020-11-15T21:31:45.317+03:00', rand()*(180-1)+1);
     SET i = i + 1;
   END WHILE;
   SET i = 0;
   WHILE i < 1001 DO
     INSERT INTO users(first_name, last_name, email, password, create_date, last_update_date) VALUES
     (CONCAT('firstname',convert(i, char(50))), CONCAT('lastname',convert(i, char(50))), CONCAT('user',convert(i, char(50)), '@gmail.com'), CONCAT('password',convert(i, char(50))),'2020-11-15T21:31:45.317+03:00','2020-11-15T21:31:45.317+03:00' );
     SET i = i + 1;
     END WHILE;
     SET i = 0;
   WHILE i < 10001 DO
     INSERT INTO gifts_tags(gift_id, tag_id) VALUES
     (i, RAND()*(150-1)+1), (i, RAND()*(300-151)+151),(i, RAND()*(450-301)+301), (i, RAND()*(600-451)+451),(i, RAND()*(750-601)+601), (i, RAND()*(1000-751)+751);
     SET i = i + 1;
   END WHILE;
   SET i = 0;
   WHILE i < 1001 DO
     INSERT INTO orders(price, purchase_date, user_id, create_date, last_update_date) VALUES
     (RAND()*(5800-200)+200, '2020-11-15T21:31:45.317+03:00', i, '2020-11-15T21:31:45.317+03:00','2020-11-15T21:31:45.317+03:00'),
     (RAND()*(5800-200)+200, '2020-11-15T21:31:45.317+03:00', i, '2020-11-15T21:31:45.317+03:00','2020-11-15T21:31:45.317+03:00'),
     (RAND()*(5800-200)+200, '2020-11-15T21:31:45.317+03:00', i, '2020-11-15T21:31:45.317+03:00','2020-11-15T21:31:45.317+03:00'),
     (RAND()*(5800-200)+200, '2020-11-15T21:31:45.317+03:00', i, '2020-11-15T21:31:45.317+03:00','2020-11-15T21:31:45.317+03:00');
     SET i = i + 1;
   END WHILE;
   SET i = 0;
     WHILE i < 4001 DO
     INSERT INTO orders_gifts(order_id, gift_id)
     VALUES(i, RAND()*(2500-1)+1), (i, RAND()*(5000-2501)+2501), (i, RAND()*(7500-5001)+5001), (i, RAND()*(10000-7501)+7501);
     SET i = i + 1;
  END WHILE;
END