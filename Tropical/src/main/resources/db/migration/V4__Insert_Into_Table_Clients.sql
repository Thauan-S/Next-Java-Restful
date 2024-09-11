INSERT INTO tb_clients (name, phone, birthday, zip_code, user_id)
VALUES
   ('thauan', '73988896878', '2002-11-17', '12345', (SELECT user_id FROM tb_users WHERE email = 'thauan')),
   ('thau', '11111111', '2002-11-17', '123456', (SELECT user_id FROM tb_users WHERE email = 'thau'));
