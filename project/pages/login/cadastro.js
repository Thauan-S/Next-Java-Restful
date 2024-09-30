import React, { useState,useEffect } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import HeadComponent from "@/components/head";
import styles from "../../styles/cadastro.module.css";
import CreateClient from "@/components/createClient";
import CreateEnterprise from "@/components/createEnterprise";

const Cadastro = () => {
  const [activeComponent, setActiveComponent] = useState(false);
  const components={
    client:<CreateClient/>,
    enterprise:<CreateEnterprise/>
  }
  return (
    <>
    
      <HeadComponent title={"Tropical | Cadastro"} />
      <main>
        <div className={`${styles.div_signUp}`}>
          <button
            className="btn btn-primary"
            type="submit"
            onClick={() => setActiveComponent("client")}
          >
            <i className="bi bi-person-fill-add"> </i>  Cliente
          </button>

          <button
            className="btn btn-primary"
            type="submit"
            onClick={() => setActiveComponent("enterprise")}
          >
            <i className="bi bi-building-add"> </i> Empresa
          </button>
        </div>
     
       {components[activeComponent]}
       
      </main>
      
    </>
  );
};

export default Cadastro;
