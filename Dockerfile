FROM maven:3-amazoncorretto-17 as builder

WORKDIR /app

COPY src ./src
COPY pom.xml ./pom.xml
RUN mvn clean install -DskipTests

FROM openjdk:17-alpine as runner

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENV H2_CONSOLE_ENABLED=true \
    JPA_OPEN_IN_VIEW=false \
    JPA_GENERATE_DDL=true \
    JPA_HIBERNATE_DDL_AUTO=create \
    HIKARI_CONNECTION_TIMEOUT=20000 \
    HIKARI_MAXIMUM_POOL_SIZE=5 \
    DATASOURCE_JDBC_DRIVER=postgresql \
    DATASOURCE_HOST=localhost \
    DATASOURCE_PORT=5432 \
    DATASOURCE_DBNAME=postgres \
    DATASOURCE_USERNAME=postgres \
    DATASOURCE_PASSWORD=password \
    SWAGGER_UI_ENABLED=true \
    SCRAPPER_API_URL=localhost:3000

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=env", "/app/app.jar"]