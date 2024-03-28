import Card from "@/components/card";
import HeadComponent from "@/components/head";
import axios from "axios";
import React, { useEffect, useState } from "react";

const Destinos = () => {
  const [pacotes, setPacotes] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:80//api/pacotes/v1",{
        headers:{
          Authorization:`Bearer `
        }
      })
      .then((response) => {
        setPacotes(response.data);
        console.log(pacotes);
      })
      .catch((error) => {
        console.error("Erro ao buscar a lista de pacotes");
      });
  }, []);
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
