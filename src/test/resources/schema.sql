CREATE TABLE album (
    `album_id` int(11) NOT NULL AUTO_INCREMENT,
    `member_id` varchar(10) NOT NULL,
    `album_name` varchar(50) DEFAULT NULL,
    `album_desc` varchar(100) DEFAULT NULL,
    `creat_date` date DEFAULT NULL,
    PRIMARY KEY (`album_id`)
);

CREATE TABLE album_photo (
    `pid` int(11) NOT NULL AUTO_INCREMENT,
    `album_id` varchar(10) NOT NULL,
    `file_name` varchar(100) DEFAULT NULL,
    `creat_date` date DEFAULT NULL,
    PRIMARY KEY (`pid`)
);

CREATE TABLE member (
    `member_id` int(5) NOT NULL AUTO_INCREMENT,
    `email` varchar(100) DEFAULT NULL,
    `pwd` varchar(100) DEFAULT NULL,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`member_id`)
)
