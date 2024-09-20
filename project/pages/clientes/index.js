import React, { useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import HeadComponent from "@/components/head";

const Cadastro = () => {
  const [cliente, setCliente] = useState({
    name: "",
    phone: "",
    birthday: "",
    zipCode: "",
    user:{
      email:"",
      password:""
    }
    
  });
 
  const router = useRouter();
  const handleInputChange = (e) => {
    
    if(e.target.name==="email"){
      setCliente(cliente.user.email=e.target.value)
    }else if(e.target.name==="password"){
      setCliente((cliente.user.password=e.target.value))
    }
    setCliente({ ...cliente, [e.target.name]: e.target.value });
    
  };

  const handleAddClient = () => {
    axios
      .post("https://next-java-restful-tropical-back-end.onrender.com/register/client",cliente)
      .then((response) => {
        router.push("/login");
      })
      .catch((error) => {
        console.error("erro ao cadastrar Usuário " + error);
      });
  };
  return (
    <>
    <main>
      <HeadComponent title={"Tropical | Cadastro-Cliente"} />
      <div className="container-fluid">
        <div className="row">
          <div className=" col col-md-4">
            <label htmlFor="nome" className="form-label">
              Nome
            </label>
            <input
              type="text"
              placeholder="Insira seu nome"
              className="form-control"
              id="name"
              name="name"
              value={cliente.name}
              onChange={handleInputChange}
              aria-describedby="emailHelp"
              required=""
            />
          </div>
          <div className="mb-3 col-sm-4">
            <label htmlFor="telefone" className="form-label">
              Telefone
            </label>
            <input
              type="tel"
              placeholder="(00) 0000-0000"
              className="form-control"
              id="phone"
              name="phone"
              value={cliente.phone}
              onChange={handleInputChange}
              required=""
            />
          </div>
          <div className="mb-3 row">
            <label htmlFor="birthday" className="form-label">
              Data de Nascimento
            </label>
            <div className="col-md-2 col-sm-4">
              <input
                type="date"
                placeholder="Insira seu telefone"
                className="form-control"
                id="birthday"
                name="birthday"
                value={cliente.birthday}
                onChange={handleInputChange}
                required=""
              />
            </div>
          </div>
        </div>
        <div className="mb-1 row">
          <div className="mb-2  col-lg-2 col-sm-3">
            <label htmlFor="cep" className="form-label">
              Cep
            </label>
            <input
              type="text"
              pattern="[0-9]{5}-[0-9]{3}"
              placeholder="Insira seu Cep"
              className="form-control"
              id="zipCode"
              name="zipCode"
              value={cliente.zipCode}
              onChange={handleInputChange}
              required=""
            />
          </div>
        </div>
        <div className="mb-3 row">
          <div className="col-10 col-md-3">
            <label htmlFor="email" className="form-label">
              Email
            </label>
            <input
              id="email"
              className="form-control"
              type="email"
              placeholder="seuemail@exemplo.com"
              name="email"
              value={cliente.user.email}
              onChange={handleInputChange}
            />
            <div id="emailHelp" className="form-text">
              Nunca compartilharemos seu email
            </div>
          </div>
          <div>
            <label htmlFor="senha" className="form-label">
              Senha
            </label>
            <div className="col-6 col-md-2">
              <input
                id="senha"
                className="form-control "
                type="text"
                placeholder="insira sua senha"
                name="password"
                value={cliente.user.password}
                onChange={handleInputChange}
                aria-describedby="senha"
                required
              />
            </div>
          </div>
        </div>
        <button
          type="button"
          onClick={handleAddClient}
          data-bs-toggle="collapse"
          data-bs-target="#collapseExample"
          aria-expanded="false"
          aria-controls="collapseExample"
          className="btn btn-primary mt-2"
        >
          Cadastrar
        </button>
        <div className="collapse mt-1" id="collapseExample">
          <div className="card alert alert-success text-center card-body">
            Dados enviados com sucesso
          </div>
        </div>
      </div>
      </main>
    </>
  );
};

export default Cadastro;
