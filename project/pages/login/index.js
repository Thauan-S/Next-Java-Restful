import React, { useEffect, useState } from "react";
import styles from "../../styles/login.module.css";
import HeadComponent from "@/components/head";
import axios from "axios";
import { headers } from "@/next.config";
import { useRouter } from "next/router";
const Login = () => {
  const [hidden, setHidden] = useState(true);
  const [user, setUser] = useState({
    username: "",
    password: "",
  });
  const router = useRouter();
  const handleLogin = () => {
    axios
      .post("http://localhost/api/auth/v1/login", user)
      .then((response) => {
        console.log("username", user.username);
        window.localStorage.setItem("username", user.username);
        window.localStorage.setItem("token", response.data.accessToken);
        if (user.username == "admin") {
          router.push("/clientes/lista");
        } else {
          router.push("/");
        }
      })
      .catch((error) => {
        setHidden(!hidden);
        console.error("Erro ao fazer login: " + error);
      });
  };
  const handleInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
    setHidden(true);
  };

  return (
    <>
      <HeadComponent title={"Tropical | Login"} />
      <div className={`${styles.degrade}`}>
        <div className={`container-fluid ${styles.login}  text-center`}>
          <div className={`row ${styles.input}`}>
            <div className="mb-3  col-10 col-md-5">
              <p hidden={hidden}>
                Login ou senha informados inválidos, digite novamente
              </p>
              <label htmlFor="nome" className="form-label  ">
                <p> Nome</p>
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
          <div className={`row ${styles.input}`}>
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
              <button
                onClick={handleLogin}
                type="button"
                className={`${styles.btn} btn btn-primary `}
              >
                Login
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
