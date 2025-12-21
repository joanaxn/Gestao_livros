import { useEffect, useState } from "react";
import axios from "axios";
import bookImagesService from "../services/BookImageService";

export default function UserDashboard() {
  const [emprestimos, setEmprestimos] = useState([]);

  const user = JSON.parse(localStorage.getItem("user"));

  async function carregarEmprestimos() {
    if (!user) return;

    try {
      const res = await axios.get(
        `http://localhost:8080/user/${user.id}/emprestimosAtivos`
      );

      const lista = await Promise.all(
        res.data.map(async (emp) => {
          const img = await bookImagesService.getBookImage(emp.livro.titulo);
          return { ...emp, imagem: img ?? null };
        })
      );

      setEmprestimos(lista);
    } catch (err) {
      console.error("Erro a carregar empréstimos:", err);
    }
  }

  useEffect(() => {
    carregarEmprestimos();
  }, []);

  if (!user) {
    return <h2 style={{ textAlign: "center" }}>Não estás autenticado.</h2>;
  }

  return (
    <div style={{ padding: "20px", position: "relative" }}>
      <h1 style={{ textAlign: "center" }}>Os meus empréstimos</h1>

      {emprestimos.length === 0 ? (
        <p style={{ textAlign: "center", marginTop: "20px" }}>
          Não tens empréstimos activos!
        </p>
      ) : (
        <div
          style={{
            display: "flex",
            flexWrap: "wrap",
            gap: "30px",
            justifyContent: "center",
          }}
        >
          {emprestimos.map((emp) => (
            <div
              key={emp.idEmprestimo}
              style={{
                width: "180px",
                padding: "10px",
                background: "#f9f9f9",
                borderRadius: "8px",
                boxShadow: "0 2px 6px rgba(0,0,0,0.1)",
                textAlign: "center",
              }}
            >
              <img
                src={emp.imagem}
                alt={emp.livro.titulo}
                style={{
                  width: "150px",
                  height: "220px",
                  objectFit: "cover",
                  borderRadius: "5px",
                }}
              />

              <h3 style={{ fontSize: "16px" }}>{emp.livro.titulo}</h3>

              <p style={{ color: "#555" }}>
                Emprestado em: <br />
                <strong>{emp.dataEmprestimo}</strong>
              </p>

              <button
                onClick={async () => {
                  const body = {
                    idEmprestimo: emp.idEmprestimo,
                    dataDevolucao: new Date()
                      .toISOString()
                      .split("T")[0],
                  };

                  try {
                    const resposta = await axios.post(
                      "http://localhost:8080/emprestimos/devolver",
                      body
                    );

                    alert(resposta.data);

                    // REMOVE IMEDIATAMENTE SEM REFRESH
                    setEmprestimos((prev) =>
                      prev.filter(
                        (e) => e.idEmprestimo !== emp.idEmprestimo
                      )
                    );
                  } catch (err) {
                    console.error("Erro ao devolver:", err);
                    alert("Erro ao devolver o livro.");
                  }
                }}
                style={{
                  width: "100%",
                  padding: "8px",
                  background: "#cc0000",
                  color: "white",
                  border: "none",
                  borderRadius: "5px",
                  cursor: "pointer",
                  fontWeight: "bold",
                }}
              >
                Devolver
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
