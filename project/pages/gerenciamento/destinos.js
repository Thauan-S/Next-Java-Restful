import HeadComponent from "@/components/head";
import React, { useEffect, useState,useContext } from "react";
import axios from "axios";
import Link from "next/link";
import TablePackage from "@/components/tablePackage";
import useFindAllPackages from "@/hooks/useFindAllPackages";

import { GlobalContext } from "@/contexts/appContext";
import EditPackageModal from "@/components/editPackageModal";
const ListaDestinos = () => {
    const [modal, setModal] = useState(true);
    const [idPackage, setIdPackage] = useState();
  const{packages,setUpdate,update}=useFindAllPackages()
  const {
    urlPackage: { url },
    globalState: { token, username },
  } = useContext(GlobalContext);
  const handlePackageSelected = (id) => {
    setIdPackage(id);
    setModal(!modal);
  };
  const handleDeletePackage = (id) => {
    var response = confirm(`Deseja excluir o pacote de id: ${id}?`);
    console.log(response);
    if (response == true) {
      axios
        .delete(`${url}/` + id, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          console.log(response.status);
          setUpdate((prevUpdate) => !prevUpdate);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };
  return (
    <>
      <HeadComponent title={"Lista | Destinos"} />
      <main>
      <>
      <div className="row">
       
        {username == "admin" ? (
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
                  <td>{i.destiny}</td>
                  <td>{i.description}</td>
                  <td>{i.category}</td>
                  <td>{i.days}</td>
                  <td>{i.image}</td>
                  <td>{i.price}</td>

                  <td>
                    {username.startsWith("empresa") || username == "admin" ? (
                      <button
                        onClick={() => handlePackageSelected(i.id)}
                        type="button"
                        className="btn btn-primary"
                      >
                        <i className="bi bi-gear-fill" />
                      </button>
                    ) : null}

                    {username.startsWith("empresa") || username == "admin" ? (
                      <button
                        onClick={() => handleDeletePackage(i.id)}
                        type="button"
                        className="btn btn-primary"
                      >
                        <i className="bi bi-trash"></i>
                      </button>
                    ) : null}
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
          update={ {setUpdate,update} }
        />
      )}
    </>
      </main>
    </>
  );
};

export default ListaDestinos;
