import React, { useEffect, useState } from "react";
import axios from "axios";
import Table from "@/components/table";
import HeadComponent from "@/components/head";
const Reservas = () => {
  const [reservas, setReservas] = useState(null);
  useEffect(() => {
    axios
      .get("https://localhost:7250/api/Reservas")
      .then((response) => {
        setReservas(response.data);
      })
      .catch((error) => {
        console.error("erro ao buscar as reservas ", error);
      });
  }, []);
  return (
    <main className={`container-fluid`}>
      <HeadComponent title={"Lista | Reservas "} />
      {<Table reservas={reservas} />}
    </main>
  );
};

export default Reservas;
