import { GlobalContext } from "@/contexts/appContext";
import { globalState } from "@/contexts/data";
import axios from "axios";
import React, { useEffect, useState, useContext } from "react";

const useFindAllPackagesByEnterpriseName = () => {
  const[username,setUsername]=useState("")
  const[token,setToken]=useState("")
  const[update,setUpdate]=useState(false)
 
  
  const [travelPackages, setTravelPackages] = useState("");
  useEffect(() => {
    const storedUsername = window.localStorage.getItem("username");
    const storedToken = window.localStorage.getItem("token");
    if (storedUsername && storedToken) {
      setUsername(storedUsername);
      setToken(storedToken);
    }
  }, []);
  useEffect(() => {
    if(username&&token){
    axios
      .get(`https://next-java-restful-tropical-back-end.onrender.com/api/travelPackages/v1/enterprise/${username}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTravelPackages(response.data)});
      }
  }, [token,username,update]);
  return { travelPackages,setUpdate,update };
};

export default useFindAllPackagesByEnterpriseName;
