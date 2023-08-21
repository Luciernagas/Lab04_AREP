package org.example.clase;

import java.net.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
        try {
            URL myURL = new URL("http://www.google.com:80/index.html?codigo=2123#address");
            System.out.println("Protocol: " + myURL.getProtocol());
            System.out.println("Authority: " + myURL.getAuthority());
            System.out.println("Host: " + myURL.getHost());
            System.out.println("Port: " + myURL.getPort());
            System.out.println("Path: " + myURL.getPath());
            System.out.println("Query: " + myURL.getQuery());
            System.out.println("File: " + myURL.getFile());
            System.out.println("Ref: " + myURL.getRef());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
