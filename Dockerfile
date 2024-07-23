FROM eclipse-temurin:17

LABEL mentainer=peterhamza6@gmail.com

WORKDIR /app

COPY target/structured_blog-0.0.1-SNAPSHOT.jar /app/structured_blog.jar

ENTRYPOINT["java", "-jar ", "structured_blog.jar"]