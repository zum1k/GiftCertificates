DROP TABLE IF EXISTS gifts;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS gift_certificate_tag;

CREATE TABLE tags
(
    tag_id INT AUTO_INCREMENT PRIMARY KEY,
    name   varchar(32) NOT NULL UNIQUE
);

CREATE TABLE gifts
(
    gifts_id        INT AUTO_INCREMENT PRIMARY KEY,
    name             varchar(32)   NOT NULL,
    description      TEXT          NOT NULL,
    price            decimal(9, 2) NOT NULL,
    create_date      varchar(45)   NOT NULL,
    last_update_date VARCHAR(45)   NOT NULL,
    duration         integer       NOT NULL

);
CREATE TABLE gift_certificate_tag
(
    gift INT NOT NULL,
    tag  INT NOT NULL,
    CONSTRAINT FK_gift_certificate_tag_tagId FOREIGN KEY (tag)
        REFERENCES tags (tag_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_gift_certificate_tag_gift_certificate_id FOREIGN KEY (gift)
        REFERENCES gifts (gifts_id) ON DELETE CASCADE ON UPDATE CASCADE
);


