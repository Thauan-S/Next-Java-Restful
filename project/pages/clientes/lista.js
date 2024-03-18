import { useEffect, useState } from "react";
import axios from "axios";
import Table from "@/components/table";
import HeadComponent from "@/components/head";
const Clientes = () => {
  const [clientes, setClientes] = useState(null);
  useEffect(() => {
    axios
      .get("https://localhost:7250/api/Clientes")
      .then((response) => {
        setClientes(response.data);
        console.log("Response", response.data);
      })
      .catch((error) => {
        console.error("Erro ao Listar os Clientes", error);
      });
  }, []);

  return (
    <main>
      <HeadComponent title={" Clientes | Lista"} />
      <Table clientes={clientes} />
    </main>
  );
};

export default Clientes;
