import React,{useState} from 'react'
import styles from "../styles/createClient.module.css"
const CreateEnterprise = () => {
  const [enterprise, setEnterprise] = useState({
    name: "",
    cnpj: "",
    address: "",
    user: {
      email: "",
      password: "",
    },
  });
  
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
  const handleInputEnterpriseChange = (e) => {
    console.log(e.target)
    if (e.target.name === "email") {
      setEnterprise((enterprise.user.email = e.target.value));
    } else if (e.target.name === "password") {
      setEnterprise((enterprise.user.password = e.target.value));
    }
    setEnterprise({ ...enterprise, [e.target.name]: e.target.value });
  };
  return (
    <div className={`container-fluid`}>
   <form className="row g-3 container-fluid">
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
  </div>
  )
}

export default CreateEnterprise