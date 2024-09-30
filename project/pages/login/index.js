import React, { useEffect, useState } from "react";
import styles from "../../styles/login.module.css";
import HeadComponent from "@/components/head";
import axios from "axios";
import { headers } from "@/next.config";
import { useRouter } from "next/router";
const Login = () => {
  const [hidden, setHidden] = useState(true);
  const[hiddenBtnLogin,setHiddenBtnLogin]=useState(true)
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  const router = useRouter();
  const handleLogin = () => {
    window.localStorage.clear()
    axios
      .post("https://next-java-restful-tropical-back-end.onrender.com/api/auth/v1/login", user)
      .then((response) => {
        console.log(response.data)
        window.localStorage.setItem("username", user.email);
        window.localStorage.setItem("token", response.data.accessToken);
        let typeOfUser=response.data.typeOfUser
        
        window.localStorage.setItem("typeOfUser",response.data.typeOfUser)
      
        if (typeOfUser=="ADMIN") {
          router.push("/clientes/lista");
        } else if (typeOfUser=="EMPRESA") {
          router.push("/destinos/lista");
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
        <div className={`container-fluid ${styles.login} `}>
          <div className={`row ${styles.input}`}>
            <div className="mb-3  col-10 col-md-5">
              <p hidden={hidden}>
                Login ou senha informados inválidos, digite novamente
              </p>
              <label htmlFor="nome" className="form-label  ">
                <p> Email</p>
              </label>
              <input
                type="text"
                placeholder="Insira seu email"
                className="form-control text-center "
                id="nome"
                name="email"
                value={user.email}
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
                className={`form-control  text-center ${styles.input_password}`}
                id="senha"
                name="password"
                value={user.password}
                onChange={handleInputChange}
                required=""
              />
              <div className={`${styles.div_buttons}`}>
                <button
                  onClick={handleLogin}
                  type="button"
                  className={`${styles.btnLogin} btn btn-primary `}
                >
                  Login
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
