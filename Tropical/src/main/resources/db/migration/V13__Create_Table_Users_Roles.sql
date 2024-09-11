CREATE TABLE tb_users_roles (
  user_id UUID NOT NULL,
  role_id bigint NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT FK_tb_users_roles_user_id FOREIGN KEY (user_id) REFERENCES tb_users (user_id),
  CONSTRAINT FK_tb_users_roles_role_id FOREIGN KEY (role_id) REFERENCES tb_roles (role_id)
);
