package com.epam.cdp.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yevhenii_Tolstolutsk on 12/20/2016.
 */
public class IdGenerator {
    private static AtomicInteger eventId = new AtomicInteger();
    private static AtomicInteger ticketId = new AtomicInteger();
    private static AtomicInteger userId = new AtomicInteger();

    /**
     * @return generated event id
     */
    public int generateEventId() {
        return eventId.incrementAndGet();
    }

    /**
     * @return generated ticket id
     */
    public int generateTicketId() {
        return ticketId.incrementAndGet();
    }

    /**
     * @return generated user id
     */
    public int generateUserId() {
        return userId.incrementAndGet();
    }
}
