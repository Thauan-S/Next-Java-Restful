INSERT INTO tb_companies (name, cnpj, address, user_id)
VALUES
   ('Empresa', '12345678000195', 'Rua A: 123', (SELECT user_id FROM tb_users WHERE email = 'empresa')),
   ('Empresa2', '11111111111', 'Rua B: 123', (SELECT user_id FROM tb_users WHERE email = 'empresa2'));

