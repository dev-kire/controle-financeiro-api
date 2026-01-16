# Controle Financeiro API – v1

API REST para controle financeiro pessoal, permitindo o gerenciamento de categorias e transações financeiras, com autenticação segura via JWT e isolamento de dados por usuário.

Projeto desenvolvido com foco em boas práticas de backend, segurança, organização em camadas e regras de negócio bem definidas.

# 🎯 Objetivo do Projeto

### Consolidar conhecimentos em:
- Spring Boot

- Spring Security

- Autenticação e autorização com JWT

- APIs RESTful

- Modelagem de domínio

- Separação de responsabilidades (Controller, Service, Repository)

- Controle de acesso baseado no usuário autenticado

Este projeto foi pensado como item de portifólio, simulando um backend real utilizado por múltiplos usuários.


# 🔐 Autenticação e Segurança

A API utiliza JWT (JSON Web Token) para autenticação stateless.

### Fluxo de autenticação
1. Usuário realiza login `(POST /users/login)`

2. A API retorna um token JWT

3. O token deve ser enviado no header `Authorization`

4. Um filtro de segurança valida o token a cada requisição protegida

5. O usuário autenticado é recuperado via `SecurityContext`
### Header esperado
`Authorization: Bearer <token>`

Tratamento de acesso

- `401 Unauthorized`
Token ausente ou inválido

- `403 Forbidden`
Usuário autenticado tentando acessar recurso que não pertence a ele

# 📌 Endpoints Disponíveis

### Públicos

- `POST /users/register` – Cadastro de usuário

- `POST /users/login` – Autenticação e geração de token JWT

Protegidos (JWT)

- `GET /users/me` – Retorna os dados do usuário autenticado

# 📂 Módulos da API

### Categorias

Gerenciamento de categorias financeiras, sempre vinculadas ao usuário autenticado.

### Endpoints:

- `POST /categories`

- `GET /categories`

- `GET /categories/{id}`

- `PUT /categories/{id}`

- `DELETE /categories/{id}`

Regras:

- Um usuário só pode acessar suas próprias categorias

- Categorias são utilizadas como base para as transações

### Transações

Registro de entradas (INCOME) e saídas (EXPENSE) financeiras.

### Endpoints:

- `POST /transactions`

- `GET /transactions`

- `GET /transactions/{id}`

- `PUT /transactions/{id}`

- `DELETE /transactions/{id}`

Regras:

- Cada transação pertence a uma categoria

- O usuário só pode acessar transações associadas às suas categorias

- Validação completa baseada no usuário autenticado

# 🧱 Estrutura do Projeto
```
controllers        → Camada de entrada (REST Controllers)
domain             → Entidades e regras de negócio
dto                → Data Transfer Objects
infra/security     → JWT, filtros e configuração de segurança
config             → Tratamento global de exceções  
```
##### A arquitetura segue um padrão claro de separação de responsabilidades, facilitando manutenção e evolução do projeto.

# ▶️ Como executar o projeto

### Pré-requisitos

- Java 21+

- PostgreSQL

- Maven

### Passos
```
git clone https://github.com/dev-kire/controle-financeiro-api.git
cd controle-financeiro-api
mvn spring-boot:run
```
