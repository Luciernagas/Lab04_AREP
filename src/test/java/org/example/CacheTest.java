package org.example;

import org.example.laboratorio.Cache;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CacheTest {
    @Test
    public void testAddToCache(){
        Cache.addMovieToCache("apiUrl0");
        Cache.addMovieToCache("apiUrl1");

        assertTrue(Cache.OnCache("apiUrl0"));
        assertTrue(Cache.OnCache("apiUrl1"));
        assertFalse(Cache.OnCache("apiUrl2"));
    }

}
