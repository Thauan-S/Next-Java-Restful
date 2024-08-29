CREATE TABLE `tb_users` (
   `user_id` binary(16) NOT NULL,
   `email` varchar(255) DEFAULT NULL,
   `password` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`user_id`)
 )
