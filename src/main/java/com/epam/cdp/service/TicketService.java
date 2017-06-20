package com.epam.cdp.service;

import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;

import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public interface TicketService {
    /**
     * @param userId   user id
     * @param eventId  event id
     * @param place    place
     * @param category category
     * @return booked ticket
     */
    Ticket book(long userId, long eventId, int place, Ticket.Category category);

    /**
     * @param user     user object
     * @param pageSize number of tickets per page
     * @param pageNum  page number
     * @return ticket list
     */
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    /**
     * @param event    event object
     * @param pageSize number of tickets per page
     * @param pageNum  page number
     * @return ticket list
     */
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    /**
     * @param id id of ticket to be deleted
     * @return result of deleting
     */
    boolean cancelTicket(long id);
}
