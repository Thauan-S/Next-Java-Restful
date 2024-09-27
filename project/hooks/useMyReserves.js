import { GlobalContext } from "@/contexts/appContext";
import axios from "axios";
import React, { useEffect, useContext, useState } from "react";

const useMyReserves = () => {
  const [reserves, setReserves] = useState(null);
  const[update,setUpdate]=useState(true)
  const {
    urlReserve,
    globalState: { token,username },
  } = useContext(GlobalContext);
  useEffect(() => {
    axios
      .get(`${urlReserve}/client/${username}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        console.log(res.data)
        setReserves(res.data);
      })
      .catch((error) => {
        console.error(error);
      });
 
  }, [username, token, urlReserve,update]);
  return { reserves, setReserves, username,setUpdate };
};

export default useMyReserves;
