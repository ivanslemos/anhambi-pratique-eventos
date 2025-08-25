/**
 * Universidade Anhembi Morumbi
 * Curso: Análise e Desenvolvimento de Sistemas
 * Período: 2025-2 N
 * 
 * Sistema de Gerenciamento de Eventos
 * 
 * @author Ivan Lemos
 * @matricula ******2541
 * @version 1.0
 */
package sistema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import sistema.controller.EventoController;
import sistema.controller.UsuarioController;
import sistema.model.Categoria;
import sistema.model.Evento;
import sistema.model.Usuario;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventoController eventoController = new EventoController();
    private static final UsuarioController usuarioController = new UsuarioController();
    private static Usuario usuarioLogado = null;

    /**
     * Limpa a tela do console
     */
    private static void limparTela() {
        try {
            String operatingSystem = System.getProperty("os.name").toLowerCase();

            if (operatingSystem.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Se não conseguir limpar a tela, apenas imprime várias linhas em branco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Exibe o cabeçalho do sistema com o logo da Universidade Anhembi Morumbi em ASCII art.
     */
    private static void mostrarCabecalho() {
        limparTela();
        System.out.println("        █████╗ ███╗   ██╗██╗  ██╗███████╗███╗   ███╗██████╗ ██╗");
        System.out.println("       ██╔══██╗████╗  ██║██║  ██║██╔════╝████╗ ████║██╔══██╗██║");
        System.out.println("       ███████║██╔██╗ ██║███████║█████╗  ██╔████╔██║██████╔╝██║");
        System.out.println("       ██╔══██║██║╚██╗██║██╔══██║██╔══╝  ██║╚██╔╝██║██╔══██╗██║");
        System.out.println("       ██║  ██║██║ ╚████║██║  ██║███████╗██║ ╚═╝ ██║██████╔╝██║");
        System.out.println("       ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝╚═════╝ ╚═╝");
        System.out.println("███╗   ███╗ ██████╗ ██████╗ ██╗   ██╗███╗   ███╗██████╗ ██╗");
        System.out.println("████╗ ████║██╔═══██╗██╔══██╗██║   ██║████╗ ████║██╔══██╗██║");
        System.out.println("██╔████╔██║██║   ██║██████╔╝██║   ██║██╔████╔██║██████╔╝██║");
        System.out.println("██║╚██╔╝██║██║   ██║██╔══██╗██║   ██║██║╚██╔╝██║██╔══██╗██║");
        System.out.println("██║ ╚═╝ ██║╚██████╔╝██║  ██║╚██████╔╝██║ ╚═╝ ██║██████╔╝██║");
        System.out.println("╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚═╝");
        System.out.println("\n                         Sistema de Gerenciamento de Eventos");
        System.out.println("                    Análise e Desenvolvimento de Sistemas - 2025-2");
        System.out.println("                           Desenvolvido por Ivan Lemos");
        System.out.println("\n═══════════════════════════════════════════════════════════════════════════════\n");
    }

    /**
     * Método principal que inicia o sistema de eventos.
     * Mantém um loop contínuo mostrando os menus apropriados baseado no estado de login do usuário.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            if (usuarioLogado == null) {
                mostrarMenuInicial();
            } else {
                mostrarMenuPrincipal();
            }
        }
    }

    /**
     * Exibe o menu inicial do sistema, permitindo que o usuário faça login,
     * cadastre-se como novo usuário ou saia do sistema.
     */
    private static void mostrarMenuInicial() {
        while (true) {
            mostrarCabecalho();
            System.out.println("1. Login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                String entrada = scanner.nextLine().trim();
                int opcao;
                
                try {
                    opcao = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida! Digite um número.");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        fazerLogin();
                        return;
                    case 2:
                        cadastrarNovoUsuario();
                        return;
                    case 3:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida! Digite um número entre 1 e 3.");
                        Thread.sleep(2000); // Pausa para ler a mensagem
                }
            } catch (Exception e) {
                System.out.println("Erro ao ler entrada. Por favor, tente novamente.");
                try {
                    Thread.sleep(2000); // Pausa para ler a mensagem
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Exibe o menu principal do sistema, disponível apenas para usuários logados.
     * Permite gerenciar eventos e participações.
     */
    private static void mostrarMenuPrincipal() {
        while (true) {
            mostrarCabecalho();
            System.out.println("1. Cadastrar novo evento");
            System.out.println("2. Listar eventos próximos");
            System.out.println("3. Listar eventos ocorrendo agora");
            System.out.println("4. Listar eventos passados");
            System.out.println("5. Inscrever-se em um evento");
            System.out.println("6. Cancelar inscrição em evento");
            System.out.println("7. Ver meus eventos");
            System.out.println("8. Logout");
            System.out.print("Escolha uma opção: ");

            try {
                String entrada = scanner.nextLine().trim();
                int opcao;
                
                try {
                    opcao = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida! Digite um número.");
                    Thread.sleep(2000);
                    continue;
                }

                switch (opcao) {
                    case 1:
                        cadastrarEvento();
                        return;
                    case 2:
                        listarEventosProximos();
                        return;
                    case 3:
                        listarEventosOcorrendo();
                        return;
                    case 4:
                        listarEventosPassados();
                        return;
                    case 5:
                        inscreverEmEvento();
                        return;
                    case 6:
                        cancelarInscricao();
                        return;
                    case 7:
                        verMeusEventos();
                        return;
                    case 8:
                        logout();
                        return;
                    default:
                        System.out.println("Opção inválida! Digite um número entre 1 e 8.");
                        Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("Erro ao ler entrada. Por favor, tente novamente.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Realiza o processo de login do usuário no sistema.
     * Solicita o email do usuário e verifica se ele está cadastrado.
     */
    private static void fazerLogin() {
        while (true) {
            System.out.print("Digite seu email (ou 'q' para voltar): ");
            String email = scanner.nextLine().trim();
            
            // Verifica se o usuário quer sair
            if (email.equalsIgnoreCase("q")) {
                return;
            }
            
            // Validar email vazio
            if (email.isEmpty()) {
                System.out.println("O email não pode estar vazio! Tente novamente.");
                continue;
            }

            // Validar formato básico do email
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Formato de email inválido! Tente novamente.");
                continue;
            }
            
            Usuario usuario = usuarioController.buscarUsuarioPorEmail(email);
            if (usuario != null) {
                usuarioLogado = usuario;
                System.out.println("Login realizado com sucesso!");
                break;
            } else {
                System.out.println("Usuário não encontrado! Tente novamente.");
            }
        }
    }

    /**
     * Realiza o cadastro de um novo usuário no sistema.
     * Solicita nome, email e telefone do usuário.
     * Verifica se o email já está cadastrado antes de prosseguir.
     */
    private static void cadastrarNovoUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        usuarioLogado = usuarioController.cadastrarUsuario(nome, email, telefone);
        if (usuarioLogado != null) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Já existe um usuário cadastrado com este email!");
        }
    }

    /**
     * Realiza o cadastro de um novo evento no sistema.
     * Solicita nome, endereço, categoria, data/hora e descrição do evento.
     * Realiza validação do formato da data/hora inserida.
     */
    private static void cadastrarEvento() {
        mostrarCabecalho();
        System.out.println("=== Cadastro de Novo Evento ===\n");
        System.out.println("Obs: Digite 'q' em qualquer campo para voltar ao menu principal\n");
        
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        if (nome.equalsIgnoreCase("q")) return;

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        if (endereco.equalsIgnoreCase("q")) return;

        Categoria[] categorias = Categoria.values();
        int categoriaIndex = -1;
        
        while (categoriaIndex < 0) {
            mostrarCabecalho();
            System.out.println("=== Cadastro de Novo Evento ===\n");
            System.out.println("Nome do evento: " + nome);
            System.out.println("Endereço: " + endereco);
            System.out.println("\nCategorias disponíveis:");
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i].getDescricao());
            }
            System.out.print("\nEscolha a categoria (número): ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("q")) return;
            
            try {
                categoriaIndex = Integer.parseInt(input) - 1;
                if (categoriaIndex < 0 || categoriaIndex >= categorias.length) {
                    System.out.println("Categoria inválida! Pressione ENTER para tentar novamente...");
                    scanner.nextLine();
                    categoriaIndex = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Pressione ENTER para tentar novamente...");
                scanner.nextLine();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime horario;
        
        while (true) {
            System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
            String dataHoraStr = scanner.nextLine();

            if (dataHoraStr.equalsIgnoreCase("q")) {
                return;
            }

            try {
                horario = LocalDateTime.parse(dataHoraStr, formatter);
                break; // Data válida, sai do loop
            } catch (Exception e) {
                System.out.println("\nFormato de data inválido!");
                System.out.println("Use o formato: dd/MM/yyyy HH:mm (exemplo: 22/03/2026 14:30)\n");
            }
        }

        mostrarCabecalho();
        System.out.println("=== Cadastro de Novo Evento ===\n");
        System.out.println("Obs: Digite 'q' em qualquer campo para voltar ao menu principal\n");
        System.out.println("Nome do evento: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Categoria: " + categorias[categoriaIndex].getDescricao());
        System.out.println("Data/Hora: " + horario.format(formatter));
        System.out.print("\nDescrição: ");
        String descricao = scanner.nextLine();
        
        if (descricao.equalsIgnoreCase("q")) return;

        eventoController.cadastrarEvento(nome, endereco, categorias[categoriaIndex], 
            horario, descricao);
        
        mostrarCabecalho();
        System.out.println("Evento cadastrado com sucesso!");
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    /**
     * Lista todos os eventos futuros cadastrados no sistema,
     * ordenados por data/hora.
     */
    /**
     * Formata e exibe uma lista de eventos em formato de tabela
     * @param eventos Lista de eventos a ser exibida
     * @param titulo Título da tabela
     */
    /**
     * Aguarda o usuário pressionar ENTER para continuar ou ESC para voltar
     */
    private static void aguardarEnter() {
        System.out.println("\nPressione ENTER para voltar ao menu ou digite 'q' para sair...");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("q")) {
            mostrarMenuPrincipal();
        }
    }

    private static void exibirTabelaEventos(List<Evento> eventos, String titulo) {
        mostrarCabecalho();
        if (eventos.isEmpty()) {
            System.out.println("Não há eventos para exibir.");
            aguardarEnter();
            return;
        }

        // Define o formato da data/hora
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Define larguras das colunas
        int largNome = 30;
        int largEndereco = 35;
        int largCategoria = 20;
        int largData = 20;
        int largParticipantes = 13;

        // Imprime o cabeçalho
        System.out.println("\n=== " + titulo + " ===");
        System.out.println("┌" + "─".repeat(largNome) + "┬" + "─".repeat(largEndereco) + "┬" + 
                          "─".repeat(largCategoria) + "┬" + "─".repeat(largData) + "┬" + 
                          "─".repeat(largParticipantes) + "┐");
        
        System.out.printf("│%-" + largNome + "s│%-" + largEndereco + "s│%-" + largCategoria + 
                         "s│%-" + largData + "s│%-" + largParticipantes + "s│\n",
                         "Nome", "Endereço", "Categoria", "Data/Hora", "Participantes");
        
        System.out.println("├" + "─".repeat(largNome) + "┼" + "─".repeat(largEndereco) + "┼" + 
                          "─".repeat(largCategoria) + "┼" + "─".repeat(largData) + "┼" + 
                          "─".repeat(largParticipantes) + "┤");

        // Imprime os dados
        for (Evento evento : eventos) {
            String nome = limitarTexto(evento.getNome(), largNome);
            String endereco = limitarTexto(evento.getEndereco(), largEndereco);
            String categoria = limitarTexto(evento.getCategoria().getDescricao(), largCategoria);
            String dataHora = evento.getHorario().format(formatoData);
            String participantes = String.valueOf(evento.getParticipantes().size());

            System.out.printf("│%-" + largNome + "s│%-" + largEndereco + "s│%-" + largCategoria + 
                            "s│%-" + largData + "s│%-" + largParticipantes + "s│\n",
                            nome, endereco, categoria, dataHora, participantes);
        }

        // Imprime a linha final
        System.out.println("└" + "─".repeat(largNome) + "┴" + "─".repeat(largEndereco) + "┴" + 
                          "─".repeat(largCategoria) + "┴" + "─".repeat(largData) + "┴" + 
                          "─".repeat(largParticipantes) + "┘");
        
        aguardarEnter();
    }

    /**
     * Limita o tamanho de um texto, adicionando "..." se necessário
     * @param texto Texto a ser limitado
     * @param tamanhoMaximo Tamanho máximo permitido
     * @return Texto limitado
     */
    private static String limitarTexto(String texto, int tamanhoMaximo) {
        if (texto == null || texto.length() <= tamanhoMaximo) {
            return texto;
        }
        return texto.substring(0, tamanhoMaximo - 3) + "...";
    }

    private static void listarEventosProximos() {
        List<Evento> eventosProximos = eventoController.listarEventosProximos();
        exibirTabelaEventos(eventosProximos, "Eventos Próximos");
    }

    /**
     * Lista todos os eventos que estão ocorrendo no momento atual.
     * Um evento é considerado "ocorrendo" se o horário atual estiver
     * dentro do período de duração do evento (2 horas após o início).
     */
    private static void listarEventosOcorrendo() {
        List<Evento> eventosOcorrendo = eventoController.listarEventosOcorrendo();
        exibirTabelaEventos(eventosOcorrendo, "Eventos Ocorrendo Agora");
    }

    /**
     * Lista todos os eventos que já ocorreram (eventos passados).
     * Um evento é considerado passado quando já se passaram mais de 2 horas
     * do seu horário de início.
     */
    private static void listarEventosPassados() {
        List<Evento> eventosPassados = eventoController.listarEventosPassados();
        exibirTabelaEventos(eventosPassados, "Eventos Passados");
    }

    /**
     * Permite que o usuário logado se inscreva em um evento.
     * Primeiro lista os eventos disponíveis e depois solicita
     * o nome do evento para realizar a inscrição.
     */
    private static void inscreverEmEvento() {
        List<Evento> eventosProximos = eventoController.listarEventosProximos();
        if (eventosProximos.isEmpty()) {
            exibirTabelaEventos(eventosProximos, "Eventos Disponíveis para Inscrição");
            return;
        }
        
        mostrarCabecalho();
        exibirTabelaEventos(eventosProximos, "Eventos Disponíveis para Inscrição");
        System.out.print("\nDigite o nome do evento para se inscrever (ou 'q' para voltar): ");
        String nomeEvento = scanner.nextLine();
        
        if (nomeEvento.equalsIgnoreCase("q")) {
            return;
        }
        
        Evento evento = eventoController.buscarEventoPorNome(nomeEvento);
        if (evento != null) {
            eventoController.inscreverUsuarioEvento(usuarioLogado, evento);
            System.out.println("Inscrição realizada com sucesso!");
        } else {
            System.out.println("Evento não encontrado!");
        }
    }

    /**
     * Permite que o usuário logado cancele sua inscrição em um evento.
     * Primeiro lista os eventos em que o usuário está inscrito e depois
     * solicita o nome do evento para cancelar a inscrição.
     */
    private static void cancelarInscricao() {
        List<Evento> eventosInscritos = usuarioLogado.getEventosInscritos();
        if (eventosInscritos.isEmpty()) {
            System.out.println("Você não está inscrito em nenhum evento.");
            return;
        }

        System.out.println("\n=== Seus Eventos ===");
        eventosInscritos.forEach(System.out::println);

        System.out.print("\nDigite o nome do evento para cancelar a inscrição: ");
        String nomeEvento = scanner.nextLine();

        Evento evento = eventoController.buscarEventoPorNome(nomeEvento);
        if (evento != null && eventosInscritos.contains(evento)) {
            eventoController.cancelarInscricaoUsuario(usuarioLogado, evento);
            System.out.println("Inscrição cancelada com sucesso!");
        } else {
            System.out.println("Evento não encontrado ou você não está inscrito nele!");
        }
    }

    /**
     * Lista todos os eventos em que o usuário logado está inscrito.
     * Se o usuário não estiver inscrito em nenhum evento, exibe uma
     * mensagem apropriada.
     */
    private static void verMeusEventos() {
        List<Evento> eventosInscritos = usuarioLogado.getEventosInscritos();
        exibirTabelaEventos(eventosInscritos, "Seus Eventos");
    }

    /**
     * Realiza o logout do usuário atual, retornando ao menu inicial.
     * Remove a referência ao usuário logado da sessão.
     */
    private static void logout() {
        usuarioLogado = null;
        System.out.println("Logout realizado com sucesso!");
    }
}
