package models;

import models.Emprestimo;

import java.util.ArrayList;

public class User {
    private int id;
    private String nome;
    private String email;
    private String contacto;
    private ArrayList<Emprestimo> Livrosemprestimos;

    public User(int id, String nome, String email, String contacto, ArrayList<Emprestimo> livrosemprestimos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.contacto = contacto;
        Livrosemprestimos = livrosemprestimos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public ArrayList<Emprestimo> getLivrosemprestimos() {
        return Livrosemprestimos;
    }

    public void setLivrosemprestimos(ArrayList<Emprestimo> livrosemprestimos) {
        Livrosemprestimos = livrosemprestimos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", contacto='" + contacto + '\'' +
                ", Livrosemprestimos=" + Livrosemprestimos +
                '}';
    }
}