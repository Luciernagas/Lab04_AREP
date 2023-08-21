## APLICACIONES DISTRIBUIDAS (HTTP, SOCKETS, HTML, JS, MAVEN, GIT)

En el siguiente proyecto se realizó la construcción de una aplicación para consultar la información de películas de cine con la utilización del API gratuito de https://www.omdbapi.com/, teniendo en cuenta que su implementación sea eficiente en cuanto a recursos, es decir, que cuente con un caché evite la realización de consultas repetidas al API externo.

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
mvn package
~~~
Cuando en la terminal veamos el mensaje "Listo para recibir ..." ingresamos mediante nuestro browser a la ruta http://localhost:35000.
Al realizar los pasos anteriores se espera visualizar lo siguiente en la página web:
![image](https://github.com/Luciernagas/Lab01_AREP/assets/104604359/2b274874-f2ad-410d-a085-8a5cab1772ee)
![image](https://github.com/Luciernagas/Lab01_AREP/assets/104604359/c6363e24-daae-4984-9775-8f41e88c16ac)

* * *
### Construido con
* Maven - Gestión de dependencias

* * *
### Licencia
Este proyecto esta autorizado bajo la licencia que se puede encontar en el archivo LICENSE.txt, en el se pueden encontrar los detalles

* * *
### Autores
♡ Luisa Valentina De la hoz Rocha ♡
