package org.example.laboratorio;

import java.util.*;

public class Cache {
    public static List<String> cacheList = new ArrayList<>();
    public String apiUrl;

    public Cache(String movie){
        this.apiUrl = movie;
    }

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

    public static boolean OnCache(String apiUrl){
        return cacheList.contains(apiUrl);
    }

    public static List<String> getCacheList() {
        return cacheList;
    }
}
