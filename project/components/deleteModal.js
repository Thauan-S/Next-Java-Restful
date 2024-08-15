import { GlobalContext } from "@/contexts/appContext";
import axios from "axios";
import React, { useState,useContext } from "react";

const DeleteModal = ({ idReserve, destiny, setHiddenDelete,setUpdate }) => {
  const [hidden, setHidden] = useState(true);
  const{urlReserve,globalState:{token}}=useContext(GlobalContext)
  const handleDeleteReserve=(id)=>{
    axios
    .delete(`${urlReserve}/${id}`,{
        headers:{
            Authorization:`Bearer ${token}`
        }
    }).then((res)=>{
        if(res.status==204){
            setUpdate((prevUpdate)=>!prevUpdate)
        }
        
    })
  }
  return (
    <>
      <div
        className="modal fade show"
        style={{ display: "block" }}
        id="exampleModal"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden={false}
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                <div className="alert alert-danger" role="alert">
                  Tem certeza de que deseja cancelar sua reserva com destino a{" "}
                  {destiny}?
                </div>
              </h1>
              <button
                type="button"
                onClick={() => setHiddenDelete((prevHidden) => !prevHidden)}
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              />
            </div>
            <div className="modal-body">
              <div className="alert alert-primary" hidden={hidden} role="alert">
                Reserva cancelada com com sucesso!
              </div>

              <button
                type="submit"
                onClick={()=> handleDeleteReserve(idReserve)}
                className="btn btn-primary"
              >
                Cancelar
              </button>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                onClick={() => setHiddenDelete((prevHidden) => !prevHidden)}
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

export default DeleteModal;
