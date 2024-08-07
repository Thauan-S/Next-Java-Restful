import { headers } from '@/next.config';
import React,{useState,useEffect} from 'react'

const useCreatePackage = (Package) => {
    const [newPackage,setNewPackage]=useState(Package)
    const[token,setToken]=useState()
    useEffect(()=>{
        axios
        .post("http://localhost:80/api/pacotes/v1",newPackage, {
            headers:{
                Authorization:`Bearer `
            }
        })
    })
    axios
    .post("https://localhost:80/api/pacotes/v1", newPackage,{
        headers:{
            Authorization:`Bearer `
        }
    })
    .then((response) => {
      router.push("/destinos");
    })
    .catch((error) => {
      alert("erro ao criar um novo pacote" + error);
    });
  return {

    }
}

export default useCreatePackage