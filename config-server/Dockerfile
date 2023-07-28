FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

COPY /config-server/pom.xml .
COPY /config-server/.mvn ./.mvn
COPY /config-server/mvnw .

RUN chmod +x mvnw

RUN ./mvnw clean install -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY /config-server/src ./src

RUN ./mvnw clean install -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/config-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]