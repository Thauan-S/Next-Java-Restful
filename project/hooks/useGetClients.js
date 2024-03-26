import React, { useEffect, useState } from "react";
import axios from "axios";
function useGetClients() {
  const [username, setUsername] = useState();
  const [clientes, setClientes] = useState(null);
  useEffect(() => {
    setUsername(window.localStorage.getItem("username"));
    axios
      .get("http://localhost:80/api/clientes/v1", {
        headers: {
          Authorization: `Bearer ${window.localStorage.getItem("token")}`,
        },
      })
      .then((response) => {
        console.log("Response", response.data);
        setClientes(response.data._embedded.clienteVOList);
      })
      .catch((error) => {
        console.error("Erro ao Listar os Clientes", error);
      });
  }, []);
  return { username,clientes };
}

export default useGetClients;
