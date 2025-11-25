package gestao_biblioteca.models;

import java.util.ArrayList;

public class User {
    private int id;
    private String nome;
    private String email;
    private String contacto;
    private String password;

    private ArrayList<Emprestimo> livrosemprestimos;


    public User() {
        this.livrosemprestimos = new ArrayList<>();
    }

    // getters e setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Emprestimo> getLivrosemprestimos() {
        return livrosemprestimos;
    }

    public void setLivrosemprestimos(ArrayList<Emprestimo> livrosemprestimos) {
        livrosemprestimos = livrosemprestimos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", contacto='" + contacto + '\'' +
                ", Livrosemprestimos=" + livrosemprestimos +
                '}';
    }
}
