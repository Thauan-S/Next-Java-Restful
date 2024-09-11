INSERT INTO tb_users_roles (user_id, role_id)
VALUES
   ((SELECT user_id FROM tb_users WHERE email = 'admin'), 1),
   ((SELECT user_id FROM tb_users WHERE email = 'thau'), 2),
   ((SELECT user_id FROM tb_users WHERE email = 'thauan'), 2),
   ((SELECT user_id FROM tb_users WHERE email = 'empresa'), 3),
   ((SELECT user_id FROM tb_users WHERE email = 'empresa2'), 3);
