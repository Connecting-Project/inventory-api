FROM openjdk:12
LABEL seongwon="seongwon@edu.hanbat.ac.kr"

COPY ./build/libs/inventory-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD [ "java", "-jar", "inventory-0.0.1-SNAPSHOT.jar"]