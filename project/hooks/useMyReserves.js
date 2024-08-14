import { GlobalContext } from '@/contexts/appContext'
import axios from 'axios'
import React,{useEffect,useContext,useState} from 'react'

const useMyReserves = () => {
    const[reserves,setReserves]=useState(null)
   const[username,setUsername]=useState()
    const{urlReserve,globalState:{token}}=useContext(GlobalContext)
    
    useEffect(()=>{
        setUsername(window.localStorage.getItem('username'))
        axios.get(`${urlReserve}/client/${username}`,{
            headers:{
                Authorization:`Bearer ${token}`
            }
        }).then((res)=>{
            console.log(res.data)
            setReserves(res.data)
        }).catch((error)=>{
            console.error(error)
        })
       
    },[username,token,urlReserve])
    console.log("useMyReserver",username)
  return {reserves,setReserves,username}
    
  
}

export default useMyReserves