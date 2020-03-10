FROM java:8
MAINTAINER SimpleX <i@denghb.com>

VOLUME /tmp

ADD @project.build.finalName@.jar @project.build.finalName@.jar

RUN sh -c 'touch /@project.build.finalName@.jar'

ENV JAVA_OPTS=""

CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /@project.build.finalName@.jar
