package org.example.laboratorio.Services;

import org.example.laboratorio.Component;

import java.io.*;

@Component
public class ImageService {
    public static String returnImage(PrintWriter out, OutputStream output) {
        File file = new File("src/main/resources/images/elmejorpokemon.png");
        String defaultHeader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] longFile = new byte[(int) file.length()];
            fileInputStream.read(longFile);
            defaultHeader = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: image/png \r\n"
                    + "Content-Length: " + longFile.length
                    + "\r\n";
            out.println(defaultHeader);
            output.write(longFile);

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return defaultHeader;
    }
}
