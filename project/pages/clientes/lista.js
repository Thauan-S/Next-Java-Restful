import { useEffect, useState } from "react";
import Table from "@/components/table";
import HeadComponent from "@/components/head";
import useGetClients from "@/hooks/useGetClients";
import Link from "next/link";
import axios from "axios";
const Clientes = () => {

 const { username, clientes, setClientes, setPage, page,setDirection,setUpdate } = useGetClients();


  
  return (
    <body>
      <HeadComponent title={" Clientes | Lista"} />
      <main className="list">
        <div className="row">
          <Link
            href={"/gerenciamento"}
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "25px",
            }}
            className="col-1 btn btn-primary"
          >
            <i className="bi bi-gear" />
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
            onClick={() => setPage((prevPage) => prevPage - 1)}
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
          <button
           style={{
            height: "40px",
            width: "50px",
            margin: "15px",
            marginLeft: "5px",
          }}
          className="btn col-1 btn-primary"
          onClick={()=> setDirection( 'ASC')}
          >
          <i className="bi bi-sort-alpha-down"> </i>
          </button>
          <button
           style={{
            height: "40px",
            width: "50px",
            margin: "15px",
            marginLeft: "5px",
          }}
          onClick={()=> setDirection((prevDirection)=> "DESC" )}
          className="btn col-1 btn-primary">
          <i className="bi bi-sort-alpha-up"> </i>
          </button>
          


        </div>

         <Table setUpdate={setUpdate} clientes={clientes} setClientes={setClientes} username={username} /> 
      </main>
    </body>
  );
};

export default Clientes;
