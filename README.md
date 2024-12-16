# Nexus Repository Demo Project

A demonstration project showing a complete development workflow using a private Nexus repository. This project includes
a reusable Java library and a demo application that uses it.

## Project Structure

```
nexus-repository-demo/
├── pdf-downloader/     # PDF download library with complete development lifecycle
├── demo/              # Spring Boot demo application using the library
└── pom.xml           # Parent POM file
```

## Components

### PDF Downloader Library

A Java library for downloading PDF files from URLs. Features complete development lifecycle including:

- Unit testing
- Code coverage
- Documentation
- Code quality checks
- Deployment to private Nexus repository

[View PDF Downloader Documentation](./pdf-downloader/README.md)

### Demo Application

A simple Spring Boot application demonstrating how to use the PDF Downloader library in a real project.

[View Demo Application Documentation](./demo/README.md)

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker and Docker Compose (for running Nexus repository)

## Building

To build all modules:

```bash
  mvn clean install
```

## License

This project is licensed under the Apache License 2.0.