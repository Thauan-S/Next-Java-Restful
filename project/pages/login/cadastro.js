import React, { useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import HeadComponent from "@/components/head";
import styles from "../../styles/cadastro.module.css";
const Cadastro = () => {
  const [hiddenFormClient, setHiddenFormClient] = useState(true);
  const [hiddenFormEnterprise, setHiddenFormEnterprise] = useState(true);
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
  const [enterprise, setEnterprise] = useState({
    name: "",
    cnpj: "",
    address: "",
    user: {
      email: "",
      password: "",
    },
  });

  const router = useRouter();
  const handleInputClientChange = (e) => {
    if (e.target.name === "email") {
      setClient((client.user.email = e.target.value));
    } else if (e.target.name === "password") {
      setClient((client.user.password = e.target.value));
    }
    setClient({ ...client, [e.target.name]: e.target.value });
  };
  const handleInputEnterpriseChange = (e) => {
    if (e.target.name === "email") {
      setEnterprise((enterprise.user.email = e.target.value));
    } else if (e.target.name === "password") {
      setEnterprise((enterprise.user.password = e.target.value));
    }
    setEnterprise({ ...enterprise, [e.target.name]: e.target.value });
  };
  console.log(client);
  const handleAddClient = () => {
    axios
      .post("https://next-java-restful-tropical-back-end.onrender.com/register/client", client)
      .then((response) => {
        router.push("/login");
      })
      .catch((error) => {
        console.error("erro ao cadastrar Usuário " + error);
      });
  };
  const handleAddEnteprise = () => {
    axios
      .post("https://next-java-restful-tropical-back-end.onrender.com/register/enterprise", enterprise)
      .then((response) => {
        router.push("/login");
      })
      .catch((error) => {
        console.error("erro ao cadastrar Empresa " + error);
      });
  };
  return (
    <>
      <HeadComponent title={"Tropical | Cadastro"} />
      <main>
        <div className={`${styles.div_signUp}`}>
          <button
            className="btn btn-primary"
            type="submit"
            onClick={() => setHiddenFormClient((prevHidden) => !prevHidden)}
          >
            <i className="bi bi-person-fill-add"> </i>  cliente
          </button>

          <button
            className="btn btn-primary"
            type="submit"
            onClick={() => setHiddenFormEnterprise((prevHidden) => !prevHidden)}
          >
            <i className="bi bi-building-add"> </i> empresa
          </button>
        </div>
        <div className={`${styles.div_client} container-fluid`}>
          <form hidden={hiddenFormClient} className="row g-3">
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

        <form hidden={hiddenFormEnterprise} className="row g-3">
        <div className="col-md-6">
              <label htmlFor="inputEmail4" className="form-label">
                Nome da empresa
              </label>
              <input
                value={enterprise.name}
                name="name"
                onChange={handleInputEnterpriseChange}
                type="text"
                className="form-control"
                id="enterpriseName"
              />
            </div>
            <div className="col-md-6">
              <div className="col-5">
                <label htmlFor="inputPassword4" className="form-label col-4">
                  Cnpj
                </label>
                <input
                  name="cnpj"
                  onChange={handleInputEnterpriseChange}
                  value={enterprise.cnpj}
                  type="text"
                  placeholder="000000000"
                  className="form-control"
                  id="enterprisecnpj"
                />
              </div>
            </div>
            <div className="col-md-2 col-5">
              <label htmlFor="inputAddress" className="form-label">
                Endereço
              </label>
              <input
                name="address"
                type="text"
                value={enterprise.address}
                onChange={handleInputEnterpriseChange}
                className="form-control"
                id="enterpriseAddres"
              />
            </div>
            <div className="col-md-12 col-10">
              <div className="col-4">
                <label htmlFor="inputCity" className="form-label">
                  Email
                </label>
                <input
                  name="email"
                  value={enterprise.email}
                  onChange={handleInputEnterpriseChange}
                  type="text"
                  className="form-control"
                  id="enterpriseEmail"
                />
              </div>
            </div>
            <div className="col-md-4 col-4">
              <label htmlFor="inputState" className="form-label">
                Senha
              </label>
              <input
              value={enterprise.password}
                name="password"
                onChange={handleInputEnterpriseChange}
                id="enterprisePassword"
                type="password"
                className="form-control"
                placeholder="******"
              />
            </div>
            <div className="col-12">
              <button
                type="submit"
                onClick={handleAddEnteprise}
                className="btn btn-primary"
              >
                Cadastrar Empresa
              </button>
            </div>
        </form>
      </main>
    </>
  );
};

export default Cadastro;
