import { GlobalContext } from "@/contexts/appContext";
import axios from "axios";
import React, { useContext, useEffect, useState } from "react";

const CreateReserveModal = ({ packageId, modal: { modal, setModal } }) => {
  const {
    urlReserve,
    urlPackage: { url },
    globalState: { token, username },
  } = useContext(GlobalContext);
  const [packageDb, setPackageDb] = useState({
    id: "",
    destino: "",
    duracaoEmDias: "",
    preco: "",
  });
  const [reserva, setReserva] = useState({
    dataViagem: "",
    cliente: {
      user:{username:username}
    },
    pacote: {
      id: packageId,
    },
  });
  const [hidden, setHidden] = useState(true);
  console.log(packageDb);
  useEffect(() => {
    axios
      .get(`${url}/` + packageId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setPackageDb(res.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [packageId, url, token]);
  const handleCreateReserve=()=>{
    axios
    .post(urlReserve,reserva,{
        headers:{
            Authorization:`Bearer ${token}`
        }
    })
    .then((res)=>{
        console.log(res.status)
        if(res.status==200){
            setHidden(false)
        }
       
    })
  }
  const handleInputChange=(e)=>{
    setReserva({...reserva,[e.target.name]:e.target.value})
  }
  
  return (
    <>
      <div
        className="modal fade show"
        style={{ display: "block" }}
        id="exampleModal"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden="hidden"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                Pacote id <p>ID: {packageId}</p>
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
                Reserva feita com  com sucesso!
              </div>

              <div className="mb-3">
                <label htmlFor="exampleInputEmail1" className="form-label">
                  Destino
                </label>
                <input
                  value={packageDb.destino}
                  name="destino"
                  hidden={false}
                  readOnly
                  onChange={''}
                  type="text"
                  className="form-control"
                  id="exampleInputNome"
                  aria-describedby="emailHelp"
                />
              </div>
              <div className="mb-3">
                <label htmlFor="exampleInputDate" className="form-label">
                  Data de data de Viagem
                </label>
                <input
                  onChange={handleInputChange}
                  name="dataViagem"
                  value={reserva.dataViagem}
                  type="date"
                  className="form-control"
                  id="exampleInputDate"
                />
              </div>
              <button
                type="submit"
                onClick={handleCreateReserve}
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

export default CreateReserveModal;
