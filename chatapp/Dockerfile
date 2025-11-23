# Use Java 17 to match your pom.xml java.version
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# copy the built jar from target into the image and rename to app.jar
# NOTE: ensure target/chatapp-0.0.1-SNAPSHOT.jar exists (build first)
COPY target/chatapp-0.0.1-SNAPSHOT.jar app.jar

# create non-root user and use it
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java -jar /app/app.jar"]
