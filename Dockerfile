FROM java:8
MAINTAINER SimpleX <i@denghb.com>
VOLUME /tmp
ADD ./simplex-launcher/target/simplex.jar /app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]