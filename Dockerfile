FROM openjdk:12
LABEL seongwon="seongwon@edu.hanbat.ac.kr"

COPY ./build/libs/inventory-*.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar", "app.jar"]