# 1️⃣ Usar una imagen base de OpenJDK ligera
FROM gradle:jdk17-noble

# 2️⃣ Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# 3️⃣ Copiar el JAR generado por Gradle al contenedor
COPY ./ .

RUN gradle build -x test
# 4️⃣ Exponer el puerto en el que corre tu aplicación (ajusta si es necesario)
EXPOSE 8080

# 5️⃣ Definir el comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "build/libs/restapi-0.0.1-SNAPSHOT.jar"]
