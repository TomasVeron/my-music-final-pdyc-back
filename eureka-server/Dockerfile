FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

COPY /eureka-server/pom.xml .
COPY /eureka-server/.mvn ./.mvn
COPY /eureka-server/mvnw .

RUN chmod +x mvnw

RUN ./mvnw clean install -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY /eureka-server/src ./src

RUN ./mvnw clean install -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/eureka-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8761

ENTRYPOINT ["java","-jar","eureka-server-0.0.1-SNAPSHOT.jar"]