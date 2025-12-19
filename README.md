# Sistema de Gestão de Biblioteca

Autora : Joana Nogueira

Desenvolvedora fullstack com maior foco em backend, apaixonada por criar soluções funcionais e bem estruturadas.

Esta é uma aplicação fullstack desenvolvida em **Java Spring Boot** e **React**, com base de dados **MySQL**, que permite gerir livros, utilizadores e empréstimos de forma eficiente e intuitiva.


---
## Funcionalidades

### **Livros**
- Adicionar livros
- Listar todos os livros no catálogo
- Pesquisa automática de capas via **Google Books API**
- Listar apenas livros disponíveis na página de empréstimos
- Validação de ISBN

---

### **Utilizadores**
- Criar utilizadores
- Login simples
- Validação de contacto e email
- Dashboard com empréstimos ativos

---

### **Empréstimos**
- Registar empréstimo
- Devolver empréstimo
- Limite automático de **3 empréstimos ativos por utilizador**
- Atualização automática da disponibilidade do livro
- Modal para selecionar utilizador e data do empréstimo

---

### **Frontend **
- Interface limpa e moderna
- Galeria com miniaturas e **lightbox**
- Validações de formulários
- Navegação com **React Router**


---

## Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Hibernate

### Frontend
- React
- Axios
- React Router
- CSS Modules

---
## Arquitetura & Backend

- Estruturação de API REST seguindo boas práticas
- Separação correta de responsabilidades (**Controller → Service → Repository**)
- Prevenção de loops JSON através de DTOs
- Integração com **MySQL + JPA + Hibernate**
- Implementação de validações no backend

---

## Frontend & Integração

- Gestão de estado com React
- Integração com APIs REST
- Validações de formulários
- Integração com **Google Books API**
- Tratamento de erros e melhorias de UX


---

## Base de Dados

### Tabelas criadas automaticamente pelo Hibernate:
- **users**
- **livros**
- **emprestimos**

Chaves e relações:
- User (1) → (N) Emprestimos
- Livro (1) → (N) Emprestimos

---

##  Como correr o projeto

### Backend
```bash
cd Gestao_livros
mvn spring-boot:run

O backend arranca em:
http://localhost:8080


Frontend
cd biblioteca-frontend
npm install
npm start

Frontend disponível em:
http://localhost:3000
