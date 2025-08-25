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

# Cria diretórios necessários
log "Criando diretórios..."
mkdir -p target/classes
mkdir -p target/dist/lib

# Limpa os diretórios de build
log "Limpando diretórios de build..."
rm -rf target/classes/*
rm -rf target/dist/*

# Compila os arquivos Java
log "Compilando arquivos Java..."
find src/main/java -name "*.java" -type f > sources.txt
javac -d target/classes -cp "lib/*" @sources.txt
rm sources.txt

# Verifica se a compilação foi bem sucedida
if [ $? -ne 0 ]; then
    error "Erro durante a compilação"
fi

# Copia os arquivos de dependência
log "Copiando dependências..."
cp lib/* target/dist/lib/
cp -r data target/dist/

# Cria o arquivo de manifesto temporário
log "Configurando manifesto..."
echo "Manifest-Version: 1.0" > target/MANIFEST.MF
echo "Main-Class: sistema.Main" >> target/MANIFEST.MF
echo "Class-Path: lib/jline-3.21.0.jar" >> target/MANIFEST.MF
echo "" >> target/MANIFEST.MF

# Cria o JAR
log "Criando arquivo JAR..."
cd target/classes
jar cfm ../dist/AnhembiEventos.jar ../MANIFEST.MF .
cd ../..

# Verifica se a criação do JAR foi bem sucedida
if [ $? -ne 0 ]; then
    error "Erro ao criar o arquivo JAR"
fi

# Limpa arquivos temporários
rm target/MANIFEST.MF

log "Build concluído com sucesso!"
log "O arquivo JAR está disponível em: target/dist/AnhembiEventos.jar"
