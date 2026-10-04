# 🍕 Pizzaria API

Uma API RESTful moderna para gerenciamento completo de uma pizzaria, abrangendo desde o catálogo de produtos e categorias até a gestão de clientes, pedidos, itens e pagamentos.

> 🚧 **Status do Projeto:** Em desenvolvimento (Fase de Camada de Persistência / Repositories)

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 25
- **Framework:** Spring Boot 4.x
- **Persistência & ORM:** Spring Data JPA / Hibernate 7
- **Banco de Dados:** PostgreSQL 17
- **Ambiente Contêinerizado:** Docker & Docker Compose
- **Gerenciador de Dependências:** Apache Maven
- **Produtividade:** Project Lombok & Jakarta Bean Validation

---

## 🗄️ Modelo de Domínio (Entidades)

O modelo de dados foi estruturado com foco em integridade relacional e boas práticas de mapeamento JPA:

```mermaid
erDiagram
    CLIENTE ||--o{ ENDERECO : possui
    CLIENTE ||--o{ PEDIDO : realiza
    CATEGORIA ||--o{ PRODUTO : contem
    PEDIDO ||--|{ ITEM_PEDIDO : contem
    PRODUTO ||--|{ ITEM_PEDIDO : compoe
    PEDIDO ||--|| PAGAMENTO : gera

    CLIENTE {
        bigint id PK
        varchar nome
        varchar telefone
    }

    ENDERECO {
        bigint id PK
        varchar rua
        varchar numero
        varchar bairro
        varchar cidade
        varchar estado
        varchar cep
        varchar complemento
        bigint cliente_id FK
    }

    CATEGORIA {
        bigint id PK
        varchar nome
        varchar descricao
    }

    PRODUTO {
        bigint id PK
        varchar nome
        numeric preco
        bigint categoria_id FK
    }

    PEDIDO {
        bigint id PK
        timestamp data_hora
        numeric total
        integer status
        bigint cliente_id FK
    }

    ITEM_PEDIDO {
        bigint pedido_id PK, FK
        bigint produto_id PK, FK
        integer quantidade
        numeric preco
        varchar observacao
    }

    PAGAMENTO {
        bigint pedido_id PK, FK
        integer tipo_pagamento
        integer status_pagamento
        timestamp data_hora
    }
```

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
- **Docker** e **Docker Compose** instalados
- **Java 25+** (opcional caso use o wrapper Maven local)

### 1. Clonar o repositório
```bash
git clone git@github.com:jailsonribeirodev-sys/pizzaria-api.git
cd pizzaria-api
```

### 2. Iniciar o Banco de Dados (PostgreSQL + pgAdmin)
```bash
docker compose up -d
```
- **PostgreSQL:** `localhost:5435` (banco: `pizzaria`, user/pass: `postgres`/`postgres`)
- **pgAdmin 4:** `http://localhost:15435`

### 3. Executar a Aplicação Spring Boot
```bash
./mvnw spring-boot:run
```
A API iniciará na porta **8083** (configurada em `src/main/resources/application.yaml`).

---

## 🗺️ Roadmap de Desenvolvimento

- [x] Modelagem de Domínio e Mapeamento JPA
- [x] Ambiente de Banco de Dados com Docker Compose
- [ ] Implementação da Camada de Repositórios (`Spring Data JPA`)
- [ ] Carga Inicial de Dados para Testes (`Seed / CommandLineRunner`)
- [ ] Regras de Negócio e Serviços (`Service Layer`)
- [ ] DTOs (Data Transfer Objects) e Validações
- [ ] Endpoints RESTful (`Controllers`)
- [ ] Tratamento Global de Exceções (`ProblemDetail` / `@ControllerAdvice`)
- [ ] Documentação de API com Swagger/OpenAPI
- [ ] Testes Automatizados (Unitários e Integração)

---

## 👤 Autor

Desenvolvido por **Jailson Ribeiro**.
