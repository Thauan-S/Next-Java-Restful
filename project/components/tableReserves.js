import { GlobalContext } from "@/contexts/appContext";
import Link from "next/link";
import React, { useContext ,useState} from "react";
import EditReserveModal from "./editReserveModal";
const TableReserves = ({ reserves,username }) => {
  console.log("usernamee",username)
  const[modal,setModal]=useState(false)
  const[idReserve,setIdReserve]=useState()
  const[update,setUpdate]=useState(false)
  const {
    globalState
  } = useContext(GlobalContext);
  const handleReserveSelected=(id)=>{
    setIdReserve(id)
    setModal((prevModal)=> !prevModal)
  }
  const handleDeleteReserve=(id)=>{
    alert(id)
  }
  return (
    <>
      <main>
        <div className="row">
          {username != null ? (
            <h1 className="text-center col ">Bem vindo ,{username}</h1>
          ) : null}
          <button
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "25px",
            }}
            className="btn col-1 btn-primary"
            onClick={() => setPage((prevPage) => prevPage - 1)}
          >
            <i className="bi bi-arrow-left" />
          </button>
          <button
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "5px",
            }}
            className="btn col-1 btn-primary"
            onClick={() => setPage((prevPage) => prevPage + 1)}
          >
            <i className="bi bi-arrow-right" />
          </button>
          <button
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "5px",
            }}
            className="btn col-1 btn-primary"
            onClick={() => setDirection("ASC")}
          >
            <i className="bi bi-sort-alpha-down"> </i>
          </button>
          <button
            style={{
              height: "40px",
              width: "50px",
              margin: "15px",
              marginLeft: "5px",
            }}
            onClick={() => setDirection((prevDirection) => "DESC")}
            className="btn col-1 btn-primary"
          >
            <i className="bi bi-sort-alpha-up"> </i>
          </button>
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
                {reserves.map((i, index) => (
                  <tr key={index}>
                    <td>{i.reservaId}</td>
                    <td>{i.pacote.destino}</td>
                    <td>{i.dataReserva}</td>
                    <td>{i.dataViagem}</td>
                    <td>{i.pacote.preco}</td>
                    <td>
                      <button
                        onClick={() => handleReserveSelected(i.reservaId)}
                        type="button"
                        className="btn btn-primary"
                      >
                        <i className="bi bi-gear-fill" />
                      </button>
                      
                      <button
                        onClick={() => handleDeleteReserve(i.reservaId)}
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
        { modal? <EditReserveModal idReserve={idReserve} modal={{modal,setModal}} update={{setUpdate,update}} />: null }
      </main>
    </>
  );
};

export default TableReserves;
