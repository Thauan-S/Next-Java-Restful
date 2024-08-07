import Card from "@/components/card";
import HeadComponent from "@/components/head";
import useFindAllPackages from "@/hooks/useFindAllPackages";
import axios from "axios";
import React, { useEffect, useState } from "react";

const Destinos = () => {
  const [pacotes, setPacotes] = useState(null);
  const [token, setToken] = useState(null);
  const { packages } = useFindAllPackages();
  const destinosNacionais =
    pacotes && pacotes.filter((pacote) => pacote.categoria === "nacional");
  const destinosInternacionais =
    pacotes && pacotes.filter((pacote) => pacote.categoria === "internacional");
  return (
    <>
      <HeadComponent title={"Tropical | Destinos"} />
      <main>
        <div className="container-fluid">
          <Card pacotesNacionais={destinosNacionais} />
        </div>
        <h1 className="text-center text-info">Internacionais</h1>
        <div className={`container-fluid`}>
          <Card pacotesInternacionais={destinosInternacionais} />
        </div>
      </main>
    </>
  );
};

export default Destinos;
