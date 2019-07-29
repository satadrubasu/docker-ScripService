## While running the image, expect environment variables to be passed
## -e SPRING_DATASOURCE_USERNAME=""
## -e SPRING_DATASOURCE_PASSWORD=""
## -e SPRING_DATASOURCE_URL=""
## For Spring boot applications this naming will override any value in the application.properties file.

FROM java:8
FROM maven:alpine

LABEL maintainer="satadru.basu@gmail.com"

# Follow a best practice to install an application into CONTAINER's /usr/local/ folder
# create a new dir /usr/local/app/
# ./app/config/
# ./app/app.jar

RUN mkdir -p /usr/local/app
RUN mkdir -p /usr/local/app/logs
RUN chmod 755 -R /usr/local/app

# all subsequent commands of RUN/COPY/ADD will be executed within this dir
WORKDIR /usr/local/app/

VOLUME /tmp
EXPOSE 8080

ADD target/ScripApiService-0.0.1-SNAPSHOT.jar ScripApiService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/app/ScripApiService-0.0.1-SNAPSHOT.jar"]