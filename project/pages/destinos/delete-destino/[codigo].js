import { useRouter } from "next/router";
import React, { useState } from "react";
import axios from "axios";
const DeleteDestino = () => {
  const router = useRouter();
  const { codigo } = router.query;
  const [packageId, setpackageId] = useState(codigo);
  console.log(packageId);
  const handleDeleteDestiny = () => {
    axios
      .delete(`https://localhost:7250/api/PacoteDeViagens/${packageId}`)
      .then(() => {
        router.push("/destinos");
      })
      .catch((error) => {
        alert("erro ao deletar pacote" + error);
      });
  };
  return (
    <div style={{ minHeight: "100vh" }}>
      <h1 className={""}>Excluir Pacote</h1>
      <table style={{ marginLeft: "20px" }}>
        <tbody>
          <tr>
            <td>
              <label>ID do Pacote a ser excluído:</label>
            </td>
            <td>
              <input
                type="text"
                value={packageId}
                onChange={(e) => setpackageId(e.target.value)}
              />
            </td>
          </tr>
          <tr>
            <td colSpan="2">
              <button className="btn btn-primary" onClick={handleDeleteDestiny}>
                Excluir Pacote
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default DeleteDestino;
