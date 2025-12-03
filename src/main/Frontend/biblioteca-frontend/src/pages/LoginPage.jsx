import { useState } from "react";
import UserService from "../services/UserService";
import { useNavigate } from "react-router-dom";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  async function fazerLogin() {
    if (!email || !password) {
      alert("Preenche email e password.");
      return;
    }

    const user = await UserService.login(email, password);

    if (!user || user === "Credenciais inválidas") {
      alert("Credenciais inválidas");
      return;
    }

    // guardar user no localStorage
    localStorage.setItem("user", JSON.stringify(user));

    // navegar para dashboard
    navigate("/user/dashboard");

    // força navbar a atualizar (importante!)
    window.location.reload();
  }

  return (
    <div style={{ padding: "30px", maxWidth: "400px", margin: "0 auto" }}>
      <h1 style={{ textAlign: "center" }}>Login Utilizador</h1>

      <label>Email</label>
      <input
        type="text"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />

      <label>Password</label>
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />

      <button
        onClick={fazerLogin}
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
      >
        Entrar
      </button>
    </div>
  );
}
