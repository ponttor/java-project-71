# Difference Calculator (Java)

[![Actions Status](https://github.com/ponttor/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ponttor/java-project-71/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ponttor_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ponttor_java-project-71)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ponttor_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ponttor_java-project-71)

`gendiff` is a CLI utility for comparing two configuration files and showing their differences in a readable format.  
This project helps practice data structures, tree traversal, data formats (`json`, `yml`), and Java application architecture.

## Features

- Input format support: `json`, `yaml/yml`
- Output formats: `stylish` (default), `plain`, `json`
- Convenient CLI interface with command-line options

## Requirements

- Java 21+
- Gradle (or use the bundled wrapper: `./gradlew`)

## Installation and Run

```bash
git clone https://github.com/ponttor/java-project-71.git
cd java-project-71/app
./gradlew installDist
./build/install/app/bin/app --help
```

Quick run without installation:

```bash
cd app
./gradlew run --args="--help"
```

## Usage Examples

`stylish` format (default):

```bash
./build/install/app/bin/app path/to/file1.json path/to/file2.yml
```

`plain` format:

```bash
./build/install/app/bin/app --format plain path/to/file1.yml path/to/file2.json
```

`json` format:

```bash
./build/install/app/bin/app --format json path/to/file1.json path/to/file2.json
```

## Development

```bash
cd app
./gradlew build
./gradlew test
./gradlew checkstyleMain checkstyleTest
./gradlew jacocoTestReport
```
