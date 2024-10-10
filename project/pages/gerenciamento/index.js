import React, { useEffect,useState } from "react";
import styles from "../../styles/gerenciamento.module.css";
import DropDown from "@/components/dropDown";
import HeadComponent from "@/components/head";
import { useRouter } from "next/router";
const Gerenciamento = () => {
  const[hidden,setHidden]=useState(false)
  const router=useRouter()
  useEffect(() => {
    const storedUserType=window.localStorage.getItem("typeOfUser")
    if(storedUserType=="ADMIN"|| storedUserType=="EMPRESA"){
      setHidden(true)
    }else{
      const result =confirm("O usuário não tem permissão para acessar essa página, dejesa voltar ?")
      router.push("/")
    }
  }, []);
  const arrAdmin = [
    {
      title: "Clientes",
      create: "/clientes",
      list: "/clientes/lista",
      icon: <i className="bi bi-universal-access"></i>,
    },
    {
      title: "Mensagens",
      list: "/contato/lista",
      icon: <i className="bi bi-chat-dots"></i>,
    },
    {
      title: "Pacotes",
      create: "/destinos/criar",
      list: "/gerenciamento/destinos",

      icon: <i className="bi bi-airplane"></i>,
    },
    {
      title: "Reservas",
      list: "/gerenciamento/reservas",
      icon: <i className="bi bi-file-check"></i>,
    },
  ];
  return (
    <>
    {hidden&& <main>
      <HeadComponent title={"Tropical | Admin"} />
      <div className={`${styles.min} text-center`}>
        <h1 className={`${styles.titulo}`}>Administração</h1>
        <div className={`${styles.divbtn}`}>
          <button
            className="btn btn-primary "
            type="button"
            data-bs-toggle="offcanvas"
            data-bs-target="#staticBackdrop"
            aria-controls="staticBackdrop"
          >
            <i className="bi bi-wrench"> </i>
            Setores
          </button>
        </div>
        <div
          className={`offcanvas offcanvas-start ${styles.canvas}`}
          data-bs-backdrop="static"
          tabIndex={-1}
          id="staticBackdrop"
          aria-labelledby="staticBackdropLabel"
        >
          <div className="offcanvas-header">
            <h5 className="offcanvas-title" id="staticBackdropLabel">
              Offcanvas
            </h5>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="offcanvas"
              aria-label="Close"
            />
          </div>
          <div className="offcanvas-body">
            {arrAdmin.map((i, index) => (
              <DropDown
                key={index}
                title={i.title}
                create={i.create}
                list={i.list}
                icon={i.icon}
              />
            ))}
          </div>
        </div>
      </div>
      </main>}
    </>
  );
};

export default Gerenciamento;
