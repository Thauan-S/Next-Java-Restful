import HeadComponent from "@/components/head";
import React, { useEffect, useState,useContext } from "react";
import axios from "axios";
import TablePackage from "@/components/tablePackage";
import useFindAllPackages from "@/hooks/useFindAllPackages";
const ListaDestinos = () => {
  
  const{packages,setUpdate,page,size,setSize,setPage,direction,setDirection,update}=useFindAllPackages()
  //falta  recarregar a página quando atualizar
  return (
    <>
      <HeadComponent title={"Lista | Destinos"} />
      <main>
        {  <TablePackage params={{page,setPage,size,setSize,direction,setDirection}} packages={packages} update={{update,setUpdate}} /> }
      </main>
    </>
  );
};

export default ListaDestinos;
