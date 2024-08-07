import axios from "axios";
import React, { useState,useEffect } from "react";

const EditPackageModal = ({ idPackage, setModal, modal,token }) => {
  
  const [packageEdited, setPackageEdited] = useState({
    id: "",
    destino: "",
    descricao: "",
    categoria: "",
    imagem:"",
    preco:"",
    duracaoEmDias:""
  });
  useEffect(()=>{
    
    axios
    .get("http://localhost:80/api/pacotes/v1/"+idPackage,{
      headers:{
        Authorization:`Bearer ${token}`
      }
    })
    .then((response)=>{
      setPackageEdited(response.data)
    })
  },[idPackage])
  const handleInputChange = (e) => {
    setPackageEdited({...packageEdited, [e.target.name]: e.target.value})
  
  };
  const handlePackageEdit = () => {
    axios
    .put("http://localhost:80/api/pacotes/v1",packageEdited,{
      headers:{
        Authorization:`Bearer ${token}`
      }
    })
    .then((response)=>{
      
      console.log("O pacote foi editado ",response.data)
    })
  };
  return (
    <>
      {modal ? (
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
                  Editar Pacote de Viagem <p>ID: {packageEdited.id}</p>
                </h1>
                <button
                  type="button"
                  onClick={() => setModal(!modal)}
                  className="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                />
              </div>
              <div className="modal-body">
                <div
                  className="alert alert-primary"
                  hidden={"hidden"}
                  role="alert"
                >
                  Cliente editado com Sucesso!
                </div>

                <div className="mb-3">
                  <label htmlFor="exampleInputEmail1" className="form-label">
                    Destino
                  </label>
                  <input
                    value={packageEdited.id}
                    name="id"
                    hidden={true}
                    readOnly
                    type="text"
                    className="form-control"
                    id="exampleInputNome"
                    aria-describedby="emailHelp"
                  />

                  <input
                    name="destino"
                    id="exampleInputEmail1"
                    className="form-control"
                    value={packageEdited.destino}
                    onChange={handleInputChange}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputDate" className="form-label">
                    Categoria
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="categoria"
                    value={packageEdited.categoria}
                    type="text"
                    className="form-control"
                    id="exampleInputDate"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPassword1" className="form-label">
                    Descrição
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="descricao"
                    value={packageEdited.descricao}
                    type="text"
                    className="form-control"
                    id="exampleInputPassword1"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputPhone" className="form-label">
                    Preço
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="preco"
                    value={packageEdited.preco}
                    placeholder="1000"
                    type="number"
                    className="form-control"
                    id="exampleInputPhone"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputCep" className="form-label">
                    Duração da viagem
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="duracaoEmDias"
                    value={packageEdited.duracaoEmDias}
                    placeholder="3"
                    type="number"
                    className="form-control"
                    id="exampleInputCep"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="exampleInputDate" className="form-label">
                    Url da imagem
                  </label>
                  <input
                    onChange={handleInputChange}
                    name="imagem"
                    value={packageEdited.imagem}
                    type="text"
                    className="form-control"
                    id="exampleInputDate"
                  />
                </div>

                <button
                  type="submit"
                  onClick={handlePackageEdit}
                  className="btn btn-primary"
                >
                  Enviar
                </button>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  onClick={() => setModal(!modal)}
                  className="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
              </div>
            </div>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default EditPackageModal;
