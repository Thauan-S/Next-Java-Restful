import { useRouter } from "next/router";
import React, { useState } from "react";
import axios from "axios";
import HeadComponent from "@/components/head";
const DeleteContato = () => {
  const router = useRouter();
  const { codigo } = router.query;
  const [contatoId, setContatoId] = useState(codigo);
  const handleDeleteContato = () => {
    axios
      .delete(`https://localhost:7250/api/Contatos/${contatoId}`)
      .then((response) => {
        router.push("/contato/lista");
      })
      .catch((error) => {
        console.error("erro ao deletar um contato");
      });
  };
  return (
    <>
      <HeadComponent title={"Contato | Exclusão"} />
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
          <button onClick={handleDeleteContato} className="btn btn-primary">
            Excluir
          </button>
        </fieldset>
      </main>
    </>
  );
};

export default DeleteContato;
