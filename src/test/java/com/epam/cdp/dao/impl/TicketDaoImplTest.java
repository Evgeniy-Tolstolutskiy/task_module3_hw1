package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.impl.storage.Storage;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.model.impl.EventImpl;
import com.epam.cdp.model.impl.TicketImpl;
import com.epam.cdp.model.impl.UserImpl;
import com.epam.cdp.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhenii_Tolstolutsk on 12/27/2016.
 */
public class TicketDaoImplTest {
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NUM = 1;
    private static final int WRONG_PAGE_NUM = 100;
    private static final long DEC_26_2016 = 1482703200000L;
    private static final int TICKET_PLACE = 1;

    private TicketDaoImpl ticketDao = new TicketDaoImpl();
    private Storage storage = new Storage();
    private Ticket ticket = new TicketImpl();
    private User user = new UserImpl();
    private Event event = new EventImpl();
    private IdGenerator idGenerator = new IdGenerator();
    private int ticketId = idGenerator.generateTicketId();
    private int userId = idGenerator.generateUserId();
    private int eventId = idGenerator.generateEventId();

    /**
     * Before
     */
    @Before
    public void init() {
        ticketDao.setStorage(storage);

        user.setId(userId);
        user.setName("name1");
        user.setEmail("user@email.com");

        event.setId(eventId);
        event.setDate(new Date(DEC_26_2016));
        event.setTitle("some event");

        ticket.setId(ticketId);
        ticket.setUserId(userId);
        ticket.setCategory(Ticket.Category.PREMIUM);
        ticket.setPlace(TICKET_PLACE);
        ticket.setEventId(eventId);

        storage.getStorage().put("user:" + userId, user);
        storage.getStorage().put("event:" + eventId, event);
        storage.getStorage().put("ticket:" + ticketId, ticket);
    }

    /**
     * Test
     */
    @Test
    public void shouldBookTicket() {
        Ticket ticket = ticketDao.book(userId, eventId, TICKET_PLACE + 1, Ticket.Category.PREMIUM);
        assertTrue(storage.getStorage().containsValue(ticket));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotBookTicket() {
        Ticket ticket = ticketDao.book(userId, eventId, TICKET_PLACE, Ticket.Category.PREMIUM);
        assertNull(ticket);
    }

    /**
     * Test
     */
    @Test
    public void shouldGetBookedTickets() {
        assertTrue(ticketDao.getBookedTickets(user, PAGE_SIZE, PAGE_NUM).contains(ticket));
        assertTrue(ticketDao.getBookedTickets(event, PAGE_SIZE, PAGE_NUM).contains(ticket));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotGetBookedTickets() {
        assertFalse(ticketDao.getBookedTickets(user, PAGE_SIZE, WRONG_PAGE_NUM).contains(ticket));
        assertFalse(ticketDao.getBookedTickets(event, PAGE_SIZE, WRONG_PAGE_NUM).contains(ticket));
    }

    /**
     * Test
     */
    @Test
    public void shouldCancelTicket() {
        assertTrue(ticketDao.cancelTicket(ticketId));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotCancelTicket() {
        int newId = ticketId + 1;
        assertFalse(ticketDao.cancelTicket(newId));
    }
}
