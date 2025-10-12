# Conecta Social API

API REST desenvolvida em Java com Spring Boot para gestão de funcionários e eventos da organização Conecta Social.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados
- **Flyway** - Migração de banco de dados
- **JWT** - Autenticação stateless
- **Swagger/OpenAPI 3** - Documentação da API
- **Maven** - Gerenciamento de dependências

## 📋 Funcionalidades

### Autenticação
- Login com JWT
- Controle de acesso baseado em roles (ADMIN, MANAGER, VOLUNTEER)
- Middleware de autenticação automático

### Gestão de Funcionários
- ✅ Criar funcionário
- ✅ Listar funcionários ativos
- ✅ Buscar funcionário por ID
- ✅ Atualizar dados do funcionário
- ✅ Desativar funcionário (soft delete)
- ✅ Buscar funcionários por nome/sobrenome

### Gestão de Eventos
- ✅ Criar evento
- ✅ Listar eventos ativos
- ✅ Buscar evento por ID
- ✅ Atualizar dados do evento
- ✅ Desativar evento (soft delete)
- ✅ Buscar eventos por nome
- ✅ Listar eventos futuros
- ✅ Listar eventos passados
- ✅ Filtrar eventos por status

## 🗄️ Estrutura do Banco de Dados

### Tabelas
- **employees** - Dados dos funcionários
- **events** - Dados dos eventos
- **log_employee_event** - Logs de interação entre funcionários e eventos

### Roles de Funcionários
- **ADMIN** - Acesso total ao sistema
- **MANAGER** - Pode gerenciar funcionários e eventos
- **VOLUNTEER** - Acesso somente leitura

## 🛠️ Configuração e Execução

### Pré-requisitos
- Java 17+
- Maven 3.6+
- PostgreSQL 12+

### Configuração do Banco de Dados
1. Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE conecta_social;
```

2. Configure as variáveis de ambiente ou edite o `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/conecta_social
    username: seu_usuario
    password: sua_senha
```

### Executando a Aplicação

#### Opção 1: Script Automatizado (Recomendado)
```bash
# Tornar o script executável (apenas na primeira vez)
chmod +x run.sh

# Executar a aplicação
./run.sh
```

#### Opção 2: Manual
1. Clone o repositório:
```bash
git clone <url-do-repositorio>
cd conecta-social-java-api
```

2. Configure o PostgreSQL e crie o banco:
```sql
CREATE DATABASE conecta_social;
```

3. Execute as migrações do Flyway:
```bash
mvn flyway:migrate
```

4. Execute a aplicação:
```bash
mvn spring-boot:run
```

#### Opção 3: Docker
```bash
# Subir apenas o banco de dados
docker-compose up postgres -d

# Ou subir tudo (banco + aplicação)
docker-compose up
```

A API estará disponível em: `http://localhost:3001`

## 🔑 Credenciais de Teste

Para testar a API, use as seguintes credenciais:
- **Email**: admin@empresa.com
- **Senha**: password

## 📚 Documentação da API

A documentação interativa da API está disponível através do Swagger UI:
- **URL**: `http://localhost:3001/swagger-ui/index.html`
- **API Docs**: `http://localhost:3001/v3/api-docs`

## 🔐 Autenticação

### Login
```bash
POST /auth/login
Content-Type: application/json

{
  "email": "admin@empresa.com",
  "password": "password"
}
```

### Resposta
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer"
}
```

### Uso do Token
Inclua o token no header Authorization:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## 📝 Exemplos de Uso

### Criar Funcionário
```bash
POST /employees
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "João",
  "surname": "Silva",
  "birthDate": "1990-01-15",
  "cpf": "12345678901",
  "email": "joao.silva@exemplo.com",
  "phone": "11999999999",
  "password": "senha123",
  "role": "VOLUNTEER",
  "cep": "01234-567",
  "street": "Rua das Flores",
  "neighborhood": "Centro",
  "number": "123",
  "city": "São Paulo",
  "uf": "SP",
  "state": "São Paulo"
}
```

### Criar Evento
```bash
POST /events
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Evento Beneficente",
  "description": "Evento para arrecadação de fundos",
  "date": "2024-12-25T18:00:00",
  "status": "PLANNED",
  "street": "Rua da Caridade",
  "neighborhood": "Centro",
  "number": "456",
  "city": "São Paulo",
  "uf": "SP",
  "state": "São Paulo",
  "cep": "01234-567"
}
```

## 🧪 Testes

Execute os testes unitários:
```bash
mvn test
```

## 📊 Monitoramento

A aplicação inclui endpoints de monitoramento do Spring Actuator:
- **Health Check**: `http://localhost:3001/actuator/health`
- **Info**: `http://localhost:3001/actuator/info`
- **Metrics**: `http://localhost:3001/actuator/metrics`

## 🔧 Configurações Avançadas

### Variáveis de Ambiente
- `DB_USERNAME` - Usuário do banco de dados
- `DB_PASSWORD` - Senha do banco de dados
- `JWT_SECRET` - Chave secreta para JWT

### Logs
Os logs são configurados para mostrar informações detalhadas sobre:
- Requisições HTTP
- Tempo de resposta
- Erros e exceções
- Autenticação

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📞 Suporte

Para suporte, entre em contato:
- Email: contato@conectasocial.org
- Website: https://conectasocial.org
# conecta-social-java-api
