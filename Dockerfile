# Dockerfile always strats with FROM instruction
# we use FROM onstruction to define the base image or a parent image for this image
FROM eclipse-temurin:17

# defianing the metadata:
LABEL mentainer = "podlaski83@gmail.com"

# defining directory name, whenever we run the container, then app direcotry will be created in a container
WORKDIR /app

# COPY instruction to copy source file from our system into a destination in a container
# basically we are going to copy this jar file from our project into the app folder
# todo where is target folder???
COPY target/csm-system-0.0.1-SNAPSHOT.jar /app/csmsystem-docker.jar

ENTRYPOINT ["java", "-jar", "csmsystem-docker.jar"]
