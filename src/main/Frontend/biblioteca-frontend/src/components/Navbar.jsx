import React from "react";
import "./Navbar.css";

export default function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-container">

        {/* LOGO */}
        <h1 className="logo">Biblioteca</h1>

        {/* LINKS */}
        <ul className="nav-links">
          <li><a href="/livros">Livros</a></li>
          <li><a href="/livros/adicionar">Adicionar Livro</a></li> {/* 🔥 ROTA CERTA */}
          <li><a href="/users">Utilizadores</a></li>
          <li><a href="/emprestimos">Empréstimos</a></li>
        </ul>

      </div>
    </nav>
  );
}
