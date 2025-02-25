# 🏗️ Etapa de construcción
FROM gradle:jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

# 🏃‍♂️ Etapa de producción con una imagen más liviana
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/restapi-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto necesario
EXPOSE 8080

# Iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
