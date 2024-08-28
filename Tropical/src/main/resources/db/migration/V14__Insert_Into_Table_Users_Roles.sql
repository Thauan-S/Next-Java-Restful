INSERT INTO `tb_users_roles` VALUES ((select user_id from tb_users where username="admin" ),1),
((select user_id from tb_users where username="thau" ),2),
((select user_id from tb_users where username="thauan" ),2),
((select user_id from tb_users where username="empresa" ),3),
((select user_id from tb_users where username="empresa2" ),3);