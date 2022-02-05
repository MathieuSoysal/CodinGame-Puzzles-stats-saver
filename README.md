[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=MathieuSoysal_CodinGame-Puzzles-stats-saver&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=MathieuSoysal_CodinGame-Puzzles-stats-saver)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=MathieuSoysal_CodinGame-Puzzles-stats-saver&metric=coverage)](https://sonarcloud.io/summary/new_code?id=MathieuSoysal_CodinGame-Puzzles-stats-saver)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=MathieuSoysal_CodinGame-Puzzles-stats-library&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=MathieuSoysal_CodinGame-Puzzles-stats-saver)
![GitHub Actions](https://github.com/MathieuSoysal/CodinGame-Puzzles-stats-saver/workflows/Java%20CI%20with%20Maven/badge.svg)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md) 

# <img src="https://cdn.iconscout.com/icon/free/png-512/java-43-569305.png" width="100"> CodinGame Puzzles Stats Saver [![GitHub](https://img.shields.io/badge/license-Apache%202.0%20License-green)](LICENSE)

A program to save regularly the statistics of CodinGame's puzzles to your MongoDB database.

## Use the program

### Install

```bash
$ docker pull ghcr.io/mathieusoysal/codingame-puzzles-stats-saver:v1.0.2
```
### Run

```bash
$ docker run \
  -e MONGODB_PASSWORD=<the password> \
  -e MONGODB_USERNAME=<the username> \
  -e MONGODB_ADDRESS=<the address> \
  -e MONGODB_DATABASE=<the database> \
  ghcr.io/mathieusoysal/codingame-puzzles-stats-saver:v1.0.2 --period 24
```

*The `--period <number>` argument define the period in hours to execute the saver, you can define this to `0` if you want a unique single execution.*

## How to contribute

### Clone the project

```bash
$ git clone https://github.com/MathieuSoysal/CodinGame-Puzzles-stats-saver.git
```

### Test the project

```bash
$ mvn test
```
*note: you don't need a MongoDB database, the project has a embeded MongoDB database for testing*

## Contact

If you have any problems to setup the program do not hesitate to contact me at Mathieu.Soysal@etu.umontpellier.fr
