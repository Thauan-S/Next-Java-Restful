CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE tb_users (
   user_id UUID NOT NULL DEFAULT uuid_generate_v4(),
   email varchar(255) DEFAULT NULL,
   password varchar(255) DEFAULT NULL,
   PRIMARY KEY (user_id)
);
