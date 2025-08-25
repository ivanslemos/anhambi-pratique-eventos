# Sistema de Gerenciamento de Eventos - Anhembi Morumbi

```
        █████╗ ███╗   ██╗██╗  ██╗███████╗███╗   ███╗██████╗ ██╗
       ██╔══██╗████╗  ██║██║  ██║██╔════╝████╗ ████║██╔══██╗██║
       ███████║██╔██╗ ██║███████║█████╗  ██╔████╔██║██████╔╝██║
       ██╔══██║██║╚██╗██║██╔══██║██╔══╝  ██║╚██╔╝██║██╔══██╗██║
       ██║  ██║██║ ╚████║██║  ██║███████╗██║ ╚═╝ ██║██████╔╝██║
       ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝╚═════╝ ╚═╝
███╗   ███╗ ██████╗ ██████╗ ██╗   ██╗███╗   ███╗██████╗ ██╗
████╗ ████║██╔═══██╗██╔══██╗██║   ██║████╗ ████║██╔══██╗██║
██╔████╔██║██║   ██║██████╔╝██║   ██║██╔████╔██║██████╔╝██║
██║╚██╔╝██║██║   ██║██╔══██╗██║   ██║██║╚██╔╝██║██╔══██╗██║
██║ ╚═╝ ██║╚██████╔╝██║  ██║╚██████╔╝██║ ╚═╝ ██║██████╔╝██║
╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚═╝
```

# Sistema de Gerenciamento de Eventos - Universidade Anhembi Morumbi

Sistema desenvolvido como projeto para a disciplina de Programação de Soluções Computacionais da Universidade Anhembi Morumbi.

> **Observação**: Para fim de estudo, este projeto foi desenvolvido utilizando a arquitetura MVC (Model-View-Controller) de forma didática, focando nos conceitos fundamentais de programação orientada a objetos e boas práticas de desenvolvimento.

## Funcionalidades Principais

- **Gerenciamento de Usuários**
  - Cadastro de novos usuários
  - Autenticação (login)
  - Perfil de administrador e usuário comum

- **Gerenciamento de Eventos**
  - Criação de eventos
  - Listagem de eventos
  - Inscrição em eventos
  - Categorização de eventos

- **Interface**
  - Interface em linha de comando (CLI) com ASCII art
  - Navegação intuitiva por menus
  - Feedback visual com cores

## Descrição

O sistema permite o gerenciamento completo de eventos, incluindo:
- Cadastro e autenticação de usuários
- Criação e gerenciamento de eventos
- Inscrição em eventos
- Visualização de eventos (próximos, em andamento e passados)
- Interface em modo texto com ASCII art

## Tecnologias e Ferramentas

- **Linguagem**: Java 11+
- **IDE**: Visual Studio Code
- **Bibliotecas**:
  - JLine 3.21.0 (para melhor experiência no terminal)
- **Persistência**: 
  - Arquivos `.data` para armazenamento
  - Formato próprio de serialização
- **Build e Execução**: 
  - Scripts shell para automatização
  - Sistema de build personalizado
  - Geração de JAR executável em target/dist/

## Estrutura do Projeto

```
.
├── src/                    # Código fonte
│   └── main/
│       └── java/
│           └── sistema/
│               ├── Main.java     # Classe principal
│               ├── controller/    # Controladores
│               │   ├── EventoController.java
│               │   └── UsuarioController.java
│               ├── model/        # Modelos
│               │   ├── Categoria.java
│               │   ├── Evento.java
│               │   └── Usuario.java
│               └── util/         # Utilitários
│                   └── FileManager.java
├── data/                  # Dados persistentes
│   ├── events.data       # Armazena eventos
│   └── users.data        # Armazena usuários
├── lib/                   # Dependências
│   └── jline-3.21.0.jar  # Biblioteca para interface
├── target/               # Arquivos compilados
│   └── dist/            # Distribuição final
├── build.sh             # Script principal de build
├── manifest.txt         # Manifesto do JAR
└── README.md            # Documentação
```

## Compilação e Execução

### Compilando o Projeto

O projeto pode ser compilado e executado de duas formas:

1. **Usando o script de build (recomendado):**
   ```bash
   ./build.sh
   ```
   Este script irá:
   - Limpar os diretórios target/
   - Compilar todos os arquivos .java
   - Copiar as dependências necessárias
   - Gerar o arquivo JAR em target/dist/AnhembiEventos.jar
   - Copiar os arquivos de dados e bibliotecas
   - Testar a execução do JAR gerado

2. **Compilação e execução manual:**
   ```bash
   # Compilar
   cd src/main/java
   javac sistema/Main.java

   # Executar
   java -cp .:../../../lib/jline-3.21.0.jar sistema.Main
   ```

### Executando o Sistema

Após a compilação, você pode executar o sistema de três formas:

1. **A partir do JAR gerado (recomendado):**
   ```bash
   cd target/dist
   java -jar AnhembiEventos.jar
   ```

2. **Diretamente dos arquivos compilados:**
   ```bash
   java -cp src/main/java:lib/jline-3.21.0.jar sistema.Main
   ```

3. **Usando o script de build:**
   ```bash
   ./build.sh
   ```
   O script já inclui um teste de execução ao final.

### Estrutura do Build

O processo de build organiza os arquivos da seguinte forma:

```
target/
└── dist/
    ├── AnhembiEventos.jar  # Executável principal
    ├── lib/                # Bibliotecas necessárias
    │   └── jline-3.21.0.jar
    └── data/              # Arquivos de dados
        ├── events.data
        └── users.data
```

O arquivo JAR gerado inclui:
- Todas as classes compiladas do sistema
- Manifesto com a configuração do classpath
- Configuração da classe principal (Main)

## Distribuição

O sistema é distribuído em um pacote completo na pasta `target/dist/`, contendo:
- `AnhembiEventos.jar`: Executável principal
- `lib/`: Bibliotecas necessárias
- `data/`: Arquivos de dados do sistema

## Arquitetura e Implementação

### Camada Model
- `Usuario.java`: Entidade e regras de negócio para usuários
- `Evento.java`: Entidade e regras de negócio para eventos
- `Categoria.java`: Enumeração de categorias de eventos

### Camada Controller
- `UsuarioController.java`: Gerencia operações de usuários
- `EventoController.java`: Gerencia operações de eventos

### Camada Util
- `FileManager.java`: Gerencia persistência de dados em arquivos

### Persistência
- Dados são armazenados em arquivos `.data`
- `users.data`: Armazena informações dos usuários
- `events.data`: Armazena informações dos eventos

## Autor

- **Nome**: Ivan Lemos
- **Curso**: Análise e Desenvolvimento de Sistemas
- **Disciplina**: Programação de Soluções Computacionais
- **Instituição**: Universidade Anhembi Morumbi
- **Período**: 2025-2

## Licença

Este projeto é disponibilizado para fins educacionais, como parte do programa da disciplina de Programação de Soluções Computacionais da Universidade Anhembi Morumbi.
