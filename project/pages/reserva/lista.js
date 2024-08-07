import React, { useEffect, useState } from "react";
import axios from "axios";
import Table from "@/components/table";
import HeadComponent from "@/components/head";
import { headers } from "@/next.config";
const Reservas = () => {
  const [reservas, setReservas] = useState(null);
  
  useEffect(() => {
    axios
      .get("http://localhost:80/api/reservas/v1",{
        headers: {
          Authorization: `Bearer ${window.localStorage.getItem("token")}`,
         }
      ,params:{
         page:0,
        size:2,
         direction:"ASC"
        }
      })
      .then((response) => {
        console.log("response",response.data)
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
