FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/bingo-0.0.1.jar
ARG DEPENDENCY=src/main/resources/cartones.js
COPY ${JAR_FILE} bingo.jar
COPY ${DEPENDENCY} /apps/cartones.js
ENTRYPOINT ["java","-jar","/bingo.jar"]