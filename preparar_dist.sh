#!/bin/bash

# Criar pasta de distribuição
mkdir -p dist
mkdir -p dist/data
mkdir -p dist/lib

# Copiar arquivos
cp AnhembiEventos.jar dist/
cp data/* dist/data/
cp lib/* dist/lib/

# Criar arquivo README
echo "Sistema de Gerenciamento de Eventos - Anhembi Morumbi" > dist/README.txt
echo "Desenvolvido por Ivan Lemos" >> dist/README.txt
echo "" >> dist/README.txt
echo "Para executar:" >> dist/README.txt
echo "java -jar AnhembiEventos.jar" >> dist/README.txt
