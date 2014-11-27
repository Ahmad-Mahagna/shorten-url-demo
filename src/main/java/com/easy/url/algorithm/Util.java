package com.easy.url.algorithm;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Util class represent static methods used for algorithm 
 *
 * @author Ahmad Mahagna
 */
public class Util {

    private static final AtomicLong INC = new AtomicLong();
    private static final long xFF = 0xFF;

    /**
     *  algorithm (prototype)  get current time in long + counter.
     *  @return long represent random long.
     */
    public static long getRandomLong() {
        //TODO improve the random method currently its prototype    CRC32 / hashCode / counter / instanceId / UUID / USID ...
        return (System.currentTimeMillis() + (INC.getAndIncrement() & xFF));

    }


}
