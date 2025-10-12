#!/bin/bash

echo "🚀 Iniciando Conecta Social API..."

# Verificar se o Java está instalado
if ! command -v java &> /dev/null; then
    echo "❌ Java não encontrado. Por favor, instale o Java 17+"
    exit 1
fi

# Verificar se o Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven não encontrado. Por favor, instale o Maven"
    exit 1
fi

echo "✅ Java e Maven encontrados"

# Compilar o projeto
echo "🔨 Compilando o projeto..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "❌ Erro na compilação"
    exit 1
fi

echo "✅ Compilação concluída"

# Executar a aplicação
echo "🎯 Iniciando a aplicação..."
echo "📖 Documentação disponível em: http://localhost:3001/api/swagger-ui.html"
echo "🔍 Health check em: http://localhost:3001/api/"
echo ""
echo "Pressione Ctrl+C para parar a aplicação"
echo ""

mvn spring-boot:run
