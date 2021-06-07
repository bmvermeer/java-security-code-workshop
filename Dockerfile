FROM maven:3-openjdk-11 AS build
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project
RUN mvn package -DskipTests
#CMD mvn spring-boot:run

FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir /project
COPY --from=build /usr/src/project/target/java-application.jar /project/
WORKDIR /project
CMD java -jar java-application.jar
