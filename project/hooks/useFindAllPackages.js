import { GlobalContext } from "@/contexts/appContext";
import axios from "axios";
import React, { useState, useEffect,useContext } from "react";


const useFindAllPackages = () => {
  const [packages, setPackages] = useState();
  const{globalState:{username}}=useContext(GlobalContext)
  var [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [direction, setDirection] = useState("ASC");
  useEffect(() => {
    axios
      .get("http://localhost:80/api/pacotes/v1", {
        params: {
          page: page,
          size: size,
          direction: direction
        }
      })
      .then((response) => {
        setPackages(response.data);
      })
      .catch((error) => {
        console.error("Erro ao buscar a lista de pacotes");
      });
  }, [direction,page,size]);
  return { packages,direction,page,size, };
};

export default useFindAllPackages;
