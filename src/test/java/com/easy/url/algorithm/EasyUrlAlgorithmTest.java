package com.easy.url.algorithm;

import com.easy.url.DB;
import com.easy.url.Exception.DuplicateShortenUrl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Algorithm
 *
 * @author Ahmad Mahagna
 */


public class EasyUrlAlgorithmTest {

    private String BASIC_URL = "https://localhost:123/welcome";

    //TODO NEED MORE TESTS !
    @Test
    public void testAlgorithm() throws DuplicateShortenUrl {

        String easyUrl = EasyUrlAlgorithm.getEasyUrl(Util.getRandomLong());
        DB.addUrl(BASIC_URL, easyUrl);
        Assert.assertEquals(BASIC_URL, DB.fetchUrl(easyUrl));

    }

   @Test
    public void testSizeOfEasyUrl() {

        String easyUrl = EasyUrlAlgorithm.getEasyUrl(Util.getRandomLong());
        Assert.assertTrue("size of easy url too long",easyUrl.length() < 8);


    }

    //TODO add duplicate test
    //TODO add invalid test
}
