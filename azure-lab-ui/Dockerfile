FROM java:8-jre-alpine

VOLUME /tmp
EXPOSE 8080
ADD ./build/libs/*.jar app.jar

RUN sh -c 'touch /app.jar'
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Ddeploy.path=/gssp -jar /app.jar
