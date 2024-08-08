import Link from "next/link";
import React from "react";

const TableDestinos = ({ destinos }) => {

    const handleDestinySelected=(id)=>{
        const destiny=destinos.find((d)=> d.id===id)
        console.log(destiny)
       
    }
  console.log("destinos", destinos);
  return (
    <>
      {destinos ? (
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
              {destinos.map((i, index) => (
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
                     onClick={() => handleDestinySelected(i.id)}
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
          <h1>Carregando a lista de clientes</h1>
        </div>
      )}
    </>
  );
};

export default TableDestinos;
