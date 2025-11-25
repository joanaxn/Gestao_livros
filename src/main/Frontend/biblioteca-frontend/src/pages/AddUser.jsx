import { useState } from "react";
import UserService from "../services/UserService";

export default function UsersPage() {

  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [contacto, setContacto] = useState("");
  const [password, setPassword] = useState("");

  async function adicionarUser() {
    if (!nome || !email || !contacto || !password) {
      alert("Preenche todos os campos pf");
      return;
    }

    const newUser = { nome, email, contacto, password };
    const result = await UserService.adicionarUser(newUser);

    alert(result);

    setNome("");
    setEmail("");
    setContacto("");
    setPassword("");
  }

  return (
    <div style={{ padding: "30px", maxWidth: "500px", margin: "0 auto" }}>

      <h1 style={{ textAlign: "center", marginBottom: "20px" }}>
        Adicionar User
      </h1>

      {/* Nome */}
      <label><strong>Nome</strong></label>
      <input
        type="text"
        value={nome}
        onChange={(e) => setNome(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />

      {/* Email */}
      <label><strong>Email</strong></label>
      <input
        type="text"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />

      {/* Contacto */}
      <label><strong>Contacto</strong></label>
      <input
        type="number"
        value={contacto}
        onChange={(e) => setContacto(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />


       {/* Password */}
            <label><strong>Password</strong></label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              style={{ width: "100%", marginBottom: "15px" }}
            />


      {/* Botão */}
      <button
        style={{
          width: "100%",
          padding: "10px",
          background: "#1A2A6C",
          color: "white",
          fontWeight: "bold",
          border: "none",
          borderRadius: "5px",
          cursor: "pointer"
        }}
        onClick={adicionarUser}
      >
        Adicionar User
      </button>

    </div>
  );
}
