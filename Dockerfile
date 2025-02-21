# 1️⃣ Usar una imagen base de OpenJDK ligera
FROM openjdk:17-jdk-slim

# 2️⃣ Establecer el directorio de trabajo dentro del contenedor
WORKDIR /home/app

# 3️⃣ Copiar el JAR generado por Gradle al contenedor
COPY build/libs/restapi-0.0.1-SNAPSHOT.jar app.jar

# 4️⃣ Exponer el puerto en el que corre tu aplicación (ajusta si es necesario)
EXPOSE 8080

# 5️⃣ Definir el comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
