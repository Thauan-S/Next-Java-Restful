import { useEffect, useState } from "react";

import Table from "@/components/table";
import HeadComponent from "@/components/head";
import { headers } from "@/next.config";
import useGetClients from "@/hooks/useGetClients";
const Clientes = () => {
  
  
  const {username,clientes}=useGetClients()

  return (
    <main>
      <HeadComponent title={" Clientes | Lista"} />
      <h1 className="text-center">Bem vindo ,{username}</h1>
      <Table clientes={clientes} />
    </main>
  );
};

export default Clientes;
