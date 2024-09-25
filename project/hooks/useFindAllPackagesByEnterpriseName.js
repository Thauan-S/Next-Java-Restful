import { GlobalContext } from "@/contexts/appContext";
import { globalState } from "@/contexts/data";
import axios from "axios";
import React, { useEffect, useState, useContext } from "react";

const useFindAllPackagesByEnterpriseName = () => {
  const [name, setName] = useState();
  const {
    urlPackage:{url},
    globalState: { token, username },
  } = useContext(GlobalContext);
  console.log(username)
  console.log(url)
  const [travelPackages, setTravelPackages] = useState("");
  useEffect(() => {
    setName(username);
    axios
      .get(`${url}/enterprise/${name}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log(response.data)
        setTravelPackages(response.data)});
  }, [name, token, username, url]);
  return { travelPackages };
};

export default useFindAllPackagesByEnterpriseName;
