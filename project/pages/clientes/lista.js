import { useEffect, useState } from "react";
import axios from "axios";
import Table from "@/components/table";
import HeadComponent from "@/components/head";
import { headers } from "@/next.config";
const Clientes = () => {
  const [clientes, setClientes] = useState(null);
  const [username,setUsername]=useState()
  useEffect(() => {
    setUsername(window.localStorage.getItem('username'))
    axios
      .get("http://localhost:80/api/clientes/v1",{
        headers:{
          Authorization:`Bearer ${window.localStorage.getItem('token')}`
        }
        
      })
      .then((response) => {
        console.log("Response", response.data);
        setClientes(response.data._embedded.clienteVOList);
        
      })
      .catch((error) => {
        console.error("Erro ao Listar os Clientes", error);
      });
  }, []);

  return (
    <main>
      <HeadComponent title={" Clientes | Lista"} />
      <h1 className="text-center">Bem vindo ,{username}</h1>
      <Table clientes={clientes} />
    </main>
  );
};

export default Clientes;
