import axios from "axios";

const API_URL = "http://localhost:8080/user";

const UserService = {

  async listar() {
    const res = await axios.get(`${API_URL}/listar`);
    return res.data;
  },

  async adicionarUser(user) {
    const res = await axios.post(`${API_URL}/adicionar`, user);
    return res.data;
  },

  async login(email, password) {
    const body = { email, password };
    const res = await axios.post(`${API_URL}/login`, body);
    return res.data;
  }

};

export default UserService;
