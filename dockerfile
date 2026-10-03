FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# --- RUNTIME (AQUI ESTÁ A CORREÇÃO) ---
FROM eclipse-temurin:25-jre

# Copia o JAR gerado pelo Maven
COPY --from=build /app/target/pizzaria-api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Spring Boot usa (padrão é 8080)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]