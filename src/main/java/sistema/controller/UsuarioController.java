/**
 * Faculdade Anhambi Morumbi
 * Curso: Análise e Desenvolvimento de Sistemas
 * Período: 2025-2 N
 * 
 * Controlador para gerenciamento de usuários no sistema
 * 
 * @author Ivan Lemos
 * @matricula ******2541
 * @version 1.0
 */
package sistema.controller;

import java.util.ArrayList;
import java.util.List;
import sistema.model.Evento;
import sistema.model.Usuario;
import sistema.util.FileManager;

public class UsuarioController {
    private final List<Usuario> usuarios;

    public UsuarioController() {
        this.usuarios = FileManager.carregarUsuarios();
    }

    public Usuario cadastrarUsuario(String nome, String email, String telefone) {
        // Verifica se já existe um usuário com este email
        if (buscarUsuarioPorEmail(email) != null) {
            return null; // Usuário já existe
        }

        Usuario usuario = new Usuario(nome, email, telefone);
        usuarios.add(usuario);
        FileManager.salvarUsuarios(usuarios);
        return usuario;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<Evento> listarEventosInscritos(Usuario usuario) {
        return usuario.getEventosInscritos();
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public void salvarUsuarios() {
        FileManager.salvarUsuarios(usuarios);
    }
}
