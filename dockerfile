FROM uatdockerdtr.alico.corp/infra/openjdk:8-jre-alpine
VOLUME /tmp
ARG JAR_FILE
ADD iib-0.0.1-SNAPSHOT.jar /iib-0.0.1-SNAPSHOT.jar
ENV TZ=Asia/Kolkata
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${spring_profiles_active}","-jar","/iib-0.0.1-SNAPSHOT.jar"]