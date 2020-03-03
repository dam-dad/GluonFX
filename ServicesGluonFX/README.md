# GluonFXServices

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

Servicios de gestión administrativa, para el proyecto GluonFX.

## Requisitos

Para el desarrollo y ejecución del proyecto es necesario:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)

## Ejecutar la aplicación localmente

Existen distintas maneras de ejecutar la aplicación, la más sencilla de esta es lanzar  el método main en el `com.moimah.hibernate.spring.App`

Como alternativa se puede usar:  [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html):

```shell
mvn spring-boot:run
```

## Dependencias Maven: 

```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.8.5</version>
		</dependency>
		<dependency>
			<groupId>com.mashape.unirest</groupId>
			<artifactId>unirest-java</artifactId>
			<version>1.4.9</version>
		</dependency>
		<dependency>
			<groupId>net.sf.jasperreports</groupId>
			<artifactId>jasperreports</artifactId>
			<version>6.11.0</version>
		</dependency>
	</dependencies>
```

## Copyright

Más información acerca de la licencia [GNU General Public License v3.0](https://github.com/dam-dad/GluonFX/blob/master/LICENSE) 