import { useEffect, useState } from "react";
import LivroService from "../services/LivroService";
import UserService from "../services/UserService";
import BookImageService from "../services/BookImageService";
import LoanModal from "../components/LoanModal";
import EmprestimoService from "../services/EmprestimoService";

export default function LoansPage() {

  const [livros, setLivros] = useState([]);
  const [users, setUsers] = useState([]);
  const [livroSelecionado, setLivroSelecionado] = useState(null);


  async function carregarDados() {

    //USERS
    const usersList = await UserService.listar();
    const usersArray = Array.isArray(usersList) ? usersList : [];
    setUsers(usersArray);

    // LIVROS
    const listaLivros = await LivroService.listarDisponiveis();

    const livrosComImagem = await Promise.all(
      listaLivros.map(async (livro) => {
        const img = await BookImageService.getBookImage(livro.titulo);
        return { ...livro, imagem: img ?? livro.imagem ?? null };
      })
    );

    setLivros(livrosComImagem);
  }

  useEffect(() => {
    carregarDados();
  }, []);


  // Confirmar empréstimo
  async function confirmarEmprestimo(userId, data) {
    const body = {
      userId: Number(userId),
      titulo: livroSelecionado.titulo,
      dataEmprestimo: data,
      idEmprestimo: Math.floor(Math.random() * 100000)
    };

    const resposta = await EmprestimoService.registarEmprestimo(body);
    alert(resposta);

    await carregarDados();
    setLivroSelecionado(null);
  }


  return (
    <div style={{ padding: "20px" }}>
      <h1 style={{ textAlign: "center", marginBottom: "30px" }}>
        Livros Disponíveis
      </h1>

      <div
        style={{
          display: "flex",
          gap: "50px",
          flexWrap: "wrap",
          justifyContent: "center"
        }}
      >
        {livros.map((livro) => (
          <div
            key={livro.isbn}
            style={{
              width: "160px",
              textAlign: "center",
              cursor: "pointer"
            }}
            onClick={() => setLivroSelecionado(livro)}
          >
            <img
              src={livro.imagem}
              alt={livro.titulo}
              style={{
                width: "150px",
                height: "220px",
                objectFit: "cover",
                borderRadius: "5px",
                marginBottom: "8px"
              }}
            />
            <p style={{ fontWeight: "bold" }}>{livro.titulo}</p>
          </div>
        ))}
      </div>

      {livroSelecionado && (
        <LoanModal
          livro={livroSelecionado}
          users={users}
          onClose={() => setLivroSelecionado(null)}
          onConfirm={confirmarEmprestimo}
        />
      )}
    </div>
  );
}
