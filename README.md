
# <div align="center" >Tropical Agência de viagens</div>
## Autor 
https://www.linkedin.com/in/thauan-devjr/

## Descrição 

### Este projeto foi desenvolvido com fins didáticos, abordando tanto o Front End quanto o Back End. Foi implementada autenticação e autorização de usuários, assegurando que cada tipo de usuário tenha um papel específico. Além disso, utilizei o RabbitMQ para mensageria assíncrona, e, sempre que um novo cliente é cadastrado, ele recebe um e-mail de boas-vindas.

<details>
    <summary><b>Usuários</b></summary>
    <img src="./database/Users.png" alt="Users">
</details>

<details>
    <summary><b>Pacotes de viagem</b></summary>
    <img src="./database/Travel packages.png" alt="Travel Packages">
</details>

<details>
    <summary><b>Reservas</b></summary>
    <img src="./database/Reserves.png" alt="Reserves">
</details>
<details>
    <summary><b>Clientes</b></summary>
    <img src="./database/Clients.png" alt="Clients">
</details>
<details>
    <summary><b>Empresas</b></summary>
    <img src="./database/Enterprise.png" alt="Enterprises">
</details>

# Diagramas
<details>
    <summary><b>Diagrama de classes</b></summary>
    <img src="./database/Diagrama de classes.png" alt="Categorias">
    <summary><b>Diagrama de classes Ms email</b></summary>
    <img src="./database/Class Diagram Ms Email.png" alt="Categorias">
</details>
<details>
    <summary><b>Diagrama de entidade e relacionamento</b></summary>
    <img src="./database/DER.png" alt="Categorias">
    <summary><b>Diagrama de entidade e relacionamento Ms Email</b></summary>
    <img src="./database/DER MSemail .png" alt="Categorias">
</details>


## Criação do banco de dados
### Antes de tudo, é necessario criar o banco de dados:

create database tropical;

create database email;

### Execute os projetos para criar as tabelas
### senha dos usuários: 123
## Documentação swagger
http://localhost/swagger-ui/index.html

### Docker images 
Front:
[![Docker Hub Repo](https://img.shields.io/docker/pulls/thauans/email.svg)](https://hub.docker.com/repository/docker/thauans/project)
tropical api:
[![Docker Hub Repo](https://img.shields.io/docker/pulls/thauans/tropical.svg)](https://hub.docker.com/repository/docker/thauans/tropical)

email ms:
[![Docker Hub Repo](https://img.shields.io/docker/pulls/thauans/email.svg)](https://hub.docker.com/repository/docker/thauans/email)

