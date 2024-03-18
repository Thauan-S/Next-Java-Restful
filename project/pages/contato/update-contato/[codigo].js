import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import axios from "axios";
import HeadComponent from "@/components/head";
import Contato from "../lista";
const UpdateContato = () => {
  const router = useRouter();
  const { codigo } = router.query;
  const [contato, setContato] = useState({
    assunto: "",
    mensagem: "",
    clienteId: ""
  });
  const [contatoId, setContatoId] = useState(codigo);

  useEffect(() => {
    axios
      .get(`https://localhost:7250/api/Contatos/${contatoId}`)
      .then((response) => {
        console.log("re",response);
        setContato(response.data);
      })
      .catch((error) => {
        console.error("erro ao buscar o contato", error);
      });
  }, []);

  const handleInputChange = (e) => {
    setContato({ ...contato, [e.target.name]: e.target.value });
  };

  console.log(contato);
  console.log("cliente", contato);

  const handleUpdateContato = () => {
    axios
      .put(`https://localhost:7250/api/Contatos/${contatoId}/`,contato)
      .then((response) => {
        router.push("/contato/lista");
      })
      .catch((error) => {
        console.error("erro ao atualizar o contato", error);
      });
  };
  console.log("contato", contato);
  return (
    <>
      <HeadComponent title={"Contato | Edição"} />
      <main>
        <fieldset className="container-fluid" disabled="">
          <legend className="text-center">Edição do Contato</legend>
          <div className="mb-3 col-4 col-md-1 ">
            <label htmlFor="disabledTextInput" className="form-label">
              Contato id
            </label>
            <input
              type="text"
              value={contatoId}
              disabled
              id="disabledTextInput"
              className="form-control text-center"
              placeholder="Disabled input"
            />
          </div>
          <div className="mb-3 col-4 col-md-1">
            <label htmlFor="disabledSelect" className="form-label">
              Cliente Id
            </label>
            <input
              type="text"
              name="clienteId"
              value={contato.clienteId}
              onChange={handleInputChange}
              id="disabledTextInput"
              className="form-control text-center"
              placeholder="Disabled input"
            />
          </div>
          <div className="mb-3 col-10 col-sm-2">
            <label className="form-check-label" htmlFor="disabledFieldsetCheck">
              Assunto
            </label>
            <input
              className="form-control"
              type="text"
              name="assunto"
              value={contato.assunto}
              onChange={handleInputChange}
              id="disabledFieldsetCheck"
            />
          </div>
          <div className="mb-3 row">
            <textarea
              style={{ marginLeft: "11px", borderRadius: "5px" }}
              value={contato.mensagem}
              name="mensagem"
              onChange={handleInputChange}
              className="col-11 col-sm-5"
              rows="3"
              id="textarea"
            ></textarea>
          </div>
          <button onClick={handleUpdateContato} className="btn btn-primary">
            Submit
          </button>
        </fieldset>
      </main>
    </>
  );
};

export default UpdateContato;
