import HeadComponent from "@/components/head";
import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import TablePackage from "@/components/tablePackage";
import useFindAllPackages from "@/hooks/useFindAllPackages";
import useFindAllPackagesByEnterpriseName from "@/hooks/useFindAllPackagesByEnterpriseName";
const ListaDestinos = () => {
  const { travelPackages, setUpdate, update } =
    useFindAllPackagesByEnterpriseName();

  return (
  
      <div>
        <HeadComponent title={"Lista | Destinos"} />
        <main>
          {
            <TablePackage
              packages={travelPackages}
              update={{ setUpdate, update }}
            />
          }
        </main>
      </div>
    
  );
};

export default ListaDestinos;
