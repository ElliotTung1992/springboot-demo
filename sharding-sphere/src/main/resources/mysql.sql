

CREATE TABLE `course_0` (
`cid` bigint(20) NOT NULL,
`cname` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
`user_id` bigint(20) NOT NULL,
`status` varchar(10) COLLATE utf8_bin NOT NULL,
PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `course_1` (
`cid` bigint(20) NOT NULL,
`cname` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
`user_id` bigint(20) NOT NULL,
`status` varchar(10) COLLATE utf8_bin NOT NULL,
PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `t_user` (
  `user_id` bigint NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `age` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;

CREATE TABLE `t_dict` (
  `dict_id` bigint NOT NULL,
  `dict_name` varchar(100) NOT NULL,
  `dict_value` varchar(100) NOT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB;