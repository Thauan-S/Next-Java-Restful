INSERT INTO tb_companies (name,cnpj,address,user_id) VALUES('Empresa', '12345678000195' ,'Rua A: 123',  (select user_id from tb_users where email ="empresa")),
    ( 'Empresa2', '11111111111','Rua B: 123', (select user_id from tb_users where email ="empresa2"));
