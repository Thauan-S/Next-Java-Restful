import Card from "@/components/card";
import HeadComponent from "@/components/head";
import useFindAllPackages from "@/hooks/useFindAllPackages";
import axios from "axios";
import React, { useEffect, useState } from "react";

const Destinos = () => {
 
 
  const { packages } = useFindAllPackages();
  console.log(packages)
  const pacotesNacionais =
  packages && packages.content.filter((pacote) => pacote.category === "nacional");
  const pacotesInternacionais =
  packages && packages.content.filter((pacote) => pacote.category === "internacional");
  return (
    <>
      <HeadComponent title={"Tropical | Destinos"} />
      <main>
        <div className="container-fluid">
          <Card pacotesNacionais={pacotesNacionais} />
        </div>
        <h1 className="text-center text-info">Internacionais</h1>
        <div className={`container-fluid`}>
          <Card pacotesInternacionais={pacotesInternacionais} />
        </div>
      </main>
    </>
  );
};

export default Destinos;
