import { useEffect, useState } from "react";
import bookImagesService from "../services/BookImageService";
import "./BookCard.css";

function BookCard({ livro }) {

  const [imagem, setImagem] = useState(null);

  useEffect(() => {
    bookImagesService.getBookImage(livro.titulo)
      .then(img => setImagem(img));
  }, [livro.titulo]);

  return (
    <div className="book-card">
      <img
        src={imagem || "https://placehold.co/150x200?text=Sem+Imagem"}
        alt="capa do livro"
        className="book-image"
      />

      <h3>{livro.titulo}</h3>
      <p><strong>Autor:</strong> {livro.autor}</p>
      <p><strong>Ano:</strong> {livro.anoPublicacao}</p>
      <p><strong>ISBN:</strong> {livro.isbn}</p>
    </div>
  );
}

export default BookCard;
