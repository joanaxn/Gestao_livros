import React from "react";

export default function LoanModal({ livro, users, onClose, onConfirm }) {

  const [userId, setUserId] = React.useState("");
  const [data, setData] = React.useState("");

  const today = new Date().toISOString().split("T")[0];


  if (!livro) return null;

  return (
    <div
      style={{
        position: "fixed",
        inset: 0,
        background: "rgba(0,0,0,0.5)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        zIndex: 1000
      }}
    >
      <div
        style={{
          background: "white",
          padding: "25px",
          width: "350px",
          borderRadius: "8px",
          textAlign: "center"
        }}
      >
        <h2>Registar Empréstimo</h2>

        <img
          src={livro.imagem}
          alt={livro.titulo}
          style={{
            width: "150px",
            height: "220px",
            objectFit: "cover",
            margin: "10px auto",
            borderRadius: "4px"
          }}
        />

        <h3>{livro.titulo}</h3>

        {/* Utilizador */}
        <div style={{ marginTop: "15px" }}>
          <label><strong>Utilizador:</strong></label>
          <br />
          <select
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            style={{ marginTop: "5px", width: "90%" }}
          >
            <option value="">Selecionar utilizador...</option>
            {users.map((u) => (
              <option key={u.id} value={u.id}>{u.nome}</option>
            ))}
          </select>
        </div>

        {/* Data */}
        <div style={{ marginTop: "15px" }}>
          <label><strong>Data Empréstimo:</strong></label>
          <br />
          <input
            type="date"
            value={data}
            onChange={(e) => setData(e.target.value)}
            min={today}
            style={{ marginTop: "5px", width: "90%" }}
          />
        </div>

        {/* Botões */}
        <div style={{ marginTop: "20px", display: "flex", gap: "10px", justifyContent: "center" }}>
          <button onClick={onClose}>Cancelar</button>

          <button
            onClick={() => onConfirm(Number(userId), data)}
            disabled={!userId || !data}
          >
            Confirmar
          </button>
        </div>
      </div>
    </div>
  );
}
