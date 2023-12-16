# Docker Build Stage
FROM maven:3.8-openjdk-17 AS build


# Build Stage
WORKDIR /opt/app

COPY ./ /opt/app
RUN mvn clean install -DskipTests


# Docker Build Stage
FROM maven:3.8-openjdk-17

COPY --from=build /opt/app/target/*.jar app.jar

ENV PORT 8081
ENV DBHOST "devhubdb"
EXPOSE $PORT

ENTRYPOINT ["java","-Dspring.data.mongodb.host=${DBHOST}", "-jar","-Xmx1024M","-Dserver.port=${PORT}","app.jar"]
