create database `textbook_manager`;
use `textbook_manager`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_no` bigint(20) NOT NULL,
  `user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_admin` boolean default false,
  `user_password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_icon_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into `user`
values(1,'20160750',false,'1234','my_avator.png','李金洲'),
(2,'20160751',false,'1234','my_avator.png','李玮光'),
(3,'20160752',false,'1234','my_avator.png','韩青杨'),
(4,'20160753',false,'1234','my_avator.png','吴仁珑'),
(5,'20160754',false,'1234','my_avator.png','杨壹麟'),
(6,'20160755',false,'1234','my_avator.png','杨永旭');


create table `text_book`
(
`book_no` integer not null,
`author` varchar(255),
`book_name` varchar(255),
`book_pic` varchar(255),
`book_price` double precision,
primary key (`book_no`)
) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
