import { GlobalContext } from "@/contexts/appContext";
import { globalState } from "@/contexts/data";
import axios from "axios";
import React, { useEffect, useState, useContext } from "react";

const useFindAllPackagesByEnterpriseName = () => {
  const[update,setUpdate]=useState(false)
  const {
    urlPackage:{url},
    globalState: {token,username},
  } = useContext(GlobalContext);
  
  const [travelPackages, setTravelPackages] = useState("");
  useEffect(() => {
    axios
      .get(`${url}/enterprise/${username}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTravelPackages(response.data)});
  }, [token,url,username,update]);
  return { travelPackages,setUpdate,update };
};

export default useFindAllPackagesByEnterpriseName;
