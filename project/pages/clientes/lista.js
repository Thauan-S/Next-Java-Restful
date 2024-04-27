import { useEffect, useState } from "react";
import Table from "@/components/table";
import HeadComponent from "@/components/head";
import useGetClients from "@/hooks/useGetClients";
import Link from "next/link";
import axios from "axios";
const Clientes = () => {

 const { username, clientes, setClientes, setPage, page } = useGetClients();


  
  return (
    <>
      <HeadComponent title={" Clientes | Lista"} />
      <main>
        <div className="row">
          <Link
            href={"/clientes"}
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "25px",
            }}
            className="col-1 btn btn-primary"
          >
            <i className="bi bi-person-add" />
          </Link>
          <h1 className="text-center col ">Bem vindo ,{username}</h1>
          <button
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "25px",
            }}
            className="btn col-1 btn-primary"
            onClick={() => setPage((prevPage) => prevPage + 1)}
          >
            <i className="bi bi-arrow-left" />
             
          </button>
          <button
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "5px",
            }}
            className="btn col-1 btn-primary"
            onClick={() => setPage((prevPage) => prevPage + 1)}
          >
            <i className="bi bi-arrow-right" />
            
          </button>
        </div>

         <Table clientes={clientes} setClientes={setClientes} username={username} /> 
      </main>
    </>
  );
};

export default Clientes;
