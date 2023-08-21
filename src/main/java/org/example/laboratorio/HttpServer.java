package org.example.laboratorio;

import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean runnig = true;

        while (runnig) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean firstLine = true;
            String uriString = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    firstLine = false;
                    uriString = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }

            System.out.println("Uri: " + uriString);

            if (uriString.startsWith("/searchmovie?")) {
                String movietitle = uriString.substring("/searchmovie?title=".length());
                outputLine = searchMovie(movietitle);

            } else {
                outputLine = getIndexResponse();

            }
            System.out.println("outputline:  " + outputLine);
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

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

    public static String formatText(String apiUrl){
        String text = apiUrl.replaceAll("[{}\"]", "").replaceAll(",", "\r\n").replaceAll(":", ": ");
        return text;
    }

    public static String getIndexResponse() {
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