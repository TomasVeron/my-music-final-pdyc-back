FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

COPY /gateway/pom.xml .
COPY /gateway/.mvn ./.mvn
COPY /gateway/mvnw .

RUN chmod +x mvnw

RUN ./mvnw clean install -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY /gateway/src ./src

RUN ./mvnw clean install -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8085

ENTRYPOINT ["java","-jar","gateway-0.0.1-SNAPSHOT.jar"]