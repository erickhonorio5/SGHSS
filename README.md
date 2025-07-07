# 🏥 Sistema de Gestão Hospitalar e de Serviços de Saúde (SGHSS)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

> Sistema integrado para gestão completa de hospitais, clínicas, laboratórios e serviços de home care, desenvolvido como projeto final do curso de Análise e Desenvolvimento de Sistemas da UNINTER.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [API Documentation](#api-documentation)
- [Arquitetura](#arquitetura)
- [Testes](#testes)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

## 🎯 Sobre o Projeto

O SGHSS foi desenvolvido para atender às necessidades da rede VidaPlus, que administra hospitais, clínicas de bairro, laboratórios e equipes de home care. O sistema centraliza e integra todas as operações de saúde, eliminando a fragmentação de dados e melhorando a eficiência operacional.

### Problemas Resolvidos
- ✅ Descentralização de dados entre diferentes sistemas
- ✅ Falta de integração entre unidades da rede
- ✅ Dificuldades de compliance com LGPD
- ✅ Necessidade de escalabilidade para crescimento da rede

## 🚀 Funcionalidades

### 👥 Gestão de Pacientes
- Cadastro completo com validação de CPF
- Histórico clínico integrado
- Controle de responsáveis para menores
- Gestão de convênios e seguros

### 🗓️ Sistema de Agendamentos
- Agendamento presencial e telemedicina
- Validação de disponibilidade em tempo real
- Reagendamento e cancelamento
- Notificações automáticas

### 👨‍⚕️ Gestão de Profissionais
- Cadastro com validação de CRM/COREN
- Múltiplas especialidades
- Agenda personalizada por dia da semana
- Controle de disponibilidade

### 📋 Prontuários Eletrônicos
- Criação e edição por profissionais autorizados
- Histórico completo de consultas
- Assinatura digital
- Conformidade com normas médicas

### 🔐 Segurança e Compliance
- Autenticação JWT
- Controle de acesso baseado em perfis
- Logs de auditoria
- Conformidade com LGPD

## 🛠️ Tecnologias

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.2** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados
- **Gradle** - Gerenciamento de dependências

### Documentação
- **Swagger/OpenAPI 3.0** - Documentação da API
- **Spring Doc** - Geração automática da documentação

### Testes
- **JUnit 5** - Testes unitários
- **Mockito** - Mocking para testes
- **TestContainers** - Testes de integração

### DevOps
- **Docker** - Containerização
- **Docker Compose** - Orquestração local

## 📋 Pré-requisitos

- Java 21 ou superior
- Docker e Docker Compose
- Gradle 8.0+
- PostgreSQL 15+ (Docker compose)

## 🔧 Instalação

### 1. Clone o repositório
```bash
git clone https://github.com/erickhonorio5/SGHSS.git
cd SGHSS
```
### 2. Execute com Docker Compose (Necessãrio ter o Docker Desktop Instalado)
```bash
docker-compose up -d
```
A aplicação estará disponível em: `http://localhost:8080`

## 🎮 Uso

### 1. Primeiro Acesso
1. Acesse a documentação da API: `http://localhost:8080/swagger-ui.html`
2. Crie uma conta usando o endpoint de Sign Up
3. Faça login para obter o token JWT

### 2. Fluxo Básico de Uso Depois de Logado
```bash
# 1. Criar um paciente
POST /api/v1/patients

# 2. Criar um profissional
POST /api/v1/professional

# 3. Definir agenda do profissional
POST /api/v1/professional/{id}/work-schedules/week

# 4. Verificar horários disponíveis
GET /api/v1/appointments/professional/{id}/available-slots?date=2025-07-01

# 5. Agendar consulta
POST /api/v1/appointments
```

### 3. Exemplos de Requests

<details>
<summary>Criar Paciente</summary>

```json
POST /api/v1/patients
{
  "name": "Maria Oliveira",
  "cpf": "39053344705",
  "email": "maria.oliveira@example.com",
  "phone": "71985244402",
  "birthDate": "1990-07-01",
  "address": {
    "street": "Rua das Palmeiras",
    "number": "123",
    "neighborhood": "Centro",
    "city": "Salvador",
    "state": "BA",
    "zipCode": "40000-000"
  },
  "insurance": {
    "name": "Saúde Total",
    "number": "ST123456789",
    "expiryDate": "2026-07-01"
  }
}
```
</details>

<details>
<summary>Agendar Consulta</summary>

```json
POST /api/v1/appointments
{
  "patientId": 1,
  "professionalId": 1,
  "appointmentDate": "2025-07-01",
  "appointmentTime": "14:30",
  "appointmentType": "IN_PERSON",
  "observations": "Paciente relatou dores no ombro direito há 3 dias."
}
```
</details>

## 📚 API Documentation

A documentação completa da API está disponível via Swagger UI:

- **URL Local**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### Principais Endpoints

| Endpoint | Método | Descrição |
|----------|--------|-----------|
| `/api/v1/auth/signup` | POST | Criar conta de usuário |
| `/api/v1/auth/signin` | POST | Fazer login |
| `/api/v1/patients` | GET, POST | Gestão de pacientes |
| `/api/v1/professional` | GET, POST | Gestão de profissionais |
| `/api/v1/appointments` | GET, POST | Gestão de consultas |
| `/api/v1/speciality` | GET | Listar especialidades |

## 🏗️ Arquitetura

O projeto utiliza **Clean Architecture** adaptada em monolito:

```
src/
├── main/java/gestao/sghss/
│   ├── configs/          # Configurações gerais
│   ├── controllers/      # Entrypoints da aplicação
│   ├── domain/          # Entidades, enums e Value Objects
│   ├── exceptions/      # Tratamento de exceções
│   ├── gateways/        # Implementação de contratos
│   ├── repositories/    # Interfaces para dados
│   ├── security/        # Configurações de segurança
│   ├── services/        # Lógica de domínio compartilhada
│   └── usecases/        # Casos de uso (regras de negócio)
│       ├── paciente/
│       ├── consulta/
│       └── prontuario/
```

### Camadas da Arquitetura

- **Controllers**: Pontos de entrada da aplicação (API REST)
- **Use Cases**: Regras de negócio e orquestração
- **Domain**: Entidades e regras fundamentais do domínio
- **Gateways**: Implementação de contratos e acesso a dados
- **Security**: Autenticação, autorização e segurança

## 🔒 Segurança

### Medidas Implementadas
- **JWT Authentication**: Tokens seguros com expiração
- **Controle de Acesso**: Baseado em perfis (ADMIN, PROFESSIONAL, PATIENT)
- **Criptografia**: Senhas com BCrypt
- **Rate Limiting**: Controle de tentativas de login
- **Logs de Auditoria**: Rastreamento de operações críticas
- **LGPD Compliance**: Proteção de dados pessoais

### Perfis de Usuário
- **ADMIN**: Acesso completo ao sistema
- **PROFESSIONAL**: Gestão de consultas e prontuários

## 🤝 Contribuição

Contribuições são bem-vindas! Por favor, siga estas diretrizes:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Padrões de Código
- Siga as convenções do Java/Spring Boot
- Escreva testes para novas funcionalidades
- Mantenha a cobertura de testes acima de 80%
- Use commits semânticos

## 📈 Roadmap

### Versão 2.0
- [ ] Telemedicina completa com videoconferência
- [ ] Dashboard analítico avançado
- [ ] Aplicativo mobile
- [ ] Integração com laboratórios externos
- [ ] Sistema de notificações em tempo real

### Melhorias Técnicas
- [ ] Migração para microserviços
- [ ] Implementação de cache distribuído (Redis)
- [ ] Event Sourcing para auditoria
- [ ] API Gateway

## 📞 Contato

**Erick Lucas Honorio da Silva**
- Email: ericklucashonorio@gmail.com
- LinkedIn: [erick-honorio](https://linkedin.com/in/erick-honorio)
- GitHub: [@erickhonorio5](https://github.com/erickhonorio5)

**Projeto**: [SGHSS](https://github.com/erickhonorio5/SGHSS)

---

<div align="center">
  <p>Desenvolvido com ❤️ por <a href="https://github.com/erickhonorio5">Erick Honorio</a></p>
  <p>⭐ Não se esqueça de dar uma estrela se este projeto te ajudou!</p>
</div>
