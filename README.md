# SGHSS - Sistema de Gestão Hospitalar de Saúde Simplificada 🏥

![Java](https://img.shields.io/badge/Java-17+-red?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green?logo=springboot)
![Arquitetura](https://img.shields.io/badge/Clean%20Architecture-✔️-blue)
![Testes](https://img.shields.io/badge/Testes-JUnit5-blue)
![Docker](https://img.shields.io/badge/Container-ready-blue?logo=docker)

---

## 📖 Visão Geral

O **SGHSS** é um sistema hospitalar de gestão de pacientes construído em **Java + Spring Boot**, com foco em **qualidade de código**, **arquitetura limpa** e **boas práticas de engenharia de software**. Ele oferece recursos para cadastro, edição, consulta e gerenciamento completo de pacientes e seus dados clínicos, pessoais e de convênio.

Este projeto tem como objetivo ser **escalável**, **modular**, e **fácil de manter**, ideal para aplicações de médio e grande porte na área da saúde.

---

## 🧱 Arquitetura

A arquitetura segue os princípios de **Clean Architecture**:

├── domain
│ └── entidades de negócio (Patient, Address, Insurance, etc.)
│
├── usecases
│ └── lógica de aplicação e orquestração (CreatePatient, PatchPatient, etc.)
│
├── gateways
│ └── interfaces e implementações de acesso a dados
│
├── controllers
│ └── endpoints REST, mapeamento de entrada e saída
│
├── dtos
│ └── Request/Response (ex: PatientRequestDTO, PatientResponseDTO, PatientPatchRequestDTO)
│
├── mappers
│ └── Conversão entre DTOs e entidades de domínio
│
├── exceptions
│ └── Exceptions de domínio e handler global com mensagens claras

****


---

## ⚙️ Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.2
- Spring Data JPA
- Hibernate
- PostgreSQL (ou qualquer banco relacional)
- Docker (containerização)
- JUnit 5 + Mockito (testes unitários)
- MapStruct (mapeamento entre DTO e domínio)
- Swagger/OpenAPI (documentação dos endpoints)
- Lombok

---

## 🚀 Funcionalidades atuais completas até o momento.

- ✅ Criar paciente
- ✅ Buscar paciente por ID
- ✅ Listar pacientes com paginação
- ✅ Atualizar completamente os dados de um paciente (PUT)
- ✅ Atualizar parcialmente os dados de um paciente (PATCH)
- ✅ Validação de dados com mensagens claras
- ✅ Verificação de e-mail único
- ✅ Exceções tratadas com um `RestExceptionHandler` global
- ✅ Documentação automática com Swagger

---

## 🔐 Validações de Domínio

- `Email` deve ser único (regra validada no use case).
- Todos os campos obrigatórios são validados via anotações do Bean Validation.
- Patch permite atualização parcial com validação condicional.

---

## 🧪 Testes

- Testes de unidade para os casos de uso (usecases)
- Testes de integração
- Cobertura para exceções, regras de negócio e persistência

---

## ▶️ Como Rodar Localmente

### 1. Pré-requisitos

- Java 17
- Docker e Docker Compose (opcional, mas recomendado)
- PostgreSQL (caso não utilize Docker)

### 2. Clone o projeto

```bash
git clone https://github.com/seu-usuario/sghss.git
cd sghss
