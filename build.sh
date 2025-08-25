#!/bin/bash

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

# Função para exibir mensagens
log() {
    echo -e "${GREEN}$1${NC}"
}

error() {
    echo -e "${RED}$1${NC}"
    exit 1
}

# Verifica se está no diretório correto
if [ ! -f "manifest.txt" ]; then
    error "Erro: Execute este script no diretório raiz do projeto"
fi

# Limpa e cria diretórios
log "Preparando diretórios..."
rm -rf target
mkdir -p target/classes target/dist/lib target/dist/data

# Compila os arquivos Java
log "Compilando arquivos Java..."
find src/main/java -name "*.java" > sources.txt
javac -d target/classes -cp "lib/*" @sources.txt
rm sources.txt

# Verifica se a compilação foi bem sucedida
if [ $? -ne 0 ]; then
    error "Erro durante a compilação"
fi

# Copia as dependências e recursos
log "Copiando recursos..."
cp lib/* target/dist/lib/
cp -r data/* target/dist/data/

# Move os arquivos compilados para o diretório correto
cd target/classes
jar cfm ../dist/AnhembiEventos.jar ../../manifest.txt ./*
cd ../..

# Verifica se a criação do JAR foi bem sucedida
if [ $? -ne 0 ]; then
    error "Erro ao criar o arquivo JAR"
fi

# Testa se o JAR foi criado corretamente
log "Testando o JAR..."
if ! java -jar target/dist/AnhembiEventos.jar --version 2>/dev/null; then
    error "Erro: O JAR não foi criado corretamente"
fi

log "Build concluído com sucesso!"
log "O arquivo JAR está disponível em: target/dist/AnhembiEventos.jar"

# Instruções para execução
echo ""
log "Para executar o programa, use:"
echo "cd target/dist && java -jar AnhembiEventos.jar"

echo -e "${GREEN}Iniciando build do Sistema de Gerenciamento de Eventos${NC}"

# Criar estrutura de diretórios
echo "Criando estrutura de diretórios..."
mkdir -p target/dist/lib
mkdir -p target/dist/data

# Limpar builds anteriores
rm -rf target
mkdir -p target/classes

# Compilar
echo "Compilando código fonte..."
javac -d target/classes src/main/java/sistema/**/*.java

if [ $? -eq 0 ]; then
    echo -e "${GREEN}Compilação concluída com sucesso!${NC}"
else
    echo -e "${RED}Erro na compilação!${NC}"
    exit 1
fi

# Copiar recursos
echo "Copiando recursos..."
cp -r data/* target/dist/data/
cp -r lib/* target/dist/lib/

# Criar JAR
echo "Criando arquivo JAR..."
cd target/classes
jar cfm ../dist/AnhembiEventos.jar ../../manifest.txt .
cd ..

if [ $? -eq 0 ]; then
    echo -e "${GREEN}JAR criado com sucesso!${NC}"
else
    echo -e "${RED}Erro ao criar JAR!${NC}"
    exit 1
fi

cd ../..

# Criar arquivo README na distribuição
echo "Sistema de Gerenciamento de Eventos - Anhembi Morumbi" > target/dist/README.txt
echo "Desenvolvido por Ivan Lemos" >> target/dist/README.txt
echo "" >> target/dist/README.txt
echo "Para executar:" >> target/dist/README.txt
echo "java -jar AnhembiEventos.jar" >> target/dist/README.txt

echo -e "${GREEN}Build concluído com sucesso!${NC}"
echo "Os arquivos de distribuição estão em: target/dist/"
