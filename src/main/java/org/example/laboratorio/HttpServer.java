package org.example.laboratorio;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class HttpServer {
    static Map<String, MethodGetAndPost> servicios = new HashMap<String, MethodGetAndPost>();

    public static void main(String[] args) throws IOException {
        HttpServer.registrar("/index.html", (request, response) -> {
            return "./src/main/resources/www/index.html"; });
        HttpServer.registrar("/page2.html", (request, response) -> {
            return "./src/main/resources/www/page2.html";});
        HttpServer.registrar("/page3.html", (request, response) -> {
            return "./src/main/resources/www/page3.html";});
        HttpServer.registrar("/cielo.png", (request, response) -> {
            return "./src/main/resources/images/cielo.png";});
        HttpServer.registrar("/conejo.jpg", (request, response) -> {
            return "./src/main/resources/images/conejo.jpg";});
        HttpServer.registrar("/sanrio.gif", (request, response) -> {
            return "./src/main/resources/images/sanrio.gif";});

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            OutputStream output = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine;
            boolean firstReqLine = true;
            String request = "";
            String uri = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstReqLine) {
                    firstReqLine = false;
                    request = inputLine;
                    uri = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (servicios.containsKey(uri)) {
                uri = HttpServer.buscar(uri);
                createResponse(request, uri, out, output);
            }

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static void registrar(String url, MethodGetAndPost endpoint) {
        servicios.put(url, endpoint);
    }

    public static String buscar(String url) {
        MethodGetAndPost endpoint = servicios.get(url);
        if (endpoint != null) {
            return endpoint.handle(null, null); // Llama al método para obtener la ruta
        }
        return null; // Manejar el caso en el que el URL no se encuentra registrado
    }

    private static void createResponse(String request, String uri, PrintWriter out, OutputStream output) throws IOException {
        System.out.println("request to interpret: " + request);
        if (request.equals("")) {
            return;
        }
        String[] tokenizedRequest = request.split(" ");

        String httpVerb = tokenizedRequest[0];
        String path = tokenizedRequest[1];
        String protocol = tokenizedRequest[2];

        if (path.endsWith(".html") || path.endsWith(".js") || path.endsWith(".css") ) {
            String extension = path.substring(path.lastIndexOf('.') + 1);
            //Path file = Paths.get("./src/main/resources/www" + uri);
            Path file = Paths.get(uri);

            String defaultHeader = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/" + extension + "\r\n"
                    + "\r\n";

            Charset charset = Charset.forName("UTF-8");
            out.println(defaultHeader);
            try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    out.println(line);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

        } else if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".gif") ){
            String extension = path.substring(path.lastIndexOf('.') + 1);
            //Path file = Paths.get("./src/main/resources/images" + uri);
            Path file = Paths.get(uri);

            try{
                FileInputStream fileInputStream = new FileInputStream(file.toFile());
                byte[] longFile = new byte[(int) file.toFile().length()];
                fileInputStream.read(longFile);
                String defaultHeader = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: " + extension + "\r\n"
                        + "Content-Length: " + longFile.length
                        + "\r\n";
                out.println(defaultHeader);
                output.write(longFile);

            }catch (IOException x){
                System.err.format("IOException: %s%n", x);
            }

        }
    }

    /**
     * Método que realiza una búsqueda de información sobre una película utilizando el título proporcionado
     * Consulta la API de películas para obtener los datos de la película.
     * Consulta en memoria si la busqueda la fue realizada y se encuentra en cache
     * Le da un formato a los datos recibidos
     * @param movietitle titulo de la pelicula que se esta consultado
     * @return los detalles de la película
     * @throws IOException si ocurre un error durante la conexión a la API de películas
     */
    public static String searchMovie(String movietitle) throws IOException {
        String apiUrl = HttpConnectionExample.getAPI(movietitle);
        if(Cache.OnCache(apiUrl)) {
            int index = Cache.getCacheList().indexOf(apiUrl);
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: application/json\r\n"
                    + "\r\n"
                    + formatText(Cache.cacheList.get(index));
        }else{
            Cache.addMovieToCache(apiUrl);
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: application/json\r\n"
                    + "\r\n"
                    + formatText(apiUrl);
        }
    }

    /**
     * Formatea una cadena de texto que contiene información de la pelicula dada por la API, eliminando ciertos caracteres y ajustando el formato
     * @param apiUrl String que contiene la información de la pelicula dado por la API
     * @return Datos formateados
     */
    public static String formatText(String apiUrl){
        String text = apiUrl.replaceAll("[{}\"]", "").replaceAll(",", "\r\n").replaceAll(":", ": ");
        return text;
    }

    public static String defaultResponse(){
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>♡ movie search ♡</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Search by title</h1>\n"
                + "        <form action=\"/searchmovie\">\n"
                + "            <label for=\"title\">Title:</label><br>\n"
                + "            <input type=\"text\" id=\"title\" name=\"title\" value=\"Guardians of the galaxy\"><br><br>\n"
                + "            <input type=\"button\" value=\"Search\" onclick=\"loadGetMsg()\">\n"
                + "        </form> \n"
                + "        <div id=\"getrespmsg\"></div>\n"
                + "\n"
                + "        <script>\n"
                + "            function loadPostMsg(name){\n"
                + "                let url = \"/searchmovie?title=\" + name.value;\n"
                + "\n"
                + "                fetch (url, {method: 'POST'})\n"
                + "                    .then(x => x.text())\n"
                + "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n"
                + "            }\n"
                + "        </script>\n"
                + "    </body>\n"
                + "</html>";
        return response;
    }
}