import React, { useState,useEffect } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import HeadComponent from "@/components/head";
import styles from "../../styles/cadastro.module.css";
import CreateClient from "@/components/createClient";
import CreateEnterprise from "@/components/createEnterprise";
import Link from "next/link";

const Cadastro = () => {
  
 
    <CreateClient/>,
   <CreateEnterprise/>
  
  return (
    <>
    
      <HeadComponent title={"Tropical | Cadastro"} />
      <main>
        <div className={`${styles.div_signUp}`}>
          <Link
            className="btn btn-primary"
            
            href="./cadastroCliente"
          >
            <i className="bi bi-person-fill-add"> </i>  Cliente
          </Link>

          <Link
            className="btn btn-primary"
            href="./cadastroEnterprise"
          >
            <i className="bi bi-building-add"> </i> Empresa
          </Link>
        </div>
     
      </main>
      
    </>
  );
};

export default Cadastro;
