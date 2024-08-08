import HeadComponent from "@/components/head";
import Table from "@/components/table";
import React, { useEffect, useState } from "react";
import axios from "axios";
import TableDestinos from "@/components/tablePackage";
import TablePackage from "@/components/tablePackage";
const ListaDestinos = () => {
  const [packages, setPackages] = useState(null);
  var [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [direction, setDirection] = useState("ASC");
  const[update,setUpdate]
  //falta  recarregar a página quando atualizar
  useEffect(() => {
    axios
      .get("http://localhost:80/api/pacotes/v1",{
        params:{
          page:page,
          size:size,
          direction:direction
          
        }
      })
      .then((response) => {
        setPackages(response.data.content);

      })
      .catch((error) => {
        console.error("erro ao buscar a lista de pacotes", error);
      });
  }, [page,size,direction]);
  
  return (
    <>
      <HeadComponent title={"Lista | Destinos"} />
      <main>
        {  <TablePackage packages={packages} /> }
      </main>
    </>
  );
};

export default ListaDestinos;
