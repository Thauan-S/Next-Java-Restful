import { GlobalContext } from "@/contexts/appContext";
import { globalState } from "@/contexts/data";
import axios from "axios";
import React, { useEffect, useState, useContext } from "react";

const useFindAllPackagesByEnterpriseName = () => {
  const[username,setUsername]=useState("")
  const[token,setToken]=useState("")
  const[update,setUpdate]=useState(false)
  const {
    urlPackage:{url},
  } = useContext(GlobalContext);
  
  const [travelPackages, setTravelPackages] = useState("");
  useEffect(() => {
    setUsername(window.localStorage.getItem("username"))
    setToken(window.localStorage.getItem("token"))
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
