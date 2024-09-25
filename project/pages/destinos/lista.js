import HeadComponent from "@/components/head";
import React, { useEffect, useState,useContext } from "react";
import axios from "axios";
import TablePackage from "@/components/tablePackage";
import useFindAllPackages from "@/hooks/useFindAllPackages";
import useFindAllPackagesByEnterpriseName from "@/hooks/useFindAllPackagesByEnterpriseName";
const ListaDestinos = () => {
  
  const{travelPackages}=useFindAllPackagesByEnterpriseName()
  //falta  recarregar a página quando atualizar
 
  return (
    <>
      <HeadComponent title={"Lista | Destinos"} />
      <main>
        {  <TablePackage packages={travelPackages}  /> }
      </main>
    </>
  );
};

export default ListaDestinos;
