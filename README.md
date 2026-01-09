# Documentação do Projeto: accounts-payable

## Visão Geral do Projeto
O `accounts-payable` é um sistema moderno de gerenciamento de contas a pagar, com arquitetura desacoplada.

## Componentes da Stack
*   **ap-backend-api**: API RESTful desenvolvida em Java 21 (Spring Boot), utilizando Gradle para o gerenciamento de dependências e build.
*   **ap-frontend-web**: Interface do usuário desenvolvida em Angular (SPA).
*   **Banco de Dados**: MySQL.

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

O arquivo `build.gradle.kts` agora define a compatibilidade com a versão 21 do Java:
```kotlin
// Exemplo de trecho do build.gradle.kts
plugins {
    // ... plugins do Spring Boot e Kotlin
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
// ...
```

E o `Dockerfile` dentro de `ap-backend-api` agora utiliza uma imagem base apropriada para Java 21:
```dockerfile
# Exemplo de Dockerfile (Multi-stage build)
FROM gradle:8.5-jdk21 AS build
# ... (etapa de build)

FROM bellsoft/liberica-openjdk-alpine:21-crs-slim AS runtime
# ... (etapa de execução)
```

## Estrutura de Pastas

```
accounts-payable/
├── ap-backend-api/              # Backend (Java 21 / Spring Boot REST / Gradle)
│   ├── src/
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