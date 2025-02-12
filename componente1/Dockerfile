# Use uma imagem base Java 21
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie os arquivos compilados do Maven para o contêiner
COPY target/componente1.jar app.jar

# Exponha a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]