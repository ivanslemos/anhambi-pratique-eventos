# Sistema de Gerenciamento de Eventos - Anhembi Morumbi

Sistema desenvolvido como projeto para a disciplina de Programação de Soluções Computacionais da Faculdade Anhembi Morumbi.

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
- **Bibliotecas**:
  - JLine 3.21.0 (interface de terminal aprimorada)
- **Persistência**: Arquivos (.data)
- **Build**: Scripts shell personalizados

## Estrutura do Projeto

```
.
├── src/                    # Código fonte
│   └── main/
│       └── java/
│           └── sistema/
│               ├── controller/  # Controladores
│               │   ├── EventoController.java
│               │   └── UsuarioController.java
│               ├── model/      # Modelos
│               │   ├── Categoria.java
│               │   ├── Evento.java
│               │   └── Usuario.java
│               ├── util/       # Utilitários
│               │   └── FileManager.java
│               └── view/       # Interface
├── data/                  # Dados persistentes
│   ├── events.data
│   └── users.data
├── lib/                   # Dependências
│   └── jline-3.21.0.jar
├── target/               # Arquivos de build
│   └── dist/            # Distribuição
└── scripts/             # Scripts de build
    ├── build.sh
    └── preparar_dist.sh
```

## Compilação e Execução

### Compilando o Projeto

O projeto inclui scripts de build automatizados. Para compilar:

1. Dê permissão de execução ao script:
   ```bash
   chmod +x build.sh
   ```

2. Execute o script de build:
   ```bash
   ./build.sh
   ```

O script irá:
- Criar os diretórios necessários
- Compilar os arquivos Java
- Copiar dependências e recursos
- Gerar o arquivo JAR executável

### Executando o Sistema

Há duas formas de executar o sistema:

1. Usando o JAR diretamente:
   ```bash
   java -jar AnhembiEventos.jar
   ```

2. A partir da pasta de distribuição:
   ```bash
   cd target/dist
   java -jar AnhembiEventos.jar
   ```

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
