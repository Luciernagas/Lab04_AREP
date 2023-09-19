package org.example;
import org.example.laboratorio.HttpServer;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpServerTest {

    @Test
    void testDefaultResponse() {
        String response = HttpServer.defaultResponse();
    }

    @Test
    void testImageService() throws IOException {
        URL url = new URL("http://localhost:35000/image");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }

    @Test
    void testHtmlService() throws IOException {
        URL url = new URL("http://localhost:35000/html");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }
}
