import React, { useEffect, useState } from "react";
import axios from "axios";
import Table from "@/components/table";
import HeadComponent from "@/components/head";

const Contato = () => {
  const [contatos, setContatos] = useState(null);
  useEffect(() => {
    axios
      .get("http://localhost:5220/api/Contatos")
      .then((response) => {
        console.log("contatoss",response.data)
        setContatos(response.data);
      })
      .then((error) => {
        console.error("erro ao buscar os contatos", error);
      });
  },[]);
  return (
    <div>
      <HeadComponent title={"Lista | Contatos"} />
      <main>
         <Table contatos={contatos} />
      </main>
    </div>
  );
};

export default Contato;
