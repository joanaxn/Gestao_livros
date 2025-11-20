import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import BooksPage from "./pages/BooksPage";
import AddBookPage from "./pages/AddBookPage";
import UsersPage from "./pages/UsersPage";
import LoansPage from "./pages/LoansPage";
import LoanReturnPage from "./pages/LoanReturnPage";
import Navbar from "./components/Navbar";

export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/livros" element={<BooksPage />} />
        <Route path="/livros/adicionar" element={<AddBookPage />} />
        <Route path="/users" element={<UsersPage />} />
        <Route path="/emprestimos" element={<LoansPage />} />
        <Route path="/emprestimos/devolver" element={<LoanReturnPage />} />
      </Routes>
    </BrowserRouter>
  );
}
