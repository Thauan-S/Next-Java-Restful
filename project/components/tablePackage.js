import axios from "axios";
import Link from "next/link";
import React, { useState,useEffect } from "react";
import EditPackageModal from "./editPackageModal";


const TablePackage = ({ packages }) => {
  const [modal, setModal] = useState(true);
  const [idPackage, setIdPackage] = useState();
  const[token,setToken]=useState()
  
useEffect(()=>{
  setToken(window.localStorage.getItem('token'))
},[setToken])
  const handlePackageSelected = (id) => {
    setIdPackage(id)
    setModal(!modal);
  };

  return (
    <>
      {packages ? (
        <div className="table-responsive">
          <table className="table">
            <thead>
              <tr>
                <th scope="col"># ID</th>
                <th scope="col">Destino</th>
                <th scope="col">Descrição</th>
                <th scope="col">Categoria</th>
                <th scope="col">Duração em dias</th>
                <th scope="col">imagem</th>
                <th scope="col">Preço</th>
                <th scope="col">Ações</th>
              </tr>
            </thead>
            <tbody>
              {packages.map((i, index) => (
                <tr key={index}>
                  <td>{i.id}</td>
                  <td>{i.destino}</td>
                  <td>{i.descricao}</td>
                  <td>{i.categoria}</td>
                  <td>{i.duracaoEmDias}</td>
                  <td>{i.imagem}</td>
                  <td>{i.preco}</td>

                  <td>
                    <button
                      onClick={() => handlePackageSelected(i.id)}
                      type="button"
                      className="btn btn-primary"
                    >
                      <i className="bi bi-gear-fill" />
                    </button>

                    <button
                      href={`/destinos/delete-destino/${i.id}`}
                      type="button"
                      className="btn btn-primary"
                    >
                      <i className="bi bi-trash"></i>
                    </button>

                    <button
                      href={`/reserva/criar/${i.id}`}
                      type="button"
                      className="btn btn-primary"
                    >
                      {" "}
                      Reservar
                      <i className="bi bi-cart2"></i>
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
      {idPackage && (
        <EditPackageModal
        token={token}
          modal={modal}
          setModal={setModal}
          idPackage={idPackage}
        />
      )}
    </>
  );
};

export default TablePackage;
