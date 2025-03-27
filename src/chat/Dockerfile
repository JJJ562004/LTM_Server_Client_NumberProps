# Use OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all Java files to the container
COPY . .

# Compile the Java server file
RUN javac src/chat/ServerNumsProps.java

# Run the Java application
CMD ["java", "src.chat.ServerNumsProps"]
