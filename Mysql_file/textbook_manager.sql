-- ip 139.155.16.227  账号 root 密码qqq123
create database `textbook_manager`;

use `textbook_manager`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_no` bigint(20) NOT NULL auto_increment,
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

-- 添加管理员用户
insert into `user`
values(7,'20190001',true,'1234','my_avator.png','李老师'),
(8,'20190002',true,'1234','my_avator.png','王老师'),
(9,'20190003',true,'1234','my_avator.png','张老师'),
(10,'20190004',true,'1234','my_avator.png','韩老师'),
(11,'20190005',true,'1234','my_avator.png','杨老师'),
(12,'20190006',true,'1234','my_avator.png','谢老师');

insert into `user`(`user_id`,`is_admin`,`user_password`,`user_icon_path`,`user_name`)
values('20190007',true,'1234','my_avator.png','李老师');

create table `text_book`
(
`book_no` integer not null,
`author` varchar(255),
`book_name` varchar(255),
`book_pic` varchar(255),
`book_price` double precision,
`totalnum` integer default 0,
primary key (`book_nso`)
) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

update `text_book` set `text_book`.`totalnum` = 100 where `text_book`.`book_no`=1;

insert into `text_book` values
(1,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(2,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(3,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(4,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(5,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(6,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(7,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(8,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(9,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(10,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(11,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(12,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(13,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(14,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(15,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(16,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(17,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(18,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(19,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(20,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(21,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(22,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(23,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(24,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(25,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(26,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(27,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(28,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(29,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(30,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(31,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(32,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(33,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(34,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(35,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(36,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(37,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(38,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(39,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(40,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(41,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(42,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(43,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(44,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(45,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(46,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(47,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(48,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(49,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(50,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(51,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(52,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(53,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(54,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(55,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(56,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(57,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(58,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(59,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(60,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(61,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(62,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(63,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(64,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(65,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(66,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(67,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(68,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(69,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(70,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(71,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(72,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(73,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(74,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(75,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(76,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(77,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(78,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(79,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(80,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(81,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(82,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(83,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(84,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(85,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(86,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(87,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(88,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(89,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(90,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(91,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(92,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(93,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(94,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(95,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100),
(96,'王学惠','国际结算教程','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/077447-01.jpg',39.0,100),
(97,'刘洪丹、张兰勇、孙蓉','物联网技术与系统设计','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/082560-01.jpg',79.0,100),
(98,'张学昌、裴磊，陆俊杰、张炜、施岳定','AutoCAD机械图样典型范例与实训教程（第3版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/083125-01.jpg',39.0,100),
(99,'贾立新、倪洪杰、王辛刚','电子系统设计与实践（第4版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/084436-01.jpg',59.0,100),
(100,'朱桂明 任丽 杨文 闵悦昕 胡燕玲 胡竟男 何亚雯 李莉 王善德','基础会计（第二版）','http://www.tup.tsinghua.edu.cn/upload/bigbookimg/085109-01.jpg',49.8,100);


-- 消息表

create table `message`
 (`mess_no` integer not null auto_increment,
 `content` varchar(255),
 `start_time` timestamp default now(),
 `user_no` integer,
 primary key (`mess_no`))
 engine=InnoDB;
 
 insert into `message`(`content`,`user_no`) values ('新消息',20160751); 
 
DELETE FROM `message`
WHERE `mess_no`=1;
 
 insert into `message` values
 (1,'新消息','2002-11-14 09:40:09',20160750),
  (2,'新消息','2002-11-14 09:40:09',20160750),
   (3,'新消息','2002-11-14 09:40:09',20160750),
    (4,'新消息','2002-11-14 09:40:09',20160750),
     (5,'新消息','2002-11-14 09:40:09',20160750),
      (6,'新消息','2002-11-14 09:40:09',20160750),
       (7,'新消息','2002-11-14 09:40:09',20160750),
        (8,'新消息','2002-11-14 09:40:09',20160750),
         (9,'新消息','2002-11-14 09:40:09',20160750),
          (10,'新消息','2002-11-14 09:40:09',20160750),
           (11,'新消息','2002-11-14 09:40:09',20160750),
            (12,'新消息','2002-11-14 09:40:09',20160750),
             (13,'新消息','2002-11-14 09:40:09',20160750),
              (14,'新消息','2002-11-14 09:40:09',20160750),
               (15,'新消息','2002-11-14 09:40:09',20160750),
                (16,'新消息','2002-11-14 09:40:09',20160750),
                 (17,'新消息','2002-11-14 09:40:09',20160750);
				
 create table `shopping_cart`
 (`shopping_cart_no` integer not null auto_increment,
 `book_no` integer,
 `book_num` integer,
 `book_values` double precision,
 `start_time` datetime default now(),
 `user_no` integer,
 primary key (`shopping_cart_no`)) engine=InnoDB;

SET time_zone = '+8:00';

  insert into `shopping_cart`(`book_no`,`book_num`,`book_values`,`user_no`) values
 (1,1,100.0,20160750);
 
 create table `history`
 (`history_no` integer not null auto_increment,
 `order_no` integer,
 `start_time` datetime default now(),
 primary key (`history_no`))
 engine=InnoDB;
 
 set global time_zone = '+8:00';
 
 insert into `history`(`order_no`) values
(1);
 
 create table `order`
 (`order_no` integer not null auto_increment,
 `book_no` integer,
 `book_num` integer,
 `book_values` double precision,
`start_time` datetime default now(),
`user_no` integer,
 primary key (`order_no`)) engine=InnoDB;
 
insert into `order`(`book_no`,`book_num`,`book_values`,`user_no`) values
(1,1,100.0,20160750);