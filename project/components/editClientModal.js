import React, { useEffect, useState } from "react";
import axios from "axios";

import useGetClients from "@/hooks/useGetClients";
import { useRouter } from "next/router";
const EditClientModal = ({
  cli,
  showModal,
  token,
  setShowModal,
  username,
  setClientes,
  setUpdate
}) => {

  const router = useRouter();
  const [hidden, setHidden] = useState(true);
  const [papeis, setPapeis] = useState(["EMPRESA", "BASIC", "ADMIN"]);
  const [cliente, setCliente] = useState({
    clienteId: "",
    nome: "",
    telefone: "",
    cep: "",
    dataNascimento: "",
    user: {
      username: "",
      password: "",
      roles: [
        {
          roleId: "",
          name: "",
        },
      ],
    },
  });
  const handleInputChange = (e) => {
    setCliente({ ...cliente, [e.target.name]: e.target.value });
  };
  useEffect(() => {
    axios
      .get("http://localhost:80/api/clientes/v1/" + cli.clienteId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setCliente(response.data);
      })
      .catch((error) => {
        console.error;
      });
  }, [cli.clienteId]);
  const handleEditClient = () => {
    axios
      .put("http://localhost:80/api/clientes/v1", cliente, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log(response.status);
        if(response.status==200){
          setUpdate((prevUpdate)=> !prevUpdate)
        }
        //router.reload();
      })
      .catch((error) => {
        console.error(error);
      });
    setHidden(!hidden);
  };

  // };
  // useEffect(() => {
  //   axios
  //     .get("http://localhost:80/api/clientes/v1", {
  //       headers: {
  //         Authorization: `Bearer ${window.localStorage.getItem("token")}`,
  //       },
  //     })
  //     .then((response) => {
  //       console.log("Response", response.data);
  //       setClientes(response.data.clientesList);
  //     })
  //     .catch((error) => {
  //       console.error("Erro ao Listar os Clientes", error);
  //     });
  // }, []);
  if (showModal) {
    return (
      <>
        <div
          className="modal fade show"
          style={{ display: "block" }}
          id="exampleModal"
          tabIndex={-1}
          aria-labelledby="exampleModalLabel"
          aria-hidden="hidden"
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h1 className="modal-title fs-5" id="exampleModalLabel">
                  Editar cliente <p>ID: {cliente.clienteId}</p>
                </h1>
                <button
                  type="button"
                  onClick={() => setShowModal(!showModal)}
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                />
              </div>
              <div className="modal-body">
                <div
                  className="alert alert-primary"
                  hidden={hidden}
                  role="alert"
                >
                  Cliente editado com Sucesso!
                </div>

                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Nome
                  </label>
                  <input
                    value={cliente.clienteId}
                    name="clienteId"
                    hidden={true}
                    readOnly
                    type="text"
                    className="form-control"
                    id="exampleInputNome"
                    aria-describedby="emailHelp"
                  />

                  <input
                    name="nome"
                    id="exampleInputEmail1"
                    className="form-control"
                    value={cliente.nome}
                    onChange={handleInputChange}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputDate" className="form-label">
                    Usuário
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="user"
                    value={cliente.user.username}
                    type="email"
                    className="form-control"
                    id="exampleInputDate"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPassword1" className="form-label">
                    Senha
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="user"
                    value={cliente.user.password}
                    type="password"
                    className="form-control"
                    id="exampleInputPassword1"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPhone" className="form-label">
                    Telefone
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="telefone"
                    value={cliente.telefone}
                    placeholder="(xx) xxxx-xxxx"
                    type="tel"
                    className="form-control"
                    id="exampleInputPhone"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputCep" className="form-label">
                    Cep
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="cep"
                    value={cliente.cep}
                    placeholder="xxxx-xxxx"
                    type="text"
                    className="form-control"
                    id="exampleInputCep"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputDate" className="form-label">
                    Data de Nascimento
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="dataNascimento"
                    value={cliente.dataNascimento.split("T")[0]}
                    type="date"
                    className="form-control"
                    id="exampleInputDate"
                  />
                </div>

                <button
                  type="submit"
                  onClick={handleEditClient}
                  className="btn btn-primary"
                >
                  Enviar
                </button>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  onClick={() => setShowModal(!showModal)}
                  className="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  } else return null;
};

export default EditClientModal;
