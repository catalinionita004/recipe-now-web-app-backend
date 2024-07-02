# Folosește o imagine Maven oficială pentru a construi aplicația
FROM maven:3.8.4-openjdk-17 AS build

# Setează directorul de lucru în container
WORKDIR /app

# Copiază fișierele de proiect în container
COPY pom.xml .
COPY src ./src

# Construiește aplicația
RUN mvn clean package -DskipTests

# Folosește o imagine JDK oficială pentru a rula aplicația
FROM openjdk:17-jdk-slim

# Setează variabila de mediu pentru versiunea UTF-8
ENV LANG C.UTF-8

# Setează directorul de lucru în container
WORKDIR /app

# Copiază fișierul JAR generat din etapa de build în imaginea finală
COPY --from=build /app/target/recipe-now-web-app-backend-0.0.1-SNAPSHOT.jar app.jar

# Expune portul pe care rulează aplicația
EXPOSE 8080

# Comanda pentru a rula aplicația
CMD ["java", "-jar", "app.jar"]
