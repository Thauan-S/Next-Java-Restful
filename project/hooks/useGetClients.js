import React, { useEffect, useState } from "react";
import axios from "axios";
function useGetClients() {
  const [update,setUpdate]=useState(false)
  const [username, setUsername] = useState("");
  const [clientes, setClientes] = useState(null);
  var [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [direction, setDirection] = useState("ASC");
  console.log("valor",update)
  // const [getClients, setGetClients] = useState(() => {
  //   return (p)=>{
  //   axios
  //     .get("http://localhost/api/clientes/v1", {
  //       headers: {
  //         Authorization: `${window.localStorage.getItem("token")}`,
  //       },
  //       params: {
  //         page: p,
  //         size: size,
  //         direction: direction,
  //       },
  //     })
  //     .then((response) => {
  //       console.log("Response", response.data);
  //       setClientes(response.data._embedded.clienteVOList);
  //     })
  //     .catch((error) => {
  //       console.error("Erro ao Listar os Clientes", error);
  //     })
  //   }
  // });
  useEffect(() => {
    setUsername(window.localStorage.getItem("username"));
    axios
      .get("http://localhost:80/api/clients/v1", {
        headers: {
          Authorization: `Bearer ${window.localStorage.getItem("token")}`,
        },params:{
        page:page,
        size:size,
        direction:direction
       }
      })
      .then(response => {
        setClientes(response.data.customersList)
   });
  },[page,size,direction,update]);
  return { username, clientes, setPage, page ,setUpdate,setDirection};
}

export default useGetClients;
