/**
 * Faculdade Anhambi Morumbi
 * Curso: Análise e Desenvolvimento de Sistemas
 * Período: 2025-2 N
 * 
 * Controlador para gerenciamento de eventos no sistema
 * 
 * @author Ivan Lemos
 * @matricula ******2541
 * @version 1.0
 */
package sistema.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import sistema.model.Categoria;
import sistema.model.Evento;
import sistema.model.Usuario;
import sistema.util.FileManager;

public class EventoController {
    private List<Evento> eventos;

    public EventoController() {
        this.eventos = FileManager.carregarEventos();
    }

    public void cadastrarEvento(String nome, String endereco, Categoria categoria,
                              LocalDateTime horario, String descricao) {
        Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
        eventos.add(evento);
        salvarEventos();
    }

    public List<Evento> listarEventos() {
        return eventos.stream()
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }

    public List<Evento> listarEventosProximos() {
        LocalDateTime agora = LocalDateTime.now();
        return eventos.stream()
                .filter(e -> !e.jaOcorreu())
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }

    public List<Evento> listarEventosPassados() {
        return eventos.stream()
                .filter(Evento::jaOcorreu)
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }

    public List<Evento> listarEventosOcorrendo() {
        return eventos.stream()
                .filter(Evento::isOcorrendo)
                .collect(Collectors.toList());
    }

    public void inscreverUsuarioEvento(Usuario usuario, Evento evento) {
        evento.adicionarParticipante(usuario);
        salvarEventos();
    }

    public void cancelarInscricaoUsuario(Usuario usuario, Evento evento) {
        evento.removerParticipante(usuario);
        salvarEventos();
    }

    private void salvarEventos() {
        FileManager.salvarEventos(eventos);
    }

    public Evento buscarEventoPorNome(String nome) {
        return eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
