import { GlobalContext } from "@/contexts/appContext";
import useFindAllPackages from "@/hooks/useFindAllPackages";
import axios from "axios";
import React, { useContext, useState, useEffect } from "react";

const EditReserveModal = ({ idReserve, modal: { modal, setModal } }) => {
  const {
    urlReserve,
    urlPackage: { url },
    globalState: { token, username },
  } = useContext(GlobalContext);
  const { packages } = useFindAllPackages();
  const [reserveDb, setReserveDb] = useState({
    id: "",
    destino: "",
    dataViagem: "",
  });

  const [reserva, setReserva] = useState({
     reservaId: "",
     dataViagem:"",
     pacote: {
       id:""
     }
    
  });
  const [hidden, setHidden] = useState(true);
  const handleCreateReserve = () => {
    axios
      .post(urlReserve, reserva, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        console.log(res.status);
        if (res.status == 200) {
          setHidden(false);
        }
      });
  };
  const handleInputChange = (e) => {
    if(e.target.name=="id"){
        setReserva(reserva.pacote.id=e.target.value)
    }
    setReserva({ ...reserva, [e.target.name]: e.target.value });
  };
  console.log(reserva)
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
                Pacote id <p>ID: {idReserve}</p>
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
                  {packages && packages.content.map((i)=><option  key={i.id}  value={i.id}>{i.destino}</option>)}
                </select>
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

export default EditReserveModal;
