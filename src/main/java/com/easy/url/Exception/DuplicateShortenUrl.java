package com.easy.url.Exception;

/**
 * Internal Exception DuplicateShortenUrl Exception  
 * USE CASE : Two entities at least in db have same id , key=[EasyURL] exist at least twice .
 * 
 * Created by amahagna on 11/24/14.
 * 
 */
public class DuplicateShortenUrl extends Exception {

  /**
   *          Constructor 
   * @see Exception 
   */
    public DuplicateShortenUrl(String message) {
        super(message);
    }

}
