package com.easy.url;

import com.easy.url.Exception.DuplicateShortenUrl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by amahagna on 11/24/14.
 */
public class DB {


    private static final Map<String,String> DB = new ConcurrentHashMap<String, String>();



    public static synchronized void addUrl(String url,String shortenUrl) throws DuplicateShortenUrl {

        boolean isExist = DB.containsKey(shortenUrl);
        if (isExist) {
            throw new DuplicateShortenUrl("duplicate shorten url found [ " +  shortenUrl + "] for two urls [" + url + "] and ["  + DB.get(shortenUrl)+"]"  );
        }
        DB.put(shortenUrl,url);

    }


    public static String fetchUrl(String shortenUrl) {

        // return null if not exist
        return DB.get(shortenUrl);

    }



}
