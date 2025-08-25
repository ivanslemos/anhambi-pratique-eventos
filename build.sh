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
mkdir -p target/classes target/dist/lib

# Compila os arquivos Java
log "Compilando arquivos Java..."
javac -d target/classes -cp "lib/*" $(find src/main/java -name "*.java")

# Verifica se a compilação foi bem sucedida
if [ $? -ne 0 ]; then
    error "Erro durante a compilação"
fi

# Copia os arquivos de dependência
log "Copiando recursos..."
cp lib/* target/dist/lib/
cp -r data target/dist/

# Cria o JAR
log "Criando arquivo JAR..."
jar cfm target/dist/AnhembiEventos.jar manifest.txt -C target/classes .

# Verifica se a criação do JAR foi bem sucedida
if [ $? -ne 0 ]; then
    error "Erro ao criar o arquivo JAR"
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
