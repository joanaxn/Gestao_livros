# Sistema de Gestão de Biblioteca
Java • Spring Boot • React • MySQL • REST API

Aplicação fullstack para gestão de livros, utilizadores e empréstimos, desenvolvida em Java Spring Boot no backend e React no frontend, com persistência em MySQL.

## Objetivo
Permitir a gestão eficiente de uma biblioteca, garantindo:
1. Controlo de livros disponíveis
2. Gestão de utilizadores;
3. Regras claras de empréstimos
4. Interface simples e funcional

## Funcionalidades Principais
### Livros
- Adicionar livros ao catálogo;
- Listar todos os livros;
- Listar apenas livros disponíveis para empréstimo;
- Integração com a Google Books API para obtenção automática de capas;
- Verificação de disponibilidade em tempo real.

### Utilizadores
- Criar utilizadores;
- Autenticação simples (login);
- Dashboard do utilizador com empréstimos ativos.

### Empréstimos
- Registar empréstimos;
- Devolver empréstimos;
- Limite automático de 3 empréstimos ativos por utilizador;
- Atualização automática da disponibilidade do livro;
- Associação correta entre utilizador, livro e empréstimo.

## Tecnologias Utilizadas


### Backend
Java
Spring Boot
Spring Web
Spring Data JPA
Hibernate
MySQL
REST API

### Frontend
React
Axios
React Router
Gestão de estado e UI dinâmica

# Arquitetura
O backend segue uma arquitetura organizada por camadas:

Controller → Service → Repository
- Separação clara de responsabilidades
- Regras de negócio isoladas nos services
- Acesso à base de dados através de repositories
- Comunicação frontend ↔ backend via REST API

## Desafios Resolvidos
- Prevenção de loops JSON através do uso de DTOs
- Organização correta da API por camadas
- Gestão de estado no frontend
- Integração com API externa (Google Books)
- Controlo automático de regras de negócio (limite de empréstimos)

## Base de Dados
MySQL

Tabelas principais:
livros
users
emprestimos

- Relações entre utilizadores, livros e empréstimos geridas via JPA/Hibernate

## Como Executar o Projeto
### Backend
Abrir o projeto no IDE
Configurar a ligação à base de dados MySQL
Executar a classe principal do Spring Boot

### Frontend
cd biblioteca-frontend
npm install
npm start
