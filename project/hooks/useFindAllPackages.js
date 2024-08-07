import axios from "axios";
import React, { useState, useEffect } from "react";

const useFindAllPackages = () => {
  const [packages, setPackages] = useState();
  const [token, setToken] = useState();
  var [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [direction, setDirection] = useState("ASC");
  useEffect(() => {
    setToken(window.localStorage.getItem("token"));
    axios
      .get("http://localhost:80/api/pacotes/v1", {
        params: {
          page: page,
          size: size,
          direction: direction,
        }
      })
      .then((response) => {
        setPackages(response.data);
        console.log(packages)
      })
      .catch((error) => {
        console.error("Erro ao buscar a lista de pacotes");
      });
  }, []);
  return { packages };
};

export default useFindAllPackages;
