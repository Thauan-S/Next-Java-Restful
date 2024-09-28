import { GlobalContext } from '@/contexts/appContext';
import axios from 'axios';
import React,{useState,useEffect,useContext} from 'react'


const useFindAllReserves = () => {
    const [reserves, setReserves] = useState(null);
    const[update,setUpdate]=useState(true)
    const {
      urlReserve,
      globalState: { token,username },
    } = useContext(GlobalContext);
    useEffect(() => {
      axios
        .get(urlReserve, {
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
   
    }, [ token, urlReserve,update]);
    return {reserves,setUpdate,username}
}

export default useFindAllReserves