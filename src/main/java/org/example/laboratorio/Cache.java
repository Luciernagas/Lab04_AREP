package org.example.laboratorio;

import java.util.*;

public class Cache {
    public static List<String> cacheList = new ArrayList<>();
    public String apiUrl;

    public Cache(String movie){
        this.apiUrl = movie;
    }

    /**
     * Añade una URL de API al caché si aún no está presente en memoria, si se encuentra en memoria no realiza ninguna acción
     * @param apiUrl URl en la api a la pelicula que se añadira a caché
     */
    public static void addMovieToCache(String apiUrl){
        if(!OnCache(apiUrl)){
            cacheList.add(apiUrl);
        }else{
            for(int i = 0; i < cacheList.size() ; i ++){
                if(cacheList.get(i).equals(apiUrl)){
                    break;
                }
            }
        }
    }

    /**
     * Verifica si la url de una pelicula ya está en el caché
     * @param apiUrl URl en la api a la pelicula a verificar si está en caché
     * @return true si la url está en caché y false si no está en caché
     */
    public static boolean OnCache(String apiUrl){
        return cacheList.contains(apiUrl);
    }

    public static List<String> getCacheList() {
        return cacheList;
    }
}
