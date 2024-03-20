import React, { useState } from "react";
import styles from "../../styles/login.module.css";
import HeadComponent from "@/components/head";
import axios from "axios";
import { headers } from "@/next.config";
import { useRouter } from "next/router";
const Login = () => {
  const [user, setUser] = useState({
    username: "",
    password: "",
  });
  const router=useRouter()
  const handleLogin= () => {
    axios
    .post("http://localhost:80/auth/signin",user )
    .then((response) => {
     
     window.localStorage.setItem('username',user.username)
     window.localStorage.setItem('token',response.data.accessToken)
     router.push("/clientes/lista");
      console.log(response)
    })
    .catch((error) => {
      console.error("Erro ao fazer login: " + error);
    });
  }
  const handleInputChange = (e) => {
    setUser({...user ,[e.target.name]:e.target.value})
  };
  console.log(user)
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
                name="username"
                value={user.username}
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
                name="password"
                value={user.password}
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
