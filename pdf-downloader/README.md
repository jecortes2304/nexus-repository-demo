# PDF Downloader Library

A Java library for downloading PDF files from URLs, demonstrating complete library development lifecycle with testing,
documentation, and deployment using a private Maven repository.

## Features

- Downloads PDF files from specified URLs
- Automatically names downloaded files
- Uses OkHttp for efficient HTTP requests
- Comprehensive unit tests
- JavaDoc documentation
- Code quality checks
- Code coverage reports

## Installation

Add to your pom.xml:

```xml

<dependency>
    <groupId>com.cortestudios</groupId>
    <artifactId>pdf-downloader</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Using with Private Nexus Repository

Add to your pom.xml:

```xml

<repositories>
    <repository>
        <id>nexus-releases</id>
        <url>https://nexus-cs-aws.duckdns.org/repository/maven-public/</url>
    </repository>
</repositories>
```

Configure ~/.m2/settings.xml:

```xml

<servers>
    <server>
        <id>nexus-releases</id>
        <username>your-username</username>
        <password>your-password</password>
    </server>
</servers>
```

## Development

### Prerequisites

- Java 17
- Maven 3.6+

### Building

```bash
  mvn clean install
```

This will:

- Compile source code
- Run unit tests
- Generate JavaDoc
- Create source and JavaDoc JARs
- Run code coverage analysis
- Perform code style checks

### Quality Checks

1. **JaCoCo Code Coverage**
    - Minimum 50% line coverage required
    - Run: `mvn jacoco:report`
    - Reports: target/site/jacoco

2. **Checkstyle**
    - Uses checkstyle.xml rules
    - Run: `mvn checkstyle:check`

3. **JavaDoc**
    - Run: `mvn javadoc:javadoc`
    - Location: target/site/apidocs

## Deployment

### To Nexus Repository

1. Deploy SNAPSHOT:

```bash
  mvn clean deploy
```

2. Deploy Release:

```bash
# Set release version
mvn versions:set -DnewVersion=1.0.1
mvn clean deploy

# Tag release
git tag -a v1.0.1 -m "Release version 1.0.1"
git push origin v1.0.1

# Set next version
mvn versions:set -DnewVersion=1.0.2-SNAPSHOT
```

### Release Checklist

1. **Quality Checks**
    - Run tests: `mvn test`
    - Check coverage: `mvn jacoco:check`
    - Run checkstyle: `mvn checkstyle:check`
    - Generate docs: `mvn javadoc:javadoc`[
      ]()
2. **Version Update**
    - Review changes
    - Update version in pom.xml
    - Update documentation

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## License

Apache License 2.0

## Contact

Jose Cortes - jcortes@unuware.com