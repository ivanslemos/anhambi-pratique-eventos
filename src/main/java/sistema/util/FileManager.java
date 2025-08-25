/**
 * Faculdade Anhambi Morumbi
 * Curso: Análise e Desenvolvimento de Sistemas
 * Período: 2025-2 N
 * 
 * Classe utilitária para gerenciamento de arquivos do sistema
 * 
 * @author Ivan Lemos
 * @matricula ******2541
 * @version 1.0
 */
package sistema.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import sistema.model.Evento;
import sistema.model.Usuario;

public class FileManager {
    private static final String DATA_DIR = "data";
    private static final String EVENTS_FILENAME = DATA_DIR + "/events.data";
    private static final String USERS_FILENAME = DATA_DIR + "/users.data";

    static {
        // Garante que o diretório data existe
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    public static void salvarEventos(List<Evento> eventos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(EVENTS_FILENAME))) {
            oos.writeObject(eventos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Evento> carregarEventos() {
        if (!new File(EVENTS_FILENAME).exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(EVENTS_FILENAME))) {
            return (List<Evento>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(USERS_FILENAME))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> carregarUsuarios() {
        if (!new File(USERS_FILENAME).exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(USERS_FILENAME))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
