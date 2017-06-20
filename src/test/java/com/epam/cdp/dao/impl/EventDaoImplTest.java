package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.impl.storage.Storage;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.impl.EventImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Yevhenii_Tolstolutsk on 12/27/2016.
 */
public class EventDaoImplTest {
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NUM = 1;
    private static final int WRONG_PAGE_NUM = 100;
    private static final String EVENT_TITLE = "event title";
    private static final long DEC_26_2016 = 1482703200000L;

    private EventDaoImpl eventDao = new EventDaoImpl();
    private Storage storage = new Storage();
    private Event event = new EventImpl();

    /**
     * Before
     */
    @Before
    public void init() {
        eventDao.setStorage(storage);

        event.setTitle(EVENT_TITLE);
        event.setDate(new Date(DEC_26_2016));

        eventDao.create(event);
    }

    /**
     * Test
     */
    @Test
    public void shouldGetEventById() {
        assertNotNull(eventDao.getById(event.getId()));
    }

    /**
     * Test
     */
    @Test
    public void shouldGetEventsByTitle() {
        assertTrue(eventDao.getEventsByTitle(EVENT_TITLE, PAGE_SIZE, PAGE_NUM).contains(event));
    }

    /**
     * Test
     */
    @Test
    public void shouldGetEventsForDay() {
        assertTrue(eventDao.getEventsForDay(new Date(DEC_26_2016), PAGE_SIZE, PAGE_NUM).contains(event));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotGetEvents() {
        assertTrue(eventDao.getEventsByTitle(EVENT_TITLE, PAGE_SIZE, WRONG_PAGE_NUM).isEmpty());
        assertTrue(eventDao.getEventsForDay(new Date(DEC_26_2016), PAGE_SIZE, WRONG_PAGE_NUM).isEmpty());
    }

    /**
     * Test
     */
    @Test
    public void shouldCreateEvent() {
        long newId = event.getId() + 1;
        event.setId(newId);
        assertNotNull(eventDao.create(event));
    }

    /**
     * Test
     */
    @Test
    public void shouldUpdateEvent() {
        event.setDate(new Date(System.currentTimeMillis()));
        assertNotNull(eventDao.update(event));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotUpdateEvent() {
        long newId = event.getId() + 1;
        event.setId(newId);
        assertNull(eventDao.update(event));
    }

    /**
     * Test
     */
    @Test
    public void shouldDeleteEvent() {
        assertTrue(eventDao.delete(event.getId()));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotDeleteEvent() {
        long newId = event.getId() + 1;
        assertFalse(eventDao.delete(newId));
    }
}
