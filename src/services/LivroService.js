import axios from "axios";

const API_URL = "http://localhost:8080/livros";

export default {
  async listarTodos() {
    const res = await axios.get(`${API_URL}/todos`);
    return res.data;
  },

  async listarDisponiveis() {
    const res = await axios.get(`${API_URL}/disponiveis`);
    return res.data;
  },

  async adicionarLivro(livro) {
    const res = await axios.post("http://localhost:8080/livros/adicionar", livro);
    return res.data;
  }
};
