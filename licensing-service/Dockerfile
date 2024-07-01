# stage
# base image with java rntime
# creating and upacking the java application jar file
FROM openjdk:8-jdk-slim as build

#Maintainer info
LABEL maintainer = "Mathias Birhane <mattbirhane@gmail.com>"

#obtain jar file from maven configuration
ARG JAR_FILE

#copy jar file into image filesystem
COPY ${JAR_FILE} app.jar

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#stage 2
#Same Java runtime
FROM openjdk:8-jdk-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.optimagrowth.license.LicenseServiceApplication"]