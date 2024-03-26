import React from "react";

export const Modal = ({cliente, clienteMessage}) => {
  if (cliente) {
    return (
      <>
        <button
          type="button"
          className="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#exampleModalEdit"
        >
          <i className="bi bi-gear-fill" />
        </button>
        <div
          className="modal fade"
          id="exampleModalEdit"
          tabIndex={-1}
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h1 className="modal-title fs-5" id="exampleModalLabel">
                  Editar cliente
                </h1>
                <button
                  type="button"
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
                    Email address
                  </label>
                  <input
                    value={""}
                    readOnly
                    type="text"
                    className="form-control"
                    id="exampleInputEmail1"
                    aria-describedby="emailHelp"
                  />
                  <input
                    name="cliente"
                    onChange={handleInputChange}
                    value={""}
                    hidden={true}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPassword1" className="form-label">
                    Assunto
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="assunto"
                    value={""}
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPassword1" className="form-label">
                    Mensagem
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="mensagem"
                    value={""}
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
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
  } else if (clienteMessage) {
    return (
      <>
        <button
          type="button"
          className="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal"
        >
          <i className="bi bi-chat-dots-fill" />
        </button>
        <div
          className="modal fade"
          id="exampleModal"
          tabIndex={-1}
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h1 className="modal-title fs-5" id="exampleModalLabel">
                  Enviar mensagem
                </h1>
                <button
                  type="button"
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
                  Mensagem Enviada com Sucesso!
                </div>

                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Email address
                  </label>
                  <input
                    value={""}
                    readOnly
                    type="text"
                    className="form-control"
                    id="exampleInputEmail1"
                    aria-describedby="emailHelp"
                  />
                  <input
                    name="cliente"
                    onChange={handleInputChange}
                    value={(message.cliente.id = clienteMessage.id)}
                    hidden={true}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPassword1" className="form-label">
                    Assunto
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="assunto"
                    value={message.assunto}
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPassword1" className="form-label">
                    Mensagem
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="mensagem"
                    value={message.mensagem}
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                  />
                </div>
                <button
                  type="submit"
                  onClick={handleAddMessage}
                  className="btn btn-primary"
                >
                  Enviar
                </button>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
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
  }
};
