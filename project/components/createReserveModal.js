import { GlobalContext } from "@/contexts/appContext";
import axios from "axios";
import { useRouter } from "next/router";
import React, { useContext, useEffect, useState } from "react";

const CreateReserveModal = ({ packageId, modal: { modal, setModal } }) => {
  const {
    urlReserve,
    urlPackage: { url },
    globalState: { token, username },
  } = useContext(GlobalContext);
  const router=useRouter()
  const [packageDb, setPackageDb] = useState({
    id: "",
    destiny: "",
    days: "",
    price: "",
  });
  const [reserve, setReserve] = useState({
    travelDate: "",
    client: {
      user:{email:username}
    },
    travelPackage: {
      id: packageId,
    }
  });
  const [hidden, setHidden] = useState(true);
  useEffect(() => {
    axios
      .get(`${url}/`+packageId, {
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
  }, [packageId,url,token]);

  const handleCreateReserve=()=>{
    axios
    .post(urlReserve,reserve,{
        headers:{
            Authorization:`Bearer ${token}`
        }
    })
    .then((res)=>{
      console.log(  res.data)
        if(res.status==200){
            setHidden(false)
            router.push("/reserva/minhasReservas")
        }
       
    }).catch((error)=>{
      console.log(error)
    })
  }
  const handleInputChange=(e)=>{
    setReserve({...reserve,[e.target.name]:e.target.value})
  }
  
  return (
    <>
      <div
        className="modal fade show"
        style={{ display: "block" }}
        id="exampleModal"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden="false"
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
                  value={packageDb.destiny}
                  name="destiny"
                  hidden={false}
                  readOnly
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
                  name="travelDate"
                  value={reserve.travelDate}
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
