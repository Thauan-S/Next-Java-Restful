import HeadComponent from "@/components/head";
import Table from "@/components/table";
import React, { useEffect, useState } from "react";
import axios from "axios";
import TableDestinos from "@/components/tableDestinos";
const ListaDestinos = () => {
  const [destinos, setDestinos] = useState(null);
  var [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [direction, setDirection] = useState("ASC");
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
        setDestinos(response.data.content);

      })
      .catch((error) => {
        console.error("erro ao buscar a lista de destinos", error);
      });
  }, [page,size,direction]);
  
  return (
    <>
      <HeadComponent title={"Lista | Destinos"} />
      <main>
        {  <TableDestinos destinos={destinos} /> }
      </main>
    </>
  );
};

export default ListaDestinos;
