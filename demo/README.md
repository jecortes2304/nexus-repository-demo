# PDF Downloader Demo Application

A Spring Boot application demonstrating the usage of the PDF Downloader library.

## Features

- Spring Boot 3.4.0
- Integration with PDF Downloader library
- Simple web application functionality

## Prerequisites

- Java 17
- Maven 3.6+
- Access to private Nexus repository

## Setup

1. Configure repository access in pom.xml:
```xml
<repositories>
    <repository>
        <id>nexus-releases</id>
        <url>https://nexus-cs-aws.duckdns.org/repository/maven-public/</url>
    </repository>
</repositories>
```

2. Configure Maven settings.xml for authentication

## Building

```bash
  mvn clean install
```

## Running

```bash
  mvn spring-boot:run
```

## License

Apache License 2.0