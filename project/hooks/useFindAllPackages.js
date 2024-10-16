import { GlobalContext } from "@/contexts/appContext";
import axios from "axios";
import React, { useState, useEffect,useContext } from "react";


const useFindAllPackages = () => {
  const[update,setUpdate]=useState(false)
  const [packages, setPackages] = useState();
  const{globalState:{username},urlPackage:{url}}=useContext(GlobalContext)
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [direction, setDirection] = useState("ASC");
  useEffect(() => {
    axios
      .get("https://next-java-restful-tropical-back-end.onrender.com/api/travelPackages/v1"

, {
        params: {
          page: page,
          size: size,
          direction: direction
        }
      })
      .then((response) => {
        setPackages(response.data.content);
      })
      .catch((error) => {
        console.error("Erro ao buscar a lista de pacotes");
      });
  }, [direction,page,size,url,update]);
  return { packages,direction,page,size,setUpdate,setPage,setSize,setDirection};
};

export default useFindAllPackages;
