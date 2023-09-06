## MICROFRAMEWORKS WEB

En el siguiente proyecto se realizó la construcción un servidor web que soporte una funcionalidad similar a la de Spark.

* * *
### Prerrequisitos
Se debe tener instalado java, maven y git.
* Descargar maven en  http://maven.apache.org/download.html
* Descargar git en https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
  
* * *
### Instalando
Se debe clonar el proyecto con el comando:
~~~
git clone https://github.com/Luciernagas/Lab02_AREP.git
~~~
En la terminal se debe ingresar el siguiente comando para compilar y empaquetar el proyecto:
~~~
mvn package
~~~
Finalmente para ejecutar el programa se debe ingresar el siguiente comando:
~~~
java -cp "./target/classes" org.example.laboratorio.HttpServer
~~~
Cuando en la terminal veamos el mensaje "Listo para recibir ..." ingresamos mediante nuestro browser a la siguiente ruta:
```
localhost:35000/(el archivo o imagen que desea visualizar con su extensión correspondiente)
```

Los archivos disponibles en el disco local se encuentran en la ruta /src/main/resources, se encontrará 2 carpetas, images que contiene los archivos tipo imagen (jpg, png y gif) y www en donde se encontraran los archivos html, js y css, se tiene 2 archivos para la prueba de los html (index.html y page2.html) y por otro lado un archivo html en donde se probaban una página creada con JavaScript y css.

* * *
### Ejecutando pruebas
En la sección de pruebas se confirmará el correcto funcionamiento del servidor web:
1. Archivos .html
![image](https://github.com/Luciernagas/Lab02_AREP/assets/104604359/b48446be-2a21-4440-a84b-1d1556340e20)
![image](https://github.com/Luciernagas/Lab02_AREP/assets/104604359/c6e9893b-4f62-42a4-aef2-8ab80eba430c)

2. Imagenes .png , .jpg. , .gif
![image](https://github.com/Luciernagas/Lab02_AREP/assets/104604359/dc42d72f-afb8-4e20-ad40-dcfa5a284498)
![image](https://github.com/Luciernagas/Lab02_AREP/assets/104604359/d79e13d9-7aad-4335-9588-4936fbed0fd3)
![image](https://github.com/Luciernagas/Lab02_AREP/assets/104604359/b7491f05-d809-489a-8f05-747867d54804)

3. Archivos .js .css
![image](https://github.com/Luciernagas/Lab02_AREP/assets/104604359/a8c9b3d7-b9d0-4bf9-9214-ec76cc10a1f9)
![image](https://github.com/Luciernagas/Lab02_AREP/assets/104604359/382a5c07-4f52-407d-9692-e898945aa5b8)


* * *
### Arquitectura del prototipo
En este servidor, se registran diferentes rutas (URLs) junto con los controladores que manejan las solicitudes HTTP para esas rutas específicas. Luego, el servidor escucha en un puerto determinado (en este caso, el puerto 35000) y maneja las solicitudes entrantes para las rutas registradas, en mi concepto su arquitectura está dada por:
* Registro de Rutas. (Spark)
* Configuración del Servidor.
* Manejo de Solicitudes.
* Creación de Respuesta.
  
* * *
### Construido con
* Maven - Gestión de dependencias

* * *
### Autores
♡ Luisa Valentina De la hoz Rocha ♡
