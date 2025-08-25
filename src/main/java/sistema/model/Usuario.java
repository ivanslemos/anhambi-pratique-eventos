/**
 * Faculdade Anhambi Morumbi
 * Curso: Análise e Desenvolvimento de Sistemas
 * Período: 2025-2 N
 * 
 * Classe que representa um usuário no sistema
 * 
 * @author Ivan Lemos
 * @matricula ******2541
 * @version 1.0
 */
package sistema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private String telefone;
    private List<Evento> eventosInscritos;

    public Usuario(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.eventosInscritos = new ArrayList<>();
    }

    // Getters e Setters
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Evento> getEventosInscritos() {
        return eventosInscritos;
    }

    public void inscreverEvento(Evento evento) {
        if (!eventosInscritos.contains(evento)) {
            eventosInscritos.add(evento);
        }
    }

    public void cancelarInscricao(Evento evento) {
        eventosInscritos.remove(evento);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
