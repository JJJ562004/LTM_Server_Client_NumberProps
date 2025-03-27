# Use OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all project files to the container
COPY . .

# Compile the Java files (change this if using packages)
RUN javac -d out src/chat/ServerNumsProps.java

# Set classpath and run the Java application
CMD ["java", "-cp", "out", "chat.ServerNumsProps"]
