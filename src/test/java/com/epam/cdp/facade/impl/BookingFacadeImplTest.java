package com.epam.cdp.facade.impl;

import com.epam.cdp.facade.BookingFacade;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.model.impl.EventImpl;
import com.epam.cdp.model.impl.UserImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:config.xml")
public class BookingFacadeImplTest {
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NUM = 1;
    private static final int EVENT_PLACE = 1;
    private static final long DEC_26_2016 = 1482703200000L;
    private static final String USER_NAME = "name2";
    private static final String USER_EMAIL = "user2@email.com";
    private static final String EVENT_TITLE = "event title";

    @Autowired
    private BookingFacade facade;

    private User user = new UserImpl();
    private Event event = new EventImpl();

    /**
     * Before
     */
    @Before
    public void init() {
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);

        event.setTitle(EVENT_TITLE);
        event.setDate(new Date(DEC_26_2016));
    }

    /**
     * Test
     */
    @Test
    public void realisticScenario() {
        if (facade.createUser(user) == null) {
            facade.updateUser(user);
        }

        assertEquals(user, facade.getUserById(user.getId()));
        assertEquals(user, facade.getUserByEmail(USER_EMAIL));
        assertTrue(facade.getUsersByName(USER_NAME, PAGE_SIZE, PAGE_NUM).contains(user));

        if (facade.createEvent(event) == null) {
            facade.updateEvent(event);
        }

        assertEquals(event, facade.getEventById(event.getId()));
        assertTrue(facade.getEventsByTitle(EVENT_TITLE, PAGE_SIZE, PAGE_NUM).contains(event));
        assertTrue(facade.getEventsForDay(new Date(DEC_26_2016), PAGE_SIZE, PAGE_NUM).contains(event));

        Ticket bookedTicket = facade.bookTicket(user.getId(), event.getId(), EVENT_PLACE, Ticket.Category.PREMIUM);
        assertTrue(facade.getBookedTickets(user, PAGE_SIZE, PAGE_NUM).contains(bookedTicket));

        facade.cancelTicket(bookedTicket.getId());
        assertFalse(facade.getBookedTickets(user, PAGE_SIZE, PAGE_NUM).contains(bookedTicket));
    }

    /**
     * Test
     */
    @Test
    public void shouldSelectTicketsSortedByEventDate() {
        if (facade.createUser(user) == null) {
            facade.updateUser(user);
        }

        if (facade.createEvent(event) == null) {
            facade.updateEvent(event);
        }

        Event event2 = new EventImpl();
        event2.setDate(new Date(System.currentTimeMillis()));
        event2.setTitle("event title");

        if (facade.createEvent(event2) == null) {
            facade.updateEvent(event2);
        }

        Ticket ticket1 = facade.bookTicket(user.getId(), event.getId(), EVENT_PLACE, Ticket.Category.PREMIUM);
        Ticket ticket2 = facade.bookTicket(user.getId(), event2.getId(), EVENT_PLACE, Ticket.Category.PREMIUM);

        List<Ticket> bookedTickets = facade.getBookedTickets(user, PAGE_SIZE, PAGE_NUM);
        assertEquals(ticket2, bookedTickets.get(0));
        assertEquals(ticket1, bookedTickets.get(1));
    }

    /**
     * Test
     */
    @Test
    public void shouldSelectTicketsSortedByUserEmail() {
        if (facade.createUser(user) == null) {
            facade.updateUser(user);
        }

        User user2 = new UserImpl();
        user2.setName("name3");
        user2.setEmail("z_name3@email.com");

        if (facade.createUser(user2) == null) {
            facade.updateUser(user2);
        }

        if (facade.createEvent(event) == null) {
            facade.updateEvent(event);
        }

        Ticket ticket1 = facade.bookTicket(user.getId(), event.getId(), EVENT_PLACE, Ticket.Category.PREMIUM);
        Ticket ticket2 = facade.bookTicket(user2.getId(), event.getId(), EVENT_PLACE + 1, Ticket.Category.PREMIUM);

        List<Ticket> bookedTickets = facade.getBookedTickets(event, PAGE_SIZE, PAGE_NUM);
        assertEquals(ticket1, bookedTickets.get(0));
        assertEquals(ticket2, bookedTickets.get(1));
    }
}
