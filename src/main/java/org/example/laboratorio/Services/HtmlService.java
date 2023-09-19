package org.example.laboratorio.Services;

import org.example.laboratorio.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

@Component
public class HtmlService {
    public static String returnHtml(PrintWriter out){
        Path file = Paths.get("src/main/resources/www/page2.html");

        String defaultHeader = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html \r\n"
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
        return defaultHeader;
    }
}
