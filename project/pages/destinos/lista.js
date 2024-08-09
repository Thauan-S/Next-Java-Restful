import HeadComponent from "@/components/head";
import Table from "@/components/table";
import React, { useEffect, useState,useContext } from "react";
import axios from "axios";
 import TableDestinos from "@/components/tablePackage";
import TablePackage from "@/components/tablePackage";
import { GlobalContext } from "@/contexts/appContext";
const ListaDestinos = () => {
  const [packages, setPackages] = useState(null);
  var [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [direction, setDirection] = useState("ASC");
  const[update,setUpdate]=useState(false)
  const {urlPackage:{url}}=useContext(GlobalContext)
  //falta  recarregar a página quando atualizar
  console.log("UPDATE NA PÁG DE LISTAGEM" ,update)
  useEffect(() => {
    axios
      .get(url,{
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
  }, [page,size,direction,url,update]);
  
  return (
    <>
      <HeadComponent title={"Lista | Destinos"} />
      <main>
        {  <TablePackage packages={packages} update={{update,setUpdate}} /> }
      </main>
    </>
  );
};

export default ListaDestinos;
