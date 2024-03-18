import React, { useEffect, useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
const DeleteReserva = () => {
  const router = useRouter();
  const { codigo } = router.query;
  const [reservaId, setReservaId] = useState(codigo);
  const handleDeleteReserva = () => {
    axios
      .delete("https://localhost:7250/api/Reservas/" + reservaId)
      .then((response) => {
        router.push("/reserva/lista");
      })
      .catch((error) => {
        console.error("não foi possível excluir a reserva", error);
      });
  };

  return (
    <main>
      <h1>Reserva a ser excluida</h1>
      <table>
        <tr>
          <td>
            <label>Id da reserva a ser excluida:</label>
          </td>
          <td>
            <input type="text" value={reservaId} />
          </td>
        </tr>
        <tr>
          <td>
            <button className="btn btn-primary" onClick={handleDeleteReserva}>
              excluir
            </button>
          </td>
        </tr>
      </table>
    </main>
  );
};

export default DeleteReserva;
