import React,{useState} from 'react'
import styles from "../styles/createClient.module.css"
import axios from 'axios';
import { useRouter } from 'next/router';

const CreateClient = () => {
    const [client, setClient] = useState({
        name: "",
        phone: "",
        birthday: "",
        zipCode: "",
        user: {
          email: "",
          password: "",
        },
      });
      const router=useRouter()
      const handleInputClientChange = (e) => {
        console.log(e.target)
        if (e.target.name === "email") {
          setClient((client.user.email = e.target.value));
        } else if (e.target.name === "password") {
          setClient((client.user.password = e.target.value));
        }
        setClient({ ...client, [e.target.name]: e.target.value });
      };
      const handleAddClient = (e) => {
        e.preventDefault()
        axios
          .post("https://next-java-restful-tropical-back-end.onrender.com/register/client", client)
          .then((response) => {
            console.log(response.status)
            if(response.status==200)
            router.push("/login");
            
          })
          .catch((error) => {
            console.error("erro ao cadastrar Usuário " + error);
          });
      };
  return (
    <div className={`container-fluid`}>
    <form  className="row g-3">
      <div className="col-md-6">
        <label htmlFor="inputEmail4" className="form-label">
          Nome
        </label>
        <input
          value={client.name}
          name="name"
          onChange={handleInputClientChange}
          type="text"
          className="form-control"
          id="inputEmail4"
        />
      </div>
      <div className="col-md-6">
        <div className="col-5">
          <label htmlFor="inputPassword4" className="form-label col-4">
            Telefone
          </label>
          <input
            name="phone"
            onChange={handleInputClientChange}
            value={client.phone}
            type="tel"
            placeholder="(00)000000000"
            className="form-control"
            id="inputPhone"
          />
        </div>
      </div>
      <div className="col-md-2 col-5">
        <label htmlFor="inputAddress" className="form-label">
          Data de nascimento
        </label>
        <input
          name="birthday"
          type="date"
          onChange={handleInputClientChange}
          className="form-control"
          id="inputbirthday"
        />
      </div>
      <div className="col-md-4 col-5">
        <label htmlFor="inputAddress2" className="form-label">
          CEP
        </label>
        <input
          type="text"
          name="zipCode"
          onChange={handleInputClientChange}
          className="form-control"
          id="inputAddress2"
          placeholder="00000-0000"
        />
      </div>
      <div className="col-md-12 col-10">
        <div className="col-4">
          <label htmlFor="inputCity" className="form-label">
            Email
          </label>
          <input
            name="email"
            value={client.user.email}
            onChange={handleInputClientChange}
            type="text"
            className="form-control"
            id="inputCity"
          />
        </div>
      </div>
      <div className="col-md-4 col-4">
        <label htmlFor="inputState" className="form-label">
          Senha
        </label>
        <input
          name="password"
          onChange={handleInputClientChange}
          value={client.user.password}
          id="inputState"
          type="password"
          className="form-control"
          placeholder="******"
        />
      </div>
      <div className="col-12">
        <button
          type="submit"
          onClick={handleAddClient}
          className="btn btn-primary"
        >
          Cadastrar
        </button>
      </div>
    </form>
  </div>
  )
}

export default CreateClient