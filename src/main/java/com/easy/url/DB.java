package com.easy.url;

import com.easy.url.Exception.DuplicateShortenUrl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DB ProtoType currently the DB is IMDB (in memory DB ) implement by HashTable
 * <p/>
 * <p/>
 * * store the data in Nosql database e.g. Redis key/value based rather than IMDB (in memory DB )
 * this type of database used for quickly storing basic information, it is extremely performant ,efficient  easily scalable.
 *
 * @author Ahmad Mahagna
 */
public class DB {

    //TODO adding interface for multi implementation

    private static final Map<String, String> DB = new ConcurrentHashMap<String, String>();


    /**
     * add URL to DB
     * Key[easyURL]=value[originalURL]
     *
     * @param url        string represent url
     * @param shortenUrl easy url
     * @throws DuplicateShortenUrl
     */
    public static synchronized void addUrl(String url, String shortenUrl) throws DuplicateShortenUrl {

        boolean isExist = DB.containsKey(shortenUrl);
        if (isExist) {
            throw new DuplicateShortenUrl("duplicate shorten url found [ " + shortenUrl + "] for two urls [" + url + "] and [" + DB.get(shortenUrl) + "]");
        }
        DB.put(shortenUrl, url);

    }

    /**
     * fetch original URL from DB
     *
     * @param shortenUrl easy url
     * @return original url otherwise return null
     */
    public static String fetchUrl(String shortenUrl) {

        // return null if not exist
        return DB.get(shortenUrl);

    }


}
