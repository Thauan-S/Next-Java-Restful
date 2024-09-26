import { GlobalContext } from "@/contexts/appContext";
import axios from "axios";
import { useRouter } from "next/router";
import React, { useState, useEffect, useContext } from "react";

const CriarPacote = () => {
  const {
    globalState: { token, username },
    urlPackage: { url },
  } = useContext(GlobalContext);
  const [newPackage, setNewPackage] = useState({
    destino: "",
    descricao: "",
    duracaoEmDias: "",
    preco: "",
    categoria: "",
    imagem: "",
    enterprise: {
      user: {
        email: username,
      },
    },
  });
  const router = useRouter();
  const handleInputChange = (e) => {
    setNewPackage({ ...newPackage, [e.target.name]: e.target.value });
  };
  const handleAddPackage = () => {
    axios
      .post(`${url}`, newPackage, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        router.push("/destinos/lista");
      });
  };

  return (
    <main>
      <div className="container formulario">
        <div className="form-floating mt-4">
          <input
            className="form-control"
            name="destiny"
            placeholder="Leave a comment here"
            id="floatingTextarea"
            onChange={handleInputChange}
            value={newPackage.destiny}
          />
          <label htmlFor="floatingTextarea">Destino</label>
        </div>
        <div className="form-floating">
          <input
            className="form-control mt-1"
            name="description"
            placeholder="Leave a comment here"
            id="floatingTextarea2"
            onChange={handleInputChange}
            style={{ height: 100 }}
            value={newPackage.description}
          />
          <label htmlFor="floatingTextarea2">Descrição </label>
        </div>
        <div className="form-floating">
          <input
            className="form-control mt-1"
            name="days"
            placeholder="Leave a comment here"
            id="floatingTextarea2"
            onChange={handleInputChange}
            value={newPackage.days}
          />
          <label htmlFor="floatingTextarea2">Duracão em Dias </label>
        </div>
        <div className="form-floating">
          <input
            className="form-control mt-1"
            name="price"
            placeholder="Leave a comment here"
            id="floatingTextarea2"
            style={{ height: 100 }}
            onChange={handleInputChange}
            value={newPackage.price}
          />
          <label htmlFor="floatingTextarea2">Preço </label>
        </div>
        <div className="form-floating">
          <input
            type="text"
            className="form-control mt-1"
            name="image"
            placeholder="Leave a comment here"
            id="floatingTextarea2"
            value={newPackage.image}
            onChange={handleInputChange}
            style={{ height: 10 }}
          />
          <label htmlFor="floatingTextarea2">Imagem </label>
        </div>
        <div className="form-floating">
          <input
            className="form-control mt-1"
            name="category"
            placeholder="Leave a comment here"
            id="floatingTextarea2"
            onChange={handleInputChange}
            style={{ height: 10 }}
            value={newPackage.category}
          />
          <label htmlFor="floatingTextarea2">categoria </label>
        </div>
        <button
          onClick={handleAddPackage}
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#collapseExample"
          aria-expanded="false"
          aria-controls="collapseExample"
          className="btn btn-primary mt-2 mb-2"
        >
          Enviar
        </button>
        <div className="collapse mt-1" id="collapseExample">
          <div className="card alert alert-success text-center card-body">
            Dados enviados com sucesso
          </div>
        </div>
      </div>
    </main>
  );
};

export default CriarPacote;
