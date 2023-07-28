FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

COPY /firebase-auth-server/pom.xml .
COPY /firebase-auth-server/.mvn ./.mvn
COPY /firebase-auth-server/mvnw .

RUN chmod +x mvnw

RUN ./mvnw clean install -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY /firebase-auth-server/src ./src

RUN ./mvnw clean install -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/firebase-auth-server-0.0.1-SNAPSHOT.jar .
COPY /firebase-auth-server/assets ./assets

ENTRYPOINT ["java","-jar","firebase-auth-server-0.0.1-SNAPSHOT.jar"]