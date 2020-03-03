# GluonFX

Proyecto Java de gestión administrativa. Frameworks utilizados en el proyecto: 

  - Gluon Mobile.
  - JavaFX.
  - Spring.
  - Hibernate.

## Acerca del proyecto

El objetivo principal del proyecto, se ha centrado en poder desarrollar una aplicación funcional que usase JavaFX para como framework de desarrollo interfaces, siendo esta aplicación compatible con la mayoría de plataformas positibles,  dispositivos móviles "Android e IOS", y como aplicación de escritorio para Windows, Linux, MacOs.  Actualmente, Gluon Mobile es el único framework que nos permite esa posibilidad y en el que se ha basado el proyecto. 

###  Write Once, Run Anywhere 

Gracias a GluonMobile, se ha podido llevar el lema inicial de java que decía "Escríbelo una vez, ejecútalo en cualquier lugar" al mundo móvil. 

###  Estructura

Se compone de 3 subproyectos, siendo cada uno de estos una aplicación en sí: 

- ####  GluonFXServices: 
Proyecto Spring MVC, con funcionalidades CRUD implementadas a través de "Hibernate", generación de informes con "JasperReports", subida de imágenes a hosting...

- #### GluonFXMobile: 
Aplicación administrativa usando "GluonMobile" para su desarrollo. Genera ".apk"  para Android y ".jar" ejecutable para escritorio. 

- #### GluonFXDesktop: 
Aplicación JavaFX nativa para escritorio, con "Hibernate" integrado para realizar operaciones CRUD.


| Proyecto        | README                                   |
| --------------- | ---------------------------------------- |
| GluonFXServices | [/ServicesGluonFX/README.md][PlDb]       |
| GluonFXMobile   | [/GluonFXMobile/GluonFX/README.md][PlGh] |
| GluonFXDesktop  | [/GluonFXDesktop/README.md][PlGd]        |

####  Esquema del proyecto:

![](https://i.imgur.com/rwity11.png)