import React, { useEffect, useState } from "react";
import LivroService from "../services/LivroService";
import BookImageService from "../services/BookImageService";

function LoansPage() {
  const [livros, setLivros] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const data = await LivroService.listarDisponiveis();

      // Vai buscar a imagem para cada livro
      const livrosComImagem = await Promise.all(
        data.map(async (livro) => {
          const img = await BookImageService.getBookImage(livro.titulo);
          return { ...livro, imagem: img };
        })
      );

      setLivros(livrosComImagem);
    }

    fetchData();
  }, []);

  return (
     <div style={{ padding: "20px" }}>
          <h1 style={{ textAlign: "center", marginBottom: "30px" }}>
            Livros Disponíveis
          </h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(180px, 1fr))",
          gap: "25px",
          justifyItems: "center",
          maxWidth: "1200px",
          margin: "0 auto",
        }}
      >

        {livros.map((livro) => (
          <div
            key={livro.isbn}
            style={{
              width: "160px",
              textAlign: "center",
            }}
          >
            <img
              src={livro.imagem}
              alt={livro.titulo}
              style={{
                width: "150px",
                height: "220px",
                objectFit: "cover",
                borderRadius: "5px",
                marginBottom: "8px",
              }}
            />
            <p style={{ fontWeight: "bold" }}>{livro.titulo}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default LoansPage;
