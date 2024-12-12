# PDF Downloader

PDF Downloader is a simple Java library for downloading PDF files from URLs, demonstrating a complete workflow for library development, testing, documentation, and deployment using a private Maven repository.

## Features

- Downloads PDF files from specified URLs
- Automatically names the downloaded files
- Uses OkHttp for efficient HTTP requests
- Includes comprehensive unit tests
- Generates JavaDoc documentation
- Code quality checks with Checkstyle
- Code coverage reports with JaCoCo

## Installation

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.cortestudios</groupId>
    <artifactId>pdf-downloader</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

### Using with Private Nexus Repository

To use this library from our private Nexus repository, add the following repository configuration to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>nexus-cs</id>
        <url>https://nexus-cs-aws.duckdns.org/repository/maven-public/</url>
    </repository>
</repositories>
```

And configure authentication in your `~/.m2/settings.xml`:

```xml
<servers>
    <server>
        <id>nexus-cs</id>
        <username>your-username</username>
        <password>your-password</password>
    </server>
</servers>
```

## Development Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Docker and Docker Compose (for running Nexus repository)

### Building the Project

```bash
   mvn clean install
```

This will:
- Compile the source code
- Run unit tests
- Generate JavaDoc documentation
- Create source and JavaDoc JARs
- Run code coverage analysis
- Perform code style checks

### Code Quality Checks

The project includes several quality checks:

1. **JaCoCo Code Coverage**:
    - Minimum 50% line coverage required
    - Run: `mvn jacoco:report`
    - Reports location: `target/site/jacoco`

2. **Checkstyle**:
    - Uses custom rules defined in `checkstyle.xml`
    - Run: `mvn checkstyle:check`

3. **JavaDoc**:
    - Generated during build
    - Run: `mvn javadoc:javadoc`
    - Documentation location: `target/site/apidocs`


## Library Versioning

This project follows Semantic Versioning (SemVer) principles:

- **MAJOR** version (X.0.0): Incompatible API changes
- **MINOR** version (0.X.0): Add functionality in a backward-compatible manner
- **PATCH** version (0.0.X): Backward-compatible bug fixes

### Version Update Process

1. **For SNAPSHOT versions**:
   ```bash
   # Current version: 1.0.1-SNAPSHOT
   mvn versions:set -DnewVersion=1.0.1
   mvn clean deploy
   ```

2. **For release versions**:
   ```bash
   # After testing SNAPSHOT version
   mvn versions:set -DnewVersion=1.0.1
   mvn clean deploy
   
   # Prepare next development version
   mvn versions:set -DnewVersion=1.0.2-SNAPSHOT
   ```

3. **Version increment examples**:
    - Patch: 1.0.1 → 1.0.2 (bug fixes)
    - Minor: 1.0.1 → 1.1.0 (new features)
    - Major: 1.0.1 → 2.0.0 (breaking changes)

## Deployment and Publishing

### Publishing to Nexus Repository

1. **Configure Maven Settings**:
   Create or update `~/.m2/settings.xml`:
   ```xml
   <settings>
     <servers>
       <server>
         <id>nexus-releases</id>
         <username>your-username</username>
         <password>your-password</password>
       </server>
       <server>
         <id>nexus-snapshots</id>
         <username>your-username</username>
         <password>your-password</password>
       </server>
     </servers>
   </settings>
   ```

2. **Deploy SNAPSHOT Version**:
   ```bash
   mvn clean deploy
   ```
   This will deploy to the snapshots repository if version ends with `-SNAPSHOT`

3. **Deploy Release Version**:
   ```bash
   # Remove -SNAPSHOT suffix
   mvn versions:set -DnewVersion=1.0.1
   
   # Deploy to releases repository
   mvn clean deploy
   
   # Tag the release
   git tag -a v1.0.1 -m "Release version 1.0.1"
   git push origin v1.0.1
   
   # Prepare for next development version
   mvn versions:set -DnewVersion=1.0.2-SNAPSHOT
   git add pom.xml
   git commit -m "Prepare for next development version"
   git push
   ```

### Release Checklist

Before releasing a new version:

1. **Quality Checks**:
    - All tests pass: `mvn test`
    - Code coverage meets minimum: `mvn jacoco:check`
    - Checkstyle passes: `mvn checkstyle:check`
    - JavaDoc is complete: `mvn javadoc:javadoc`

2. **Version Selection**:
    - Review changes since last release
    - Determine version increment type (major/minor/patch)
    - Update version in pom.xml
    - Update documentation if needed

3. **Deployment Steps**:
   ```bash
   # Ensure all changes are committed
   git status
   
   # Run full verification
   mvn clean verify
   
   # Deploy to Nexus
   mvn deploy
   
   # Tag release in git
   git tag -a vX.Y.Z -m "Release version X.Y.Z"
   git push origin vX.Y.Z
   ```

4. **Post-Release**:
    - Update version to next SNAPSHOT
    - Update documentation with new version
    - Close released version in issue tracker
    - Announce release to users if needed

### Version Control Best Practices

1. **Branch Strategy**:
    - `main`: Latest stable release
    - `develop`: Next release development
    - `feature/*`: New features
    - `hotfix/*`: Emergency fixes

2. **Tag Convention**:
   ```bash
   # For releases
   git tag -a v1.0.1 -m "Release version 1.0.1"
   
   # For release candidates
   git tag -a v1.0.1-RC1 -m "Release candidate 1 for version 1.0.1"
   ```

3. **Commit Messages**:
    - Use conventional commits format
    - Reference issues/tickets when applicable
    - Include brief description of changes


## Infrastructure Setup

The project includes a complete infrastructure setup using Docker Compose:

### Components

1. **Nexus Repository Manager**:
    - Version: 3.69.0
    - Handles Maven artifacts storage
    - Port: 8082 (mapped from container 8081)
    - Persistent storage in `nexus-data` volume
    - Features:
        - Hosts Maven artifacts
        - Provides proxy to Maven Central
        - Supports snapshot and release repositories

2. **Nginx Proxy Manager**:
    - Manages SSL certificates and proxying
    - UI accessible on port 81
    - Handles HTTP (80) and HTTPS (443)
    - Configuration stored in `./nginx/data`
    - SSL certificates in `./nginx/letsencrypt`
    - Features:
        - Automatic SSL certificate management
        - Reverse proxy configuration
        - Access control and security settings

3. **DuckDNS Service**:
    - Manages dynamic DNS updates
    - Updates DNS records automatically
    - Configuration via environment variables
    - Features:
        - Automatic DNS record updates
        - Support for multiple subdomains
        - Configurable update intervals

### Environment Configuration

Create a `.env` file with the following variables:

```env
DUCKDNS_SUBDOMAINS=YOUR_DUCKDNS_SUBDOMAINS
DUCKDNS_TOKEN=YOUR_DUCKDNS_TOKEN
TZ=Europe/Madrid
```

### Docker Deployment

1. Start the infrastructure:
```bash
docker-compose up -d
```

2. Access services:
    - Nexus: https://nexus-cs-aws.duckdns.org
    - Nginx Proxy Manager: https://admin.nexus-cs-aws.duckdns.org

### Infrastructure Architecture

The Docker Compose setup creates a complete development infrastructure:

- All services are connected through a Docker network named `sonatype`
- Persistent volumes ensure data preservation between restarts
- Automatic container restart policies are configured
- Logging is configured with rotation to prevent disk space issues
- SSL termination is handled by Nginx Proxy Manager
- DNS management is automated through DuckDNS

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

## Contact

Jose Cortes - jcortes@unuware.com

Project Link: [https://github.com/jcortes2304/PdfDownloader](https://github.com/jcortes2304/PdfDownloader)

## Acknowledgments

- OkHttp for providing the HTTP client library
- Sonatype for Nexus Repository Manager
- jc21 for Nginx Proxy Manager
- LinuxServer.io for the DuckDNS container