import React, { useEffect, useState,useContext } from "react";
import Link from "next/link";
import styles from "../styles/navBar.module.css";
import { useRouter } from "next/router";
import Image from "next/image";

import { GlobalContext } from "@/contexts/appContext";
const NavBar = () => {
  
  const [hidden, setHidden] = useState(true);
  const[typeOfUser,setTypeOfUser]=useState("")
  const [hideMyReservesButton, setHideMyReservesButton] = useState(true);
  const router = useRouter();

  
  
  useEffect(() => {
    const typeOfUser= window.localStorage.getItem("typeOfUser")
    if(typeOfUser){
    setTypeOfUser(typeOfUser)
  }
  console.log(typeOfUser)
    if (typeOfUser ) {
      setHidden(false);
      if (typeOfUser  === "ADMIN" || typeOfUser =="EMPRESA") {
        setHideMyReservesButton(true);
      } else {
        setHideMyReservesButton(true); 
        setHidden(true);
      }
    } else  {
      setHideMyReservesButton(true);
      setHidden(true);
    }
   console.log(typeOfUser)
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
                Início
              </Link>
            </li>
            <li className="nav-item active">
              <Link href={"/destinos"} className="nav-link">
                Destinos
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
            <li className="nav-item active">
              <Link href={"/gerenciamento"} className="nav-link">
                Administração
              </Link>
            </li>
            <li  className="nav-item active">
            <div hidden={hideMyReservesButton}>
            <Link  href={"/reserva/minhasReservas"} className="nav-link">
                Minhas reservas
              </Link>
            </div>
              
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
