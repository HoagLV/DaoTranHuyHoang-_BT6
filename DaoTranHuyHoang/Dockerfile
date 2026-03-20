# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests package

# Run stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"
ENTRYPOINT ["java", "-jar", "app.jar"]
