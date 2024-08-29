INSERT INTO `tb_users_roles` VALUES ((select user_id from tb_users where email="admin" ),1),
((select user_id from tb_users where email="thau" ),2),
((select user_id from tb_users where email="thauan" ),2),
((select user_id from tb_users where email="empresa" ),3),
((select user_id from tb_users where email="empresa2" ),3);