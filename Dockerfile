# Usa uma imagem Maven com Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte do projeto e o compila
COPY src ./src
RUN mvn clean package -DskipTests

# Cria a imagem final com a aplicação compilada
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o arquivo .jar do build anterior para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Porta exposta pela aplicação
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]