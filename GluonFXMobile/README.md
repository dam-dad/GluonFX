# GluonFXMobile

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

Aplicación administrativa desarrollada con Gluon Mobile.

## Requisitos

Para el desarrollo y ejecución del proyecto es necesario:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 2.2](https://gradle.org/downloads)
- [Gluon Mobile Plugin for eclipse](https://marketplace.eclipse.org/content/gluon-plugin)
- [Android SDK](https://developer.android.com/studio)

## Ejecutar la aplicación y generar .apk:

Existen distintas maneras de ejecutar la aplicación:

*  Demo de escritorio: seleccionar Gradle Task -> Run Apllication.
*  Generar apk sin firmar: Seleccionar Gradle Task -> gluon mobile for android -> android

## Ejemplo de uso de RestProvider: 

```java
		RestClient restClient = RestClient.create()
						.method("GET")
						.host("http://52.161.156.63:4132")
						.path("product")
						.contentType("application/json;charset=UTF-8");

		GluonObservableList<Product> result = DataProvider.retrieveList(restClient.createListDataReader(Product.class));

		result.initializedProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				model.setListProducts(result);

			}
		});

```



## Ejemplo de uso ShareService: 

```java
  Services.get(ShareService.class).ifPresent(share -> {
            			                share.share("document/pdf", pdfFile);
            			            });
```



## Ejemplo de uso PictureService: 

```java
Services.get(PicturesService.class).ifPresent(pictures -> {				
					pictures.loadImageFromGallery().ifPresent(image -> {
						model.setImgProduct(image);
					});
				});
```





## Copyright

Más información acerca de la licencia [GNU General Public License v3.0](https://github.com/dam-dad/GluonFX/blob/master/LICENSE) 