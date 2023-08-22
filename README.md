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

* * *
### Diseño
#### Diagrama de clases
![image](https://github.com/Luciernagas/Lab01_AREP/assets/104604359/2b103640-edd3-4589-8afe-00d2941a9eec)

Como observamos en el diagrama de clases el proyecto se divide en 3 clases.
* HTTPServer: Es la clase principal, la cual contiene el main, esta clase crea un servidor HTTP que realiza busqueda de datos de películas, obtiene dichos datos de una API externa y almacena el caché de las busquedas en la API para mejorar el rendimiento.
  
* HttpConnectionExample: Es la clase encargada de realizar solicitudes HTTP GET a la API OMDB, con el fin de obtener los datos sobre películas solo con saber sus títulos. Esta clase se utiliza en la clase HttpServer con el fin de cuando se reciba una solicitud se buscara la información en la API.

* Cache: Es la clase encargada de verificar si existe alguna de las busquedas a la API en memoria, evitando consultas repetidas. Es utilizada en HTTPServer para realizar una confirmación de si la busqueda ya existe en memoria antes de hacerla.

#### Extensible
1. En el caso de desear agregar más tipos de respuesta se deberia expandiar la lógica en la clase HTTPServer para manejar nuevas solicitudes y generar sus respuestas, esto dado el caso que se quiera agregar nuevas funcionalidades al servidor.

#### Patrones
1. Singleton: La clase Cache maneja un atributo estático (cacheList) que implica que solo se utiliza un cache en todo el programa, en donde se guarda todas las busquedas realizadas.
   
2. Patrón Template method: se podria considerar que se maneja porque hay métodos en HttpServer que realiza una serie de pasos antes de devolver la respuesta final, como searchMovie quien verifica el caché y le da un formato a los datos dados por la API.

3. Patrón Observador: Se podria considerar que se maneja porque el código interactúa con el cliente mediante solicitudes HTTP y respuestas.

#### Modular
Se dividio la funcionalidad en clases separadas y métodos específicos para realizar tareas específicas como se puede observar y leer la explicación en el diagrama de clases.

