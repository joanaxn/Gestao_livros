import React from "react";
import "./Navbar.css";

export default function Navbar() {

  const user = JSON.parse(localStorage.getItem("user"));

  function logout() {
    localStorage.removeItem("user");


    window.location.href = "/login";
  }

  return (
    <nav className="navbar">
      <div className="navbar-container">

        {  }
        <h1 className="logo">Biblioteca</h1>

        <ul className="nav-links">
          <li><a href="/livros">Catálogo de livros</a></li>
          <li><a href="/livros/adicionar">Adicionar Livro</a></li>
          <li><a href="/users">Criar Utilizador</a></li>
          <li><a href="/emprestimos">Empréstimos</a></li>

          {!user ? (
            <li><a href="/login">Login</a></li>
          ) : (
            <li>
              <a
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  localStorage.removeItem("user");
                  window.location.href = "/login";
                }}
              >
                Logout
              </a>
            </li>

          )}

        </ul>
      </div>
    </nav>
  );
}
