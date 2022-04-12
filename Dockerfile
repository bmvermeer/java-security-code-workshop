FROM maven:3-openjdk-8 as build
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project
RUN --mount=target=$HOME/.m2,type=cache mvn package -DskipTests
#CMD mvn spring-boot:run

FROM eclipse-temurin:8-jre
RUN mkdir /project
COPY --from=build /usr/src/project/target/java-application.jar /project/
WORKDIR /project
CMD java -jar java-application.jar
