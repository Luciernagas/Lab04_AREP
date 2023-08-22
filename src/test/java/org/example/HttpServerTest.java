package org.example;

import org.example.laboratorio.HttpServer;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpServerTest {
    @Test
    public void testSearchMovie() throws IOException {
        String movie = "{\"Title\":\"Freaky Friday\",\"Year\":\"2003\",\"Rated\":\"PG\",\"Released\":\"06 Aug 2003\",\"Runtime\":\"97 min\",\"Genre\":\"Comedy, Family, Fantasy\",\"Director\":\"Mark Waters\",\"Writer\":\"Mary Rodgers, Heather Hach, Leslie Dixon\",\"Actors\":\"Jamie Lee Curtis, Lindsay Lohan, Mark Harmon\",\"Plot\":\"An overworked mother and her daughter do not get along. When they switch bodies, each is forced to adapt to the other's life for one freaky Friday.\",\"Language\":\"English, Mandarin\",\"Country\":\"United States\",\"Awards\":\"5 wins & 11 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BYmU4NTk4OWYtMjE4My00MGVkLTgwY2EtZTZjN2YyOGFiMDQ0L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"6.3/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"88%\"},{\"Source\":\"Metacritic\",\"Value\":\"70/100\"}],\"Metascore\":\"70\",\"imdbRating\":\"6.3\",\"imdbVotes\":\"146,064\",\"imdbID\":\"tt0322330\",\"Type\":\"movie\",\"DVD\":\"27 May 2016\",\"BoxOffice\":\"$110,230,332\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
        HttpServer.formatText(movie);
        String expectedResponse = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n"
                + HttpServer.formatText(movie);

        String actualResponse = HttpServer.searchMovie("freaky friday");

        assertEquals(expectedResponse, actualResponse);
    }
}
