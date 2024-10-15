# Etapa 1: Build da aplicação
FROM gradle:7.6.0-jdk17 AS builder
WORKDIR /app

# Copia os arquivos de configuração do projeto
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Baixa as dependências
RUN gradle dependencies

# Copia o código-fonte do projeto
COPY src ./src

# Compila o projeto e gera o JAR
RUN gradle bootJar --no-daemon

# Etapa 2: Execução da aplicação
FROM eclipse-temurin:17-jdk AS runtime
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Expõe a porta usada pela aplicação
EXPOSE 8080

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
