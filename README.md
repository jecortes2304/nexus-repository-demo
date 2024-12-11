# PDF Downloader

PDF Downloader is a simple Java library for downloading PDF files from URLs.

## Features

- Downloads PDF files from specified URLs.
- Automatically names the downloaded files.
- Uses OkHttp to handle HTTP requests.

## Installation

Add the following dependency to your `pom.xml` file:

```xml

<dependency>
    <groupId>com.cortestudios</groupId>
    <artifactId>pdf-downloader</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

## Docker

- The docker-compose needs an environment file named `.env` with the following variables:

```env
DUCKDNS_SUBDOMAINS=YOUR_DUCKDNS_SUBDOMAINS
DUCKDNS_TOKEN=YOUR_DUCKDNS_TOKEN
TZ=Europe/Madrid
```

- Build the Docker image:

```bash
  docker-compose build
```
