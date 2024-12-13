FROM openjdk:17-oracle
WORKDIR /app
COPY . .

FROM adoptopenjdk/openjdk11-jdk-hotspot:17-jre-slim
WORKDIR /app
COPY --from=builder /app/target/Order.jar Order.jar