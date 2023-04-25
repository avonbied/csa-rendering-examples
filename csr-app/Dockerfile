# syntax=docker/dockerfile:1

# 1st Docker build stage: build the project with Maven
FROM maven:3-eclipse-temurin-17-alpine as builder
WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
# RUN ./mvnw dependency:resolve
COPY src src
RUN mvn package -DskipTests -B

# # 2nd Docker build stage: copy builder output and configure entry point
FROM eclipse-temurin:17-jre-alpine
ENV APP_DIR /home/app
ENV APP_FILE csrapp.jar

EXPOSE 8080

WORKDIR $APP_DIR
COPY --from=builder /app/target/*-fat.jar ./$APP_FILE

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -Dio.vertx.web.route.param.extended-pattern=true -jar $APP_FILE"]