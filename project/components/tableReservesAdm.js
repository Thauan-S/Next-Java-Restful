import React, { useContext, useState, useEffect } from "react";
import EditReserveModal from "./editReserveModal";
import useMyReserves from "@/hooks/useMyReserves";
import DeleteModal from "./deleteModal";
import useFindAllReserves from "@/hooks/useFindAllReserves";

const TableReserves = () => {
  const { reserves,setUpdate,username } = useFindAllReserves();
  const [modal, setModal] = useState(false);
  const [idReserve, setIdReserve] = useState("");
  const [destiny, setDestiny] = useState("");
  const [hiddenDelete, setHiddenDelete] = useState(false);
  const handleReserveSelected = (id) => {
    setIdReserve(id);
    setModal((prevModal) => !prevModal);
  };
  const handleDeleteReserve = (id, destino) => {
    setIdReserve(id);
    setDestiny(destino);
    setHiddenDelete(!hiddenDelete);
  };
  return (
    <>
      <main>
        <div className="row">
          {username != null ? (
            <h1 className="text-center col ">Bem vindo ,{username}</h1>
          ) : null}
        </div>
        {reserves ? (
          <div className="table-responsive">
            <table className="table">
              <thead>
                <tr>
                  <th scope="col"># ID</th>
                  <th scope="col">Destino</th>
                  <th scope="col">Data que foi feita a reserva</th>
                  <th scope="col">Data da viagem</th>
                  <th scope="col">Preço</th>
                  <th scope="col">Ações</th>
                </tr>
              </thead>
              <tbody>
                {reserves.content.map((i, index) => (
                  <tr key={index}>
                    <td>{i.reserveId}</td>
                    <td>{i.travelPackage.destiny}</td>
                    <td>{i.creationDate}</td>
                    <td>{i.travelDate}</td>
                    <td>{i.travelPackage.price}</td>
                    <td>
                      <button
                        onClick={() => handleReserveSelected(i.reserveId)}
                        type="button"
                        className="btn btn-primary"
                      >
                        <i className="bi bi-gear-fill" />
                      </button>

                      <button
                        onClick={() =>
                          handleDeleteReserve(i.reserveId, i.travelPackage.destiny)
                        }
                        type="button"
                        className="btn btn-primary"
                      >
                        <i className="bi bi-trash"></i>
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <div className="text-center">
            <div className="spinner-border text-primary" role="status">
              <span className="visually-hidden">Carregando</span>
            </div>
            <h1>Carregando os pacotes de viagem</h1>
          </div>
        )}
        {modal ? (
          <EditReserveModal
            setUpdate={setUpdate}
            idReserve={idReserve}
            modal={{ modal, setModal }}
          />
        ) : null}
        {hiddenDelete ? (
          <DeleteModal
          setUpdate={setUpdate}
          setHiddenDelete={setHiddenDelete}
            idReserve={idReserve}
            destiny={destiny}
          />
        ) : null
          
      }
      </main>
    </>
  );
};

export default TableReserves;
