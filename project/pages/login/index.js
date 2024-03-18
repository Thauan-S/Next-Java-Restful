import React, { useState } from "react";
import styles from "../../styles/login.module.css";
import HeadComponent from "@/components/head";
import axios from "axios";
import { headers } from "@/next.config";
import { useRouter } from "next/router";
const Login = () => {
  const [cliente, setCliente] = useState({
    nome: "",
    senha: "",
  });
  const router=useRouter()
  const handleLogin=()=>{
    axios
    .post("http://localhost:8080/api/authenticate/v1", {
      nome: cliente.nome,
      senha: cliente.senha,
    }, {
      headers: {
        'Authorization': `Basic ${btoa(`${cliente.nome}:${cliente.senha}`)}`,
        'Content-Type': 'application/json', // Adicione o tipo de conteúdo se necessário
        ...headers, 
      },
    })
    .then((response) => {
     // router.push("/login");
     window.localStorage.setItem('token',response.data)
      console.log(response)
    })
    .catch((error) => {
      console.error("Erro ao fazer login: " + error);
    });
  }
  const handleInputChange = (e) => {
    setCliente({...cliente ,[e.target.name]:e.target.value})
  };
  console.log(cliente)
  return (
    <>
      <HeadComponent title={"Tropical | Login"} />
      <div className={`${styles.degrade}`}>
        <div className={` container-fluid ${styles.login} text-center align-items`}>
          <div className="row">
            <div className="mb-3  col-10 col-md-5 ">
              <label htmlFor="nome" className="form-label ">
                Nome
              </label>
              <input
                type="text"
                placeholder="Insira seu nome"
                className="form-control text-center "
                id="nome"
                name="nome"
                value={cliente.nome}
                onChange={handleInputChange}
                aria-describedby="emailHelp"
                required=""
              />
            </div>
          </div>
          <div className="row">
            <div className="col-10 col-md-5">
              <label htmlFor="senha" className="form-label ">
                Senha
              </label>
              <input
                type="password"
                placeholder="Insira sua senha"
                className="form-control  text-center"
                id="senha"
                name="senha"
                value={cliente.senha}
                onChange={handleInputChange}
                required=""
              />
            </div>
          </div>
          <button onClick={handleLogin} type="button"  className={`${styles.btn} btn btn-primary `}>
               Login
          </button>

        </div>
      </div>
    </>
  );
};

export default Login;
