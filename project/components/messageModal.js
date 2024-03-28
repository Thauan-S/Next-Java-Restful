import React, { useState } from "react";
import axios from "axios";
import { headers } from "@/next.config";

const MessageModal = ({ clienteMessage}) => {
  const[hidden,setHidden]=useState(true)
  const [message, setMessage] = useState({
    cliente: { id: "" },
    assunto: "",
    mensagem: "",
  });
  const handleInputChange = (e) => {
    console.log(message);
    setMessage({ ...message, [e.target.name]: e.target.value });
  };
  const handleAddMessage = async (e) => {
    try {
      const token = window.localStorage.getItem("token");
      await axios.post("http://localhost:80/api/contatos/v1", message, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setHidden(!hidden)
    } catch (error) {
     
      console.error("Error sending message:", error);
    }
  };
  
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
                <div className="alert alert-primary" hidden={hidden} role="alert">
                  Mensagem Enviada com Sucesso!
                </div>

                <div className="mb-3">
                  
                  
                  <input
                    name="cliente"
                    onChange={handleInputChange}
                    value={message.cliente.id = clienteMessage.id}
                    hidden={false}
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

export default MessageModal;
