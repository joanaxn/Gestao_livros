import axios from "axios";

const API_URL = "http://localhost:8080/user";


const UserService = {
  async listar() {
    const res = await axios.get(`${API_URL}/listar`);
    return res.data;
  }
};

export default UserService;
