# Documentação do Projeto: accounts-payable

## Visão Geral do Projeto
O `accounts-payable` é um sistema moderno e **reativo** de gerenciamento de contas a pagar, com arquitetura desacoplada, construído com **Spring Boot WebFlux e Spring Data R2DBC**.

## Componentes da Stack
*   **ap-backend-api**: API RESTful **reativa** desenvolvida em Java 21 (Spring Boot WebFlux), utilizando Gradle para o gerenciamento de dependências e build. Implementa **Spring Data R2DBC** para persistência reativa e segue a **Arquitetura Hexagonal**.
*   **ap-frontend-web**: Interface do usuário desenvolvida em Angular (SPA).
*   **Banco de Dados**: MySQL 8, com migrações gerenciadas por Flyway (JDBC) e acesso de dados reativo via R2DBC.

## Preparação do Ambiente

### Requisitos
*   Java Development Kit (JDK) 21+
*   Gradle (opcional para execução manual, o wrapper `gradlew` já vem no projeto)
*   Node.js 22+ e Angular CLI
*   Docker & Docker Compose

## Guia de Execução

### 1. Desenvolvimento (Hot Reload)

#### Backend:
Navegue até `ap-backend-api`.
Use o Gradle Wrapper para executar a aplicação:
```bash
./gradlew bootRun
```

#### Frontend:
Navegue até `ap-frontend-web`.
Execute: `npm install` e depois `ng serve`. Acesse `http://localhost:4200`.

### 2. Produção (Docker Compose)
Esta é a forma recomendada para testar o sistema completo. O `Dockerfile` do backend já foi ajustado para usar a imagem base do JDK 21.

Vá para a pasta `deploy/`.
Inicie a stack:
```bash
docker compose up --build
```

## Ajustes Principais no Backend

O `ap-backend-api` foi atualizado para uma arquitetura **totalmente reativa** utilizando **Spring Boot WebFlux** e **Spring Data R2DBC**. As principais mudanças incluem:

*   **Substituição de tecnologias**: `spring-boot-starter-webmvc` e `spring-boot-starter-data-jpa` foram substituídos por `spring-boot-starter-webflux` e `spring-boot-starter-data-r2dbc`, respectivamente.
*   **Acesso a Dados Reativo**: A persistência de dados agora é gerenciada pelo R2DBC, garantindo um fluxo não bloqueante com o MySQL. O Flyway ainda é utilizado para migrações de esquema, acessando o banco via JDBC.
*   **Arquitetura Hexagonal**: A estrutura de pacotes reflete os princípios da Arquitetura Hexagonal (Ports & Adapters), separando as responsabilidades em:
    *   `domain`: Contém o núcleo de negócio (entidades, casos de uso/portas).
    *   `application`: Orquestra a lógica da aplicação (implementações de caso de uso).
    *   `infrastructure`: Responsável pela exposição via REST (controladores) e configurações de framework.

## Estrutura de Pastas

```
accounts-payable/
├── ap-backend-api/              # Backend (Java 21 / Spring Boot REST / Gradle)
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── pradolabs/
│   │                   └── ap/
│   │                       ├── domain/      # Núcleo de negócio (modelos, portas/interfaces de caso de uso, repositórios)
│   │                       ├── application/ # Lógica da aplicação (implementações de caso de uso)
│   │                       └── infrastructure/ # Camada de infraestrutura (controladores REST, configurações)
│   ├── Dockerfile               # Build usa JRE 21
│   ├── build.gradle.kts         # Configuração Gradle (Kotlin DSL)
│   └── settings.gradle.kts      # Configurações de módulo do Gradle
├── ap-frontend-web/             # Frontend (Angular)
│   ├── src/
│   ├── package.json
│   └── angular.json
├── deploy/                      # Catálogo de Stacks
│   ├── docker-compose.yml
│   └── .env
└── README.md                    # Documentação Geral
```


### Estrutura de Pastas - `deploy`

```
accounts-payable/
├── ap-backend-api/ (...)
├── ap-frontend-web/ (...)
└── deploy/
    ├── docker-compose.yml      # O arquivo principal que orquestra tudo
    ├── .env                    # Variáveis de ambiente globais
    └── database/               # Pasta opcional para scripts SQL do Flyway
        ├── V1__create_tables.sql
        └── V2__insert_data.sql
```

## Exemplo de Chamada de Endpoint

### `POST /api/v1/accounts-payable`

Cria um novo registro de contas a pagar.

**Requisição (Exemplo):**
```bash
curl -X POST \
  http://localhost:8080/api/v1/accounts-payable \
  -H 'Content-Type: application/json' \
  -d 
  {
    "description": "Payment for office supplies",
    "amount": 150.75,
    "dueDate": "2026-03-15",
    "bills": "INV-2026-001",
    "createdAt": "2026-01-10T10:00:00",
    "updatedAt": "2026-01-10T10:00:00"
  }
```

**Resposta (Exemplo):
```json
{
  "id": 1,
  "description": "Payment for office supplies",
  "amount": 150.75,
  "dueDate": "2026-03-15",
  "bills": "INV-2026-001",
  "createdAt": "2026-01-10T10:00:00",
  "updatedAt": "2026-01-10T10:00:00"
}
```
