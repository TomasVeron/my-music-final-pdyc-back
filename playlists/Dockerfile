FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

COPY /playlists/pom.xml .
COPY /playlists/.mvn ./.mvn
COPY /playlists/mvnw .

RUN chmod +x mvnw

RUN ./mvnw clean install -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY /playlists/src ./src

RUN ./mvnw clean install -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/playlists-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","playlists-0.0.1-SNAPSHOT.jar"]