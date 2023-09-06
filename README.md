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
git clone https://github.com/Luciernagas/Lab01_AREP
~~~
En la terminal se debe ingresar el siguiente comando para compilar y empaquetar el proyecto:
~~~
mvn package
~~~
Finalmente para ejecutar el programa se debe ingresar el siguiente comando:
~~~
java -cp "./target/classes" org.example.laboratorio.HttpServer
~~~
Cuando en la terminal veamos el mensaje "Listo para recibir ..." ingresamos mediante nuestro browser a la ruta http://localhost:35000.
Al realizar los pasos anteriores se espera visualizar lo siguiente en la página web:
![image](https://github.com/Luciernagas/Lab01_AREP/assets/104604359/2b274874-f2ad-410d-a085-8a5cab1772ee)
![image](https://github.com/Luciernagas/Lab01_AREP/assets/104604359/c6363e24-daae-4984-9775-8f41e88c16ac)

* * *
### Ejecutando pruebas
En la sección de pruebas se realizarón 2, la primera verifica el caché, que esté evite la realización de consultas repetidas al API externo y la segunda verifica la busqueda en la API, que la información dada por la API y presentada en la página web es la misma.

Para la ejecución de pruebas se debe ingresar el siguiente comando en la terminal:
~~~
mvn test
~~~
Se espera que el resultado sea el siguiente:
![image](https://github.com/Luciernagas/Lab01_AREP/assets/104604359/4d9816cb-7833-43df-86d9-d1c3959a1586)

* * *
### Construido con
* Maven - Gestión de dependencias

* * *
### Autores
♡ Luisa Valentina De la hoz Rocha ♡

* * *
### Licencia
Este proyecto esta autorizado bajo la licencia que se puede encontar en el archivo LICENSE.txt, en el se pueden encontrar los detalles


