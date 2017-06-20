package com.epam.cdp.facade.impl;

import com.epam.cdp.facade.BookingFacade;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.service.EventService;
import com.epam.cdp.service.TicketService;
import com.epam.cdp.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class BookingFacadeImpl implements BookingFacade {
    private EventService eventService;
    private TicketService ticketService;
    private UserService userService;

    /**
     * @param eventService  event service
     * @param ticketService ticket service
     * @param userService   user service
     */
    public BookingFacadeImpl(EventService eventService, TicketService ticketService, UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @Override
    public Event getEventById(long id) {
        return eventService.getById(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.create(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.update(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.delete(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userService.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.update(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.delete(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketService.book(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
