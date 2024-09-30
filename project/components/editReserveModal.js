import { GlobalContext } from "@/contexts/appContext";
import useFindAllPackages from "@/hooks/useFindAllPackages";
import axios from "axios";
import styles from "../styles/editReserveModal.module.css"
import React, { useContext, useState, useEffect } from "react";

const EditReserveModal = ({ idReserve, modal: { modal, setModal },setUpdate }) => {
  const {
    urlReserve,
    urlPackage: { url },
    globalState: { token, username },
  } = useContext(GlobalContext);
  
  const {packages} = useFindAllPackages();
  
  const [reserve, setReserve] = useState({
     reserveId: idReserve,
     travelDate:"",
     travelPackage: {
       id:""
     }
    
  });
  useEffect(()=>{
    axios.get(`${urlReserve}/`+idReserve,{
      headers:{
        Authorization:`Bearer ${token}`,
      }
    }).then((response)=>{
      console.log("response",response)
      setReserve(response.data)
    })
  },[token,idReserve,urlReserve])
  console.log("reserves",token)
  const [hidden, setHidden] = useState(true);
  const handleUpdateReserve = () => {
    axios
      .put(urlReserve, reserve, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        if (res.status == 200) {
          setUpdate((prevUpdate)=>!prevUpdate)
          setHidden(false);
        }
      });
  };
  const handleInputChange = (e) => {
    if(e.target.name=="id"){
      setReserve(reserve.travelPackage.id=e.target.value)
    }
    setReserve({ ...reserve, [e.target.name]: e.target.value });
  };
  console.log(reserve)
  return (
    <>
      <div
        className="modal fade show"
        style={{ display: "block" }}
        id="exampleModal"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className={`modal-content  ${styles.modal}`}>
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                Reserva id <p>ID: {idReserve}</p>
              </h1>
              <button
                type="button"
                onClick={() => setModal(!setModal)}
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              />
            </div>
            <div className="modal-body">
              <div className="alert alert-primary" hidden={hidden} role="alert">
                Reserva Atualizada com com sucesso!
              </div>

              <div className="mb-3">
                <label htmlFor="exampleInputEmail1" className="form-label">
                  Destino
                </label>
                <select
                    onChange={handleInputChange}
                  className="form-select"
                  aria-label="Default select example"
                  name="id"
                >
                  <option selected="">Escolha um novo destino</option>
                  {packages && packages.map((i)=><option  key={i.id}  value={i.id}>{i.destiny}</option>)}
                </select>
              </div>
              <div className="mb-3">
                <label htmlFor="exampleInputDate" className="form-label">
                
                </label>
                <input
                  onChange={handleInputChange}
                  name="travelDate"
                  value={reserve.travelDate}
                  type="date"
                  className="form-control"
                  id="exampleInputDate"
                />
              </div>
              <button
                type="submit"
                onClick={handleUpdateReserve}
                className="btn btn-primary"
              >
                Enviar
              </button>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                onClick={() => setModal(!modal)}
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default EditReserveModal;
