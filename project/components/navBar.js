import React, { useEffect, useState,useContext } from "react";
import Link from "next/link";
import styles from "../styles/navBar.module.css";
import { useRouter } from "next/router";
import Image from "next/image";

import { GlobalContext } from "@/contexts/appContext";
const NavBar = () => {
  const[typeOfUser,setTypeOfUser]=useState("")
  const [hidden, setHidden] = useState(true);
  const [hideManagementButton, setHideManagementButton] = useState(true);
  const [hideMyReservesButton, setHideMyReservesButton] = useState(true);
  const {globalState:{type}}=useContext(GlobalContext)
  const router = useRouter();

  
  
  useEffect(() => {
    setTypeOfUser(window.localStorage.getItem("typeOfUser"))
   
    if (typeOfUser) {
      setHidden(false);
      if (typeOfUser === "ADMIN" || typeOfUser=="EMPRESA") {
        setHideManagementButton(false);
        setHideMyReservesButton(true);
        setHidden(false);
      } else {
        setHideMyReservesButton(false); 
        setHidden(true);
      }
    } else if(typeOfUser== "undefined"||typeOfUser==null) {
      setHideMyReservesButton(true);
      setHidden(true);
    }
   
  }, [typeOfUser]);



  const handleLogout = () => {
    window.localStorage.clear()
    router.push("/login");
   
    //setHidden(true);
    
  };
  return (
    <header className="">
      <nav
        className={`navbar ${styles.navBar}  navbar-expand-lg navbar-ligth bg-primary `}
      >
        <div className={`col-1  ${styles.logo}`}>
          <Image
            src={"/img/Logo Agência de Viagens.png"}
            alt="Logo da empresa"
            layout="responsive"
            width={800}
            height={600}
          />
        </div>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target=".navbarNav ,.navbarNav2"
          aria-controls="#navbarNav "
          aria-expanded="false"
          aria-label="Alterna navegação"
        >
          <span className="navbar-toggler-icon" />
        </button>
        <div
          className=" justify-content-center collapse navbar-collapse navbarNav"
          id="navbarNav"
        >
          <ul className="col-md-9 justify-content-center text-center navbar-nav">
            <li className="nav-item active">
              <Link href="/" className="nav-link ">
                Home
              </Link>
            </li>
            <li className="nav-item active">
              <Link href={"/destinos"} className="nav-link">
                Destino
              </Link>
            </li>
            <li className="nav-item active">
              <Link href={"/promocoes"} className="nav-link">
                Promoções
              </Link>
            </li>
            <li className="nav-item active">
              <Link href={"/sobre"} className="nav-link">
                Sobre Nós
              </Link>
            </li>
            <li  className="nav-item active">
              <Link hidden={hideMyReservesButton} href={"/reserva/minhasReservas"} className="nav-link">
                Minhas reservas
              </Link>
            </li>
          </ul>
          <div className="col-md-2 ">
            <div className={`row ${styles.btnHeader} `}>
              <Link
                href={"/login/cadastro"}
                className="btn  btn-primary active "
              >
                Cadastre-se
              </Link>
              <Link href={"/login"} className="btn  btn-primary active ">
                Login
              </Link>
              <button
                hidden={hidden}
                onClick={handleLogout}
                className="btn  btn-primary"
              >
                <i className="bi bi-power"></i>
              </button>
            </div>
          </div>
        </div>
      </nav>
    </header>
  );
};

export default NavBar;
