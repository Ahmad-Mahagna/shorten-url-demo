package com.easy.url.algorithm;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by amahagna on 11/24/14.
 *
 * @author Ahmad Mahagna
 */
public class Util {

    private static final AtomicLong INC = new AtomicLong();
    private static final long xFF = 0xFF;


    public static long getRandomLong() {
        return (System.currentTimeMillis() + (INC.getAndIncrement() & xFF));

    }


}
