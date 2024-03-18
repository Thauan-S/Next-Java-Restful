import HeadComponent from "@/components/head";
import axios from "axios";
import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";

const UpdateDestino = () => {
  const router = useRouter();
  const { codigo } = router.query;
  const [pacote, setPacote] = useState({
    id: "",
    destino: "",
    descricao: "",
    duracaoEmDias: "",
    preco: "",
    categoria: "",
    imagem: "",
  });

  useEffect(() => {
    axios
      .get(`https://localhost:7250/api/PacoteDeViagens/${pacote.id}`)
      .then((response) => {
        setPacote(response.data);
      })
      .catch((error) => {
        console.error("Erro ao buscar detalhes do pacote:", error);
      });
  }, [pacote.id]);

  const handleInputChange = (e) => {
    setPacote({ ...pacote, [e.target.name]: e.target.value });
  };

  const handleUpdatePacote = () => {
    axios
      .put("https://localhost:7250/api/PacoteDeViagens/" + pacote.id, pacote)
      .then((response) => {
        router.push("/destinos");
      })
      .catch((error) => {
        console.error("erro ao atualizar pacote", error);
      });
  };
  console.log(pacote);
  return (
    <>
      <HeadComponent title={"Contato | Edição"} />
      <main>
        <fieldset className="container-fluid" disabled="">
          <legend className="text-center">Edição do Contato</legend>
          <div className="mb-3 col-4 col-md-1 ">
            <label htmlFor="disabledTextInput" className="form-label">
              Pacote id
            </label>
            <input
              type="text"
              value={(pacote.id = codigo)}
              disabled
              id="disabledTextInput"
              className="form-control text-center"
              placeholder="Disabled input"
            />
          </div>
          <div className="mb-3 col-8 col-md-2">
            <label htmlFor="disabledSelect" className="form-label">
              Destino
            </label>
            <input
              type="text"
              name="destino"
              value={pacote.destino}
              onChange={handleInputChange}
              id="disabledTextInput"
              className="form-control text-center"
              placeholder="Disabled input"
            />
          </div>
          <div className="mb-3 col col-sm-10">
            <textarea
              value={pacote.descricao}
              name="descricao"
              onChange={handleInputChange}
              className="col-11 col-sm-5"
              rows="3"
              id="textarea"
            ></textarea>
          </div>
          <div className="mb-3 col-4 col-sm-10">
            <input
              value={pacote.preco}
              name="preco"
              onChange={handleInputChange}
              className="col-11 col-sm-5"
            />
          </div>
          <div className="mb-3 col-4 col-md-2">
            <label htmlFor="disabledSelect" className="form-label">
              Duração Em dias
            </label>
            <input
              type="text"
              name="duracaoEmDias"
              value={pacote.duracaoEmDias}
              onChange={handleInputChange}
              id="disabledTextInput"
              className="form-control text-center"
              placeholder="Disabled input"
            />
          </div>
          <div className="mb-3 col-4 col-md-2">
            <label htmlFor="disabledSelect" className="form-label">
              Categoria
            </label>
            <input
              type="text"
              name="categoria"
              value={pacote.categoria}
              onChange={handleInputChange}
              id="disabledTextInput"
              className="form-control text-center"
              placeholder="Disabled input"
            />
          </div>
          <div className="mb-3 col-4 col-md-2">
            <label htmlFor="disabledSelect" className="form-label">
              Imagem
            </label>
            <input
              type="text"
              name="imagem"
              value={pacote.imagem}
              onChange={handleInputChange}
              id="disabledTextInput"
              className="form-control text-center"
              placeholder="Disabled input"
            />
          </div>
          <div className="mb-3 row"></div>
          <button onClick={handleUpdatePacote} className="btn btn-primary">
            Submit
          </button>
        </fieldset>
      </main>
    </>
  );
};

export default UpdateDestino;
