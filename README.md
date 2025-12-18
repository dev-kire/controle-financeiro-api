# Controle Financeiro API

API REST desenvolvida em Java com Spring Boot para controle financeiro pessoal, com autenticação via JWT e endpoints protegidos.

Este projeto faz parte do meu portfólio e tem como foco demonstrar boas práticas de backend, segurança e organização de código.

---

## 🚀 Tecnologias Utilizadas

- Java 23
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- PostgreSQL
- Maven

---

## 🔐 Autenticação e Segurança

A API utiliza autenticação baseada em **JWT**.

### Fluxo de autenticação:
1. O usuário realiza login (`/users/login`)
2. A API retorna um **token JWT**
3. O token deve ser enviado no header `Authorization`
4. Endpoints protegidos validam o token via filtro de segurança

### Header esperado:
```http
Authorization: Bearer <token>

## 📌 Endpoints Disponíveis

###  Públicos
- `POST /users/register` – Cadastro de usuário
- `POST /users/login` – Autenticação e geração de token JWT

###  Protegidos (JWT)
- `GET /users/me` – Retorna os dados do usuário autenticado

##  Como executar o projeto

### Pré-requisitos
- Java 23
- PostgreSQL
- Maven

### Passos
```bash
git clone https://github.com/dev-kire/controle-financeiro-api.git
cd controle-financeiro-api
mvn spring-boot:run

##  Tratamento de Erros

A API diferencia corretamente erros de autenticação e autorização:

- **401 Unauthorized**  
  Retornado quando o token JWT está ausente ou inválido.

- **403 Forbidden**  
  Retornado quando o usuário está autenticado, mas não possui permissão
  para acessar o recurso.

##  Estrutura do Projeto

- controllers → Camada de entrada (REST Controllers)
- domain → Regras de negócio e entidades
- dto → Objetos de transferência de dados
- infra/security → Configurações de segurança e JWT
- config → Tratamento global de exceções

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de consolidar conhecimentos em
Spring Boot, Spring Security e autenticação JWT, seguindo boas práticas de
arquitetura backend e organização de código.
