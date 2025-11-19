import { useEffect, useState } from "react";
import LivroService from "../services/LivroService";
import BookCard from "../components/BookCard";
import "./BooksPage.css";

export default function BooksPage() {
  const [livros, setLivros] = useState([]);

  useEffect(() => {
    async function fetchLivros() {
      console.log("📘 A pedir livros...");
      const data = await LivroService.listarTodos();
      console.log("📗 Livros recebidos:", data);
      setLivros(data);
    }
    fetchLivros();
  }, []);

  return (
    <div className="books-container">
      <h1>Catálogo de Livros</h1>

      <div className="books-grid">
        {livros.map((livro) => (
          <BookCard key={livro.isbn} livro={livro} />
        ))}

        {livros.length === 0 && <p>Nenhum livro encontrado.</p>}
      </div>
    </div>
  );
}
