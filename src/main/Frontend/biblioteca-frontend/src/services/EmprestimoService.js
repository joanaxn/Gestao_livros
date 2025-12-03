import axios from "axios";

const API_URL = "http://localhost:8080/emprestimos";

const EmprestimoService = {

  // REGISTAR EMPRÉSTIMO (já tinhas)
  async registarEmprestimo(body) {
    const res = await axios.post(`${API_URL}/emprestar`, body);
    return res.data;
  },

  // NOVO: CONFIRMAR EMPRÉSTIMO
  async confirmarEmprestimo(id) {
    const res = await axios.put(`${API_URL}/${id}/confirmar`);
    return res.data;
  }
};

export default EmprestimoService;
