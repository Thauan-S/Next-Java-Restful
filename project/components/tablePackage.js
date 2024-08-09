import axios from "axios";
import Link from "next/link";
import React, { useState, useEffect, useContext } from "react";
import EditPackageModal from "./editPackageModal";
import { GlobalContext } from "@/contexts/appContext";

const TablePackage = ({ packages, update }) => {
  const [modal, setModal] = useState(true);
  const [idPackage, setIdPackage] = useState();
  const {
    url,
    globalState: { token },
  } = useContext(GlobalContext);

  const handlePackageSelected = (id) => {
    setIdPackage(id);
    setModal(!modal);
  };
  
  const handleDeletePackage = (id) => {
   confirm(`Deseja excluir o pacote de id: ${id}?`)
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
                      onClick={()=>handleDeletePackage(i.id)}
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
          modal={modal}
          setModal={setModal}
          idPackage={idPackage}
          update={update}
        />
      )}
    </>
  );
};

export default TablePackage;
