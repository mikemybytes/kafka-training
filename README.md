# Kafka training materials

This repository contains examples of using Apache Kafka with Spring Boot apps.
[Spring for Apache Kafka (spring-kafka)](https://spring.io/projects/spring-kafka) has been picked as a library of choice
as it's quite low level ("close" to Kafka) and does not introduce additional levels of abstraction.

## Prerequisites

### Software requirements
- Java 11+ (but seriously, you should already run on 17+)
- Docker & Docker Compose
- Bash-compatible terminal.

All the exercises should run smoothly with the latest IntelliJ Idea.

### Local Kafka cluster

Start local Kafka cluster using Docker Compose
   ```bash
   # run from project root:
   docker compose up
   ```

Keep it running for the whole time. Once the training is over, stop it with `Ctrl+C`.

## Table of contents

- [Exercise 1 - Getting to know Apache Kafka](exercises/ex_01.md)
- [Exercise 2 - Introducing Spring Kafka](exercises/ex_02.md)
- [Exercise 3 - JSON and error handling](exercises/ex_03.md)
- [Exercise 4 - Kafka Streams](exercises/ex_04.md)
