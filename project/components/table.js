import React, { useState,useContext } from "react";
import moment from "moment";
import axios from "axios";
import EditClientModal from "./editClientModal";
import { GlobalContext } from "@/contexts/appContext";
const Table = ({ clientes, reservas, contatos, destinos,setClientes,username,setUpdate }) => {
  const [id, setId] = useState('');
  const[showModal,setShowModal]=useState(false)
  const{globalState:{token}}=useContext(GlobalContext)
  
  
  
  const [clienteSelecionado, setClienteSelecionado] = useState("");

  const handleClienteSelecionado = (customerId) => {
    
    const cliente = clientes.find((cliente) => cliente.customerId === customerId);
    setClienteSelecionado(cliente);
    setShowModal(true)
  };
  const handleDeleteClient = (id) => {
    const response = confirm(`deseja excluir o cliente de id ${id} ?`);

    if (response == true) {
      setId(id);
      axios
        .delete("https://next-java-restful-tropical-back-end.onrender.com/api/clients/v1/" + id, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          if(response.status==204){
            setUpdate((prevUpdate)=>!prevUpdate)
          }
        })
        .catch((error) => {
          console.error("erro ao excluir um cliente", error);
        });
    }
  };

  if (clientes) {
    return (
      <div className="table-responsive">
        <table className="table table-hover">
          <thead>
            <tr>
              <th scope="col"># ID</th>
              <th scope="col">Nome</th>
              <th scope="col">Telefone</th>
              <th scope="col">Cep</th>
              <th scope="col">Data de Nascimento</th>
              <th scope="col">Ações</th>
            </tr>
          </thead>
          <tbody>
            {clientes.map((i, index) => (
              <tr key={index}>
                <td>{i.customerId}</td>
                <td>{i.name}</td>
                <td>{i.phone}</td>
                <td>{i.zipCode}</td>
                <td>{moment(i.birthday).format("DD/MM/YYYY")}</td>
                <td>
                  <button
                     type="button"
                     className="btn btn-primary"
                    onClick={() => handleClienteSelecionado(i.customerId) }
                  >
                    <i className="bi bi-gear-fill" />
                  </button>
                 
                  <button
                    onClick={() => handleDeleteClient(i.customerId)}
                    type="button"
                    className="btn btn-primary"
                  >
                    <i className="bi bi-trash"></i>
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {clienteSelecionado && <EditClientModal setUpdate={setUpdate} cli={clienteSelecionado} token={token} showModal={showModal} setShowModal={setShowModal} setClientes={setClientes} username={username} />}
      </div>
    );
  } //else if (reservas) {
  //   return (
  //     <div className="table-responsive">
  //       <table className="table">
  //         <thead>
  //           <tr>
  //             <th scope="col">ID</th>
  //             <th scope="col">Cliente</th>
  //             <th scope="col">Data Reserva</th>
  //             <th scope="col">Data Viagem</th>
  //             <th scope="col">Pacote</th>
  //             <th scope="col">Ações</th>
  //           </tr>
  //         </thead>
  //         <tbody>
  //           {reservas.map((i, index) => (
  //             <tr key={index}>
  //               <td>{i.id}</td>
  //               <td>{i.clienteId}</td>
  //               <td>{moment(i.dataReserva).format("DD/MM/YYYY")}</td>
  //               <td>{moment(i.dataViagem).format("DD/MM/YYYY")}</td>
  //               <td>{i.pacoteDeViagemId}</td>
  //               <td>
  //                 <Link
  //                   href={`/reserva/update-reserva/${i.id}`}
  //                   type="button"
  //                   className="btn btn-primary"
  //                 >
  //                   <i className="bi bi-gear-fill" />
  //                 </Link>

  //                 <Link
  //                   href={`/reserva/delete-reserva/${i.id}`}
  //                   type="button"
  //                   className="btn btn-primary"
  //                 >
  //                   <i className="bi bi-trash"></i>
  //                 </Link>
  //               </td>
  //             </tr>
  //           ))}
  //         </tbody>
  //       </table>
  //     </div>
  //   );
  // } else if (contatos) {
  //   return (
  //     <div className="table-responsive">
  //       <table className="table">
  //         <thead>
  //           <tr>
  //             <th scope="col"># ID</th>
  //             <th scope="col">Cliente</th>
  //             <th scope="col">Assunto</th>
  //             <th scope="col">Mensagem</th>

  //             <th scope="col">Ações</th>
  //           </tr>
  //         </thead>
  //         <tbody>
  //           {contatos.map((i, index) => (
  //             <tr key={index}>
  //               <td>{i.id}</td>
  //               <td>{i.clienteId}</td>
  //               <td>{i.assunto}</td>
  //               <td>{i.mensagem}</td>

  //               <td>
  //                 <Link
  //                   href={`/contato/update-contato/${i.id}`}
  //                   type="button"
  //                   className="btn btn-primary"
  //                 >
  //                   <i className="bi bi-gear-fill" />
  //                 </Link>

  //                 <Link
  //                   href={`/contato/delete-contato/${i.id}`}
  //                   type="button"
  //                   className="btn btn-primary"
  //                 >
  //                   <i className="bi bi-trash"></i>
  //                 </Link>
  //               </td>
  //             </tr>
  //           ))}
  //         </tbody>
  //       </table>
  //     </div>
  //   );
  // }
  //  if (destinos) {
  //   return (
      
  //   );
   //}
};

export default Table;
