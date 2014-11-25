package com.easy.url.Exception;

/**
 * Internal Exception DuplicateShortenUrl Exception
 * USE CASE : Two entities at least in db have same id , key=[EasyURL] exist at least twice .
 *
 * @author Ahmad Mahagna
 */
public class DuplicateShortenUrl extends Exception {

    /**
     * @see java.lang.Exception constructor
     * @param message error message
     */
    public DuplicateShortenUrl(String message) {
        super(message);
    }

}
