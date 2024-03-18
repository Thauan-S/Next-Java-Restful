import React, { useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
const ContatoCriar = () => {
  const router = useRouter();
  const { codigo } = router.query;
  const [contato, setContato] = useState({
    assunto: "",
    mensagem: "",
    clienteId:"",
  });
  const [clienteid, setClienteId] = useState(codigo);

  const handleInputChange = (e) => {
    setContato({ ...contato, [e.target.name]: e.target.value });
  };

  const handleAddContato = () => {
    axios
      .post("https://localhost:7250/api/Contatos",contato)
      .then((response) => {
        router.push("/contato/lista");
      })
      .catch((error) => {
        console.error("erro ao cadastrar um novo contato", error);
        
      });
  };
  console.log(contato);
  return (
    <main>
      <div className="container">
        <label htmlFor="floatingTextarea">Id Do cliente</label>
        <div className="form-floating  col-2 col-md-1">
          <input
            disabled
            className="form-control text-center"
            placeholder="Leave a comment here"
            id="floatingTextarea"
            name="idCliente"
            value={(contato.clienteId = codigo)}
          />
        </div>
        <div className="form-floating mt-4 col-5">
          <input
            type="text"
            className="form-control"
            name="assunto"
            placeholder="Leave a comment here"
            id="floatingTextarea"
            onChange={handleInputChange}
            value={contato.assunto}
          />
          <label htmlFor="floatingTextarea">Assunto</label>
        </div>

        <div
          style={{ marginLeft: "1px" }}
          className=" form-floating mt-4 ml-3  row"
        >
          <textarea
            className="form-control col-11 col-sm-5 "
            value={contato.mensagem}
            name="mensagem"
            onChange={handleInputChange}
            rows="3"
            id="textarea"
          ></textarea>
        </div>
        <button
          onClick={handleAddContato}
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

export default ContatoCriar;
