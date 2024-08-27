CREATE TABLE `tb_users_roles` (
  `user_id` binary(16) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj5qged12p22eloqw5g4f9hm2e` (`role_id`),
  CONSTRAINT `FK5xc4yvfrjcy8bl01kq3crp8pg` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`user_id`),
  CONSTRAINT `FKj5qged12p22eloqw5g4f9hm2e` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`role_id`)
);