-- ip 139.155.16.227  账号 root 密码qqq123
create database `textbook_manager`;
use `textbook_manager`;

create table `history`
(`history_no` integer not null auto_increment,
`start_time` datetime default now(),
`order_no` integer,
primary key (`history_no`)) engine=InnoDB;

create table `message`
(`mess_no` integer not null auto_increment,
`content` varchar(255),
`start_time` datetime default now(),
`user_id` integer,
primary key (`mess_no`)) engine=InnoDB;

create table `order` (`order_no` integer not null auto_increment,
`book_num` integer,
`book_values` double precision,
`start_time` datetime default now(),
`book_no` integer, `user_id` integer,
primary key (`order_no`)) engine=InnoDB;

create table `shopping_cart`
(`shopping_cart_no` integer not null auto_increment,
`book_num` integer,
`book_values` double precision,
`start_time` datetime default now(),
`book_no` integer,
`user_id` integer,
primary key (`shopping_cart_no`)) engine=InnoDB;

create table `text_book`
(`book_no` integer not null auto_increment,
`author` varchar(255),
`book_name` varchar(255),
`book_pic` varchar(255),
`book_price` double precision,
`totalnum` integer,
primary key (`book_no`)) engine=InnoDB;

create table `user`
(`user_id` integer not null auto_increment,
`is_admin` bit,
`user_icon_path` varchar(255),
`user_name` varchar(255),
`user_password` varchar(255),
primary key (`user_id`)) engine=InnoDB;

insert into `user`
(`is_admin`,`user_icon_path`,`user_id`,`user_name`,`user_password`)values
(false,'my_avator.png',20160750,'李金洲','1234'),
(false,'my_avator.png',20160751,'李玮光','1234'),
(false,'my_avator.png',20160752,'韩青杨','1234'),
(false,'my_avator.png',20160753,'吴仁珑','1234'),
(false,'my_avator.png',20160754,'杨壹麟','1234'),
(false,'my_avator.png',20160755,'杨永旭','1234');

-- 添加管理员用户
insert into `user`
(`is_admin`,`user_icon_path`,`user_id`,`user_name`,`user_password`)values
(true,'my_avator.png',20190001,'李老师','1234'),
(true,'my_avator.png',20190002,'王老师','1234'),
(true,'my_avator.png',20190003,'张老师','1234'),
(true,'my_avator.png',20190004,'韩老师','1234'),
(true,'my_avator.png',20190005,'杨老师','1234'),
(true,'my_avator.png',20190006,'谢老师','1234');

alter table `history`
add constraint FKavet78bxwd5k9q7vb668x96co foreign key (`order_no`) references `order` (`order_no`);

alter table `message` add constraint FKnebwitbhvl9nq6mqsdlmb0v75 foreign key (`user_id`) references `user` (`user_id`);

alter table `order` add constraint FK2c189aci3ol5nt90ae9cl1yjp foreign key (`book_no`) references `text_book` (`book_no`);

alter table `order` add constraint FKrcaf946w0bh6qj0ljiw3pwpnu foreign key (`user_id`) references `user` (`user_id`);

alter table `shopping_cart` add constraint FK92wnnk8b8blrpwwiacxcqls40 foreign key (`book_no`) references `text_book` (`book_no`);

alter table `shopping_cart` add constraint FKlf4gsfxg2in4u7qyx285s45y5 foreign key (`user_id`) references `user` (`user_id`);

DROP TABLE IF EXISTS `user`;



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