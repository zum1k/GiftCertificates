CREATE TABLE tags
(
    tag_id INT AUTO_INCREMENT PRIMARY KEY,
    name   varchar(32) NOT NULL UNIQUE,
    create_date      DATETIME   NOT NULL,
    last_update_date DATETIME   NOT NULL
);

CREATE TABLE gifts
(
    gift_id          INT AUTO_INCREMENT PRIMARY KEY,
    name             varchar(32)        NOT NULL,
    description      TEXT               NOT NULL,
    price            decimal(12, 2)     NOT NULL,
    create_date      DATETIME           NOT NULL,
    last_update_date DATETIME           NOT NULL,
    duration         integer            NOT NULL
);

CREATE TABLE users
(
    user_id        INT AUTO_INCREMENT PRIMARY KEY,
    first_name       VARCHAR(45)    NOT NULL,
    last_name        VARCHAR(45)    NOT NULL,
    email            VARCHAR(45)    NOT NULL,
    password         VARCHAR(45)    NOT NULL,
    create_date      DATETIME       NOT NULL,
    last_update_date DATETIME       NOT NULL
);
CREATE TABLE orders
(
    order_id         INT AUTO_INCREMENT PRIMARY KEY,
    price            decimal(12, 2) NOT NULL,
    purchase_date    DATETIME       NOT NULL,
    user_id          INT            NOT NULL,
    create_date      DATETIME       NOT NULL,
    last_update_date DATETIME       NOT NULL,
    CONSTRAINT FK_orders_USER FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE gifts_tags
(
    gift_id INT NOT NULL,
    tag_id  INT NOT NULL,
    CONSTRAINT FK_gifts_tags_TAG FOREIGN KEY (tag_id)
        REFERENCES tags (tag_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_gifts_tags_GIFT FOREIGN KEY (gift_id)
        REFERENCES gifts (gift_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE orders_gifts
(
    order_id INT NOT NULL,
    gift_id  INT NOT NULL,
    CONSTRAINT FK_orders_gifts_ORDER FOREIGN KEY (order_id)
        REFERENCES orders (order_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_orders_gifts_GIFT FOREIGN KEY (gift_id)
        REFERENCES gifts (gift_id) ON DELETE CASCADE ON UPDATE CASCADE
);


