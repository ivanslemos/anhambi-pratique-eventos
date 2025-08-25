# Sistema de Gerenciamento de Eventos - Anhembi Morumbi

Sistema desenvolvido como projeto para a disciplina de Programação de Soluções Computacionais da Faculdade Anhembi Morumbi.

## Descrição

O sistema permite o gerenciamento completo de eventos, incluindo:
- Cadastro e autenticação de usuários
- Criação e gerenciamento de eventos
- Inscrição em eventos
- Visualização de eventos (próximos, em andamento e passados)
- Interface em modo texto com ASCII art

## Tecnologias Utilizadas

- Java 11+
- Persistência em arquivos
- Padrão MVC
- ASCII art para interface

## Estrutura do Projeto

```
projeto/
├── src/
│   └── main/
│       └── java/
│           └── sistema/
│               ├── controller/
│               ├── model/
│               ├── util/
│               └── view/
├── data/       # Arquivos de dados
├── lib/        # Bibliotecas
└── docs/       # Documentação
```

## Como Executar

1. Compilar o projeto:
```bash
javac src/main/java/sistema/**/*.java
```

2. Executar:
```bash
java -cp src/main/java sistema.Main
```

Ou usar o arquivo JAR:
```bash
java -jar AnhembiEventos.jar
```

## Autor

- Ivan Lemos
- Curso: Análise e Desenvolvimento de Sistemas
- Período: 2025-2 N

## Licença

Este projeto é para fins educacionais.
