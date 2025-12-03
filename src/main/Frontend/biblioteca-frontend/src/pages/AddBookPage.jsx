import { useState } from "react";
import LivroService from "../services/LivroService";
import BookImageService from "../services/BookImageService";

export default function AddBookPage() {

  const [titulo, setTitulo] = useState("");
  const [autor, setAutor] = useState("");
  const [ano, setAno] = useState("");
  const [isbn, setIsbn] = useState("");
  const [disponivel, setDisponivel] = useState(true);
  const [imagemPreview, setImagemPreview] = useState(null);

  async function buscarImagem() {
    if (!titulo) {
      alert("Insere primeiro um título pf");
      return;
    }

    const img = await BookImageService.getBookImage(titulo);

    if (!img) {
      alert("Não existe capa para o título procurado");
    }

    setImagemPreview(img);
  }

  async function adicionarLivro() {
    if (!titulo || !autor || !ano || !isbn) {
      alert("Preenche todos os campos pf");
      return;
    }

    // validar ISBN = exatamente 13 números
    if (!/^\d{13}$/.test(isbn)) {
      alert("O ISBN deve ter exatamente 13 números!");
      return;
    }


    // validar ANO = entre 1900 e 2025
    const anoNum = Number(ano);
    if (anoNum < 1900 || anoNum > 2025) {
      alert("O ano de publicação deve estar entre 1900 e 2025!");
      return;
    }

    const newBook = {
      isbn,
      titulo,
      autor,
      anoPublicacao: Number(ano),
      disponivel: disponivel
    };

    const result = await LivroService.adicionarLivro(newBook);
    alert(result);

    // limpar
    setTitulo("");
    setAutor("");
    setAno("");
    setIsbn("");
    setDisponivel(true);
    setImagemPreview(null);
  }




  return (
    <div style={{ padding: "30px", maxWidth: "500px", margin: "0 auto" }}>

      <h1 style={{ textAlign: "center", marginBottom: "20px" }}>
        Adicionar Livro
      </h1>

      <label><strong>Título</strong></label>
      <input
        type="text"
        value={titulo}
        onChange={(e) => setTitulo(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />

      <label><strong>Autor</strong></label>
      <input
        type="text"
        value={autor}
        onChange={(e) => setAutor(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />

      <label><strong>Ano Publicação</strong></label>
      <input
        type="number"
        value={ano}
        onChange={(e) => setAno(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}
      />

      <label><strong>ISBN</strong></label>
      <input
        type="text"
        value={isbn}
        maxLength={13}
        onChange={(e) => setIsbn(e.target.value)}
        style={{ width: "100%", marginBottom: "15px" }}

      />

      {/* DISPONÍVEL */}
      <div style={{ marginBottom: "15px" }}>
        <label>
          <strong>Disponível para Empréstimo?</strong>
        </label><br />

        <input
          type="checkbox"
          checked={disponivel}
          onChange={(e) => setDisponivel(e.target.checked)}
        />
      </div>

      <button
        style={{ marginBottom: "20px" }}
        onClick={buscarImagem}
      >
        Adicionar Capa
      </button>

      {imagemPreview && (
        <div style={{ textAlign: "center", marginBottom: "20px" }}>
          <img
            src={imagemPreview}
            alt="Preview"
            style={{
              width: "150px",
              height: "220px",
              objectFit: "cover",
              borderRadius: "8px",
            }}
          />
        </div>
      )}

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
        onClick={adicionarLivro}
      >
        Adicionar Livro
      </button>

    </div>
  );
}
