FROM java:8
WORKDIR /

ADD build/libs/service.jar /app/service.jar

EXPOSE 5050 5050

CMD "java" "-jar" "/app/service.jar"