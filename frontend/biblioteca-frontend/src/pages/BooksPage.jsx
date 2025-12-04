import { useEffect, useState } from "react";
import LivroService from "../services/LivroService";
import BookCard from "../components/BookCard";
import "./BooksPage.css";



export default function BooksPage() {
  const [livros, setLivros] = useState([]);

  useEffect(() => {
    async function carregarLivros() {
      const lista = await LivroService.listarTodos();
      setLivros(lista);
    }

    carregarLivros();
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
