package com.epam.cdp.dao;

import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;

import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public interface TicketDao {
    /**
     * @param userId   user id
     * @param eventId  event id
     * @param place    place number
     * @param category event category
     * @return booked ticket
     */
    Ticket book(long userId, long eventId, int place, Ticket.Category category);

    /**
     * @param user     user object
     * @param pageSize number of tickets per page
     * @param pageNum  page number
     * @return booked tickets list
     */
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    /**
     * @param event    event object
     * @param pageSize number of tickets per page
     * @param pageNum  page number
     * @return booked tickets list
     */
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    /**
     * @param id id of ticket to be cancelled
     * @return result of cancelling, successful (true) or not (false)
     */
    boolean cancelTicket(long id);
}
