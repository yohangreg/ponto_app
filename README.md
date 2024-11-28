# 📋 Ponto App

**Ponto App** é uma aplicação RESTful desenvolvida em Java com Spring Boot para o gerenciamento de registros de ponto de colaboradores. A aplicação permite autenticação, registro de usuários e controle de folhas de ponto, com endpoints bem definidos para cada funcionalidade.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.3.5**
- **Maven**
- **Docker**

## ⚙️ Funcionalidades

### Autenticação (`/api/auth`)
- **POST** `/api/auth/login`: Realiza login com credenciais.
- **POST** `/api/auth/register`: Registra um novo usuário.

### Controle de Ponto (`/api/timesheet`)
- **POST** `/api/timesheet/insert`: Insere um novo registro de ponto.
- **GET** `/api/timesheet/findAll`: Retorna todos os registros de ponto.
- **GET** `/api/timesheet/findById/{id}`: Retorna o registro de ponto por ID.
- **GET** `/api/timesheet/findByUser/{id}`: Retorna registros de ponto associados a um usuário específico.

### Gerenciamento de Usuários (`/api/user`)
- **GET** `/api/user/findAll`: Retorna todos os usuários.
- **PUT** `/api/user`: Atualiza informações de um usuário.
- **DELETE** `/api/user/delete/{id}`: Deleta um usuário pelo ID.
- **GET** `/api/user/findById/{id}`: Retorna informações de um usuário específico.

## 🛠️ Configuração do Ambiente

### Requisitos

- **Java 21**
- **Maven 3.9.9**
- **Docker** (opcional, para execução com container)

### Construção e Execução Local

1. Clone o repositório:

   ```bash
   git clone https://github.com/yohangreg/ponto_app.git
   cd ponto_app
   ```

2. Instale as dependências:

   ```bash
   mvn install
   ```

3. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação em `http://localhost:8080`.

## 🐳 Executando com Docker

1. Construa a imagem Docker:

   ```bash
   docker build -t ponto_app .
   ```

2. Execute o container:

   ```bash
   docker run -p 8080:8080 ponto_app
   ```

3. Acesse a aplicação em `http://localhost:8080`.

## 📂 Estrutura do Projeto

```
ponto_app/
├── src/
│   ├── main/
│   │   ├── java/com/pontoapp/
│   │   │   ├── controller/   # Controladores REST
│   │   │   ├── service/      # Serviços de negócio
│   │   │   └── dto/          # Data Transfer Objects (DTOs)
│   └── resources/
│       └── application.properties  # Configurações da aplicação
├── Dockerfile                # Arquivo para criação de container Docker
└── pom.xml                   # Gerenciador de dependências Maven
```

## 📄 Dependências (POM.xml)

As principais dependências utilizadas no projeto são:

- **Spring Boot Starter** para inicialização rápida da aplicação.
- **Spring Web** para construção de APIs REST.
- **Spring Boot Starter Test** para testes unitários.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 🔧 Configuração do Dockerfile

O Dockerfile está configurado para criar uma imagem que compila o projeto em duas etapas:

1. **Build Stage**: Utiliza a imagem `maven:3.9.9-eclipse-temurin-21` para baixar as dependências e compilar o `.jar`.
2. **Final Stage**: Utiliza `eclipse-temurin:21-jdk` para rodar a aplicação no container.

```dockerfile
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```