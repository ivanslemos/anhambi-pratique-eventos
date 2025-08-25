/**
 * Faculdade Anhambi Morumbi
 * Curso: Análise e Desenvolvimento de Sistemas
 * Período: 2025-2 N
 * 
 * Classe que representa um evento no sistema
 * 
 * @author Ivan Lemos
 * @matricula ******2541
 * @version 1.0
 */
package sistema.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento implements Serializable {
    private String nome;
    private String endereco;
    private Categoria categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<Usuario> participantes;

    public Evento(String nome, String endereco, Categoria categoria, 
                 LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(Usuario usuario) {
        if (!participantes.contains(usuario)) {
            participantes.add(usuario);
            usuario.inscreverEvento(this);
        }
    }

    public void removerParticipante(Usuario usuario) {
        participantes.remove(usuario);
        usuario.cancelarInscricao(this);
    }

    public boolean isOcorrendo() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(horario) && agora.isBefore(horario.plusHours(2)); // Assumindo duração de 2 horas
    }

    public boolean jaOcorreu() {
        return LocalDateTime.now().isAfter(horario.plusHours(2));
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", categoria=" + categoria.getDescricao() +
                ", horario=" + horario +
                ", descricao='" + descricao + '\'' +
                ", número de participantes=" + participantes.size() +
                '}';
    }
}
