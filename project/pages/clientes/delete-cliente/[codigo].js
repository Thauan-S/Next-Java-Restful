import React, { useEffect, useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
const DeleteCliente = () => {
  const router = useRouter();
  const { codigo } = router.query;
  const [clienteId, setClienteId] = useState(codigo);
  const [token, setToken] = useState();
  useEffect(()=>{
    setToken(window.localStorage.getItem("token"))
  },[])
  const handleDeleteClient = () => {
   
    axios
      .delete("http://localhost:80/api/clientes/v1/" + clienteId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        router.push("/clientes/lista");
      })
      .catch((error) => {
        console.error("não foi possível excluir o cliente", clienteId, error);
      });
    
  };
  return (
    <main>
      <h1>Cliente a ser excluído</h1>
            <input
              type="text"
              readOnly
              value={clienteId}
              onChange={(e) => setClienteId(e.target.value)}
            />
            <button className="btn btn-primary" onClick={handleDeleteClient}>
              excluir
            </button>
         
    </main>
  );
};

export default DeleteCliente;
