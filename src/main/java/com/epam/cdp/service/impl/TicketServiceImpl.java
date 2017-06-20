package com.epam.cdp.service.impl;

import com.epam.cdp.dao.TicketDao;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.service.TicketService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class TicketServiceImpl implements TicketService {
    private static final Logger LOGGER = Logger.getLogger(TicketServiceImpl.class);
    private TicketDao dao;

    /**
     * @param dao dao to be set
     */
    public void setDao(TicketDao dao) {
        this.dao = dao;
    }

    @Override
    public Ticket book(long userId, long eventId, int place, Ticket.Category category) {
        LOGGER.info(String.format("booking ticket: 'user id: %d, event id: %d, place: %d, category: %s'",
                userId, eventId, place, category));
        return dao.book(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        LOGGER.info(String.format("getting booked tickets by user: %s, page size: %d, page number: %d",
                user, pageSize, pageNum));
        return dao.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        LOGGER.info(String.format("getting booked tickets by event: %s, page size: %d, page number: %d",
                event, pageSize, pageNum));
        return dao.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long id) {
        LOGGER.info(String.format("cancelling ticket with id: %d", id));
        return dao.cancelTicket(id);
    }
}
