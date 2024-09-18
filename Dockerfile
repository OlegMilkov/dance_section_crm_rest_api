FROM openjdk:17
WORKDIR /app
COPY /target/project_dance_section_crm_rest_api-0.0.1-SNAPSHOT.jar /app/crm_rest_api.jar
EXPOSE 5050

ENTRYPOINT ["java", "-jar", "crm_rest_api.jar"]