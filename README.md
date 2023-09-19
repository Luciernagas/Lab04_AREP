## TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

En el siguiente proyecto se realizó la construcción de un servidor Web (tipo Apache) en Java. El servidor es capaz de entregar páginas html e imágenes tipo PNG, además provee un framework IoC para la construcción de aplicaciones web a partir de POJOS.

* * *
### Prerrequisitos
Se debe tener instalado java, maven y git.
* Descargar maven en  http://maven.apache.org/download.html
* Descargar git en https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
  
* * *
### Instalando
Se debe clonar el proyecto con el comando:
~~~
git clone https://github.com/Luciernagas/Lab04_AREP.git
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
localhost:35000/image
```
En caso de querer visualizar la imagen tipo PNG
Por otro lado, en caso de querer visualizar la página html se ingresara la siguiente ruta en el browser:
```
localhost:35000/html
```

* * *
### Ejecutando pruebas
En la sección de pruebas se realizó una prueba, en ella se evalua y verifica el funcionamiento del servidor HTTP
Para la ejecución de pruebas se debe ingresar el siguiente comando en la terminal:
~~~
mvn test
~~~
Se espera que el resultado sea el siguiente:
![image](https://github.com/Luciernagas/Lab04_AREP/assets/104604359/d86565b4-d8bd-4811-a042-651bdc9a51f8)



Tambien se confirmará el correcto funcionamiento del servidor web:
![image](https://github.com/Luciernagas/Lab04_AREP/assets/104604359/154602f7-4352-4f17-9eb5-fbe3cf266a52)

![image](https://github.com/Luciernagas/Lab04_AREP/assets/104604359/df7f7bea-96b2-46ee-a0a6-8372c8ebfeab)

  
* * *
### Construido con
* Maven - Gestión de dependencias

* * *
### Autores
♡ Luisa Valentina De la hoz Rocha ♡
