package org.example.laboratorio;

import org.example.laboratorio.Services.HtmlService;
import org.example.laboratorio.Services.ImageService;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class HttpServer {
    static Map<String, Method> servicios = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // leer en linea de comandos java -cp ...
        for(String arg: args){
            Class c = Class.forName(arg);
            Method[] metodos = c.getDeclaredMethods();
            for(Method m: metodos){
                if(m.isAnnotationPresent(getMapping.class)){
                    getMapping anotacion = m.getAnnotation(getMapping.class);
                    String valor = anotacion.value();
                    servicios.put(valor ,m);
                }
            }
        }
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
            String inputLine, outputLine = null;
            boolean firstReqLine = true;
            String request = "";
            String path = "";
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstReqLine) {
                    String[] tokenizedRequest = inputLine.split(" ");
                    path = tokenizedRequest[1];
                    firstReqLine = false;
                    request = inputLine;
                }
                if (!in.ready()) {
                    break;
                }
            }

            if(path.startsWith("/image")){
                outputLine = ImageService.returnImage(out, output);
            }

            else if(path.startsWith("/html")){
                outputLine = HtmlService.returnHtml(out);
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
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