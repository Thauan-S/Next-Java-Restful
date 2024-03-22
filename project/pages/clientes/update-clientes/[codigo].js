import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import axios from "axios";
const UpdateClientes = () => {
  const route = useRouter();
  const { codigo } = route.query;
  
  const [cliente, setCliente] = useState({
    id: "",
    nome: "",
    email: "",
    senha: "",
    telefone: "",
    cep: "",
    dataNascimento: ""
  });
  const [token,setToken]=useState()
  const router = useRouter();
  useEffect(() => {
    setToken(window.localStorage.getItem('token'))
    axios
      .get("http://localhost:80/api/clientes/v1/" +cliente.id,{
      headers:{
        Authorization:`Bearer ${token}`
      }
    })
      .then((response) => {
        setCliente(response.data);
      })
      .catch((error) => {
        console.error("erro ao buscar o cliente de codigo", codigo);
      });
  }, [codigo,cliente.id,token]);

  const handleInputChange = (e) => {
    setCliente({ ...cliente, [e.target.name]: e.target.value });
  };
  const handleUpdateClient = () => {
    axios
      .put("http://localhost:80/api/clientes/v1",cliente,{
  headers:{
    Authorization:`Bearer ${token}`
  }})
      .then((response) => {
        router.push("/clientes/lista");
      })
      .catch((error) => {
        console.error("erro ao editar o cliente de id", codigo);
      });
  };
  return (
    <main>
      <div className="container formulario">
        <div className="row">
          <div className="mb-3 col-3 col-md-1">
            <label htmlFor="nome" className="form-label">
              Id
            </label>
            <input
              type="text"
              placeholder="Insira seu nome"
              className="form-control text-center"
              readOnly
              id="id"
              name="id"
              value={(cliente.id = codigo)}
              onChange={handleInputChange}
              aria-describedby="emailHelp"
              required=""
            />
          </div>
          <div className="mb-3 col">
            <label htmlFor="nome" className="form-label">
              Nome
            </label>
            <input
              type="text"
              placeholder="Insira seu nome"
              className="form-control "
              id="nome"
              name="nome"
              value={cliente.nome}
              onChange={handleInputChange}
              aria-describedby="emailHelp"
              required=""
            />
          </div>
          <div className="mb-3 col">
            <label htmlFor="telefone" className="form-label">
              Telefone
            </label>
            <input
              type="tel"
              placeholder="(00) 0000-0000"
              className="form-control"
              id="telefone"
              name="telefone"
              value={cliente.telefone}
              onChange={handleInputChange}
              required=""
            />
          </div>
          <div className="mb-3 col">
            <label htmlFor="Data de Nascimento" className="form-label">
              Data de Nascimento
            </label>
            <input
              type="date"
              placeholder="Insira seu telefone"
              className="form-control"
              id="Data de Nascimento"
              name="dataNascimento"
              value={cliente.dataNascimento}
              onChange={handleInputChange}
              required=""
            />
          </div>
        </div>
        <div className="mb-1 row">
          <div className="mb-3 col-6 col-md-2">
            <label htmlFor="cep" className="form-label">
              cep
            </label>
            <input
              type="text"
              pattern="[0-9]{5}-[0-9]{3}"
              placeholder="Insira seu telefone"
              className="form-control"
              id="cep"
              name="cep"
              value={cliente.cep}
              onChange={handleInputChange}
              required=""
            />
          </div>
        </div>
        <div className="row">
          <div className="col-9 col-md-3">
            <label htmlFor="email" className="form-label">
              Email{" "}
            </label>
            <input
              id="email"
              className=" col-7  col-md-3 form-control"
              type="email"
              placeholder="seuemail@exemplo.com"
              name="email"
              value={cliente.email}
              onChange={handleInputChange}
              aria-describedby="emailHelp"
            />
            <div id="emailHelp" className="form-text">
              Nunca compartilharemos seu email
            </div>
          </div>
        </div>
        <div className="mb-3 row">
          <div className="col-5 col-md-2">
            <label htmlFor="senha" className="form-label ">
              senha
            </label>
            <input
              id="senha"
              className=" col-4 col-md-1 form-control "
              type="password"
              placeholder="insira sua senha"
              name="senha"
              value={cliente.senha}
              onChange={handleInputChange}
              aria-describedby="senha"
              required
            />
          </div>
        </div>
        <button
          type="button"
          onClick={handleUpdateClient}
          data-bs-toggle="collapse"
          data-bs-target="#collapseExample"
          aria-expanded="false"
          aria-controls="collapseExample"
          className="btn btn-primary mt-2"
        >
          
          Cadastrar
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

export default UpdateClientes;
