package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.TicketDao;
import com.epam.cdp.dao.impl.storage.Storage;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.model.impl.TicketImpl;
import com.epam.cdp.util.IdGenerator;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);
    private static final String TICKET_PREFIX = "ticket:";
    private static final String EVENT_PREFIX = "event:";
    private static final String USER_PREFIX = "user:";
    private Storage storage;
    private IdGenerator ticketIdGenerator = new IdGenerator();

    /**
     * @param storage storage to be set
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Ticket book(long userId, long eventId, int place, Ticket.Category category) {
        if (getBookedTickets(getEventById(eventId), 1, 1).stream()
                .anyMatch(ticket -> ticket.getPlace() == place)) {
            LOGGER.info(String.format("cannot book ticket on event %d with place: %d, because this place is already taken", eventId, place));
            return null;
        }

        Ticket ticket = new TicketImpl();
        ticket.setId(ticketIdGenerator.generateTicketId());
        ticket.setUserId(userId);
        ticket.setEventId(eventId);
        ticket.setPlace(place);
        ticket.setCategory(category);

        String key = TICKET_PREFIX + ticket.getId();
        storage.getStorage().put(key, ticket);

        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().contains(TICKET_PREFIX) && ((Ticket) entry.getValue()).getUserId() == user.getId())
                .map(entry -> (Ticket) entry.getValue())
                .skip(pageSize * pageNum - pageSize)
                .limit(pageSize)
                .sorted(Comparator.comparing(ticket -> getEventById(((Ticket) ticket).getEventId()).getDate()).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().contains(TICKET_PREFIX) && ((Ticket) entry.getValue()).getEventId() == event.getId())
                .map(entry -> (Ticket) entry.getValue())
                .skip(pageSize * pageNum - pageSize)
                .limit(pageSize)
                .sorted(Comparator.comparing(ticket -> getUserById(ticket.getUserId()).getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long id) {
        String key = TICKET_PREFIX + id;
        if (storage.getStorage().containsKey(key)) {
            storage.getStorage().remove(key);
            return true;
        }
        LOGGER.info(String.format("cannot cancel ticket with id: %d", id));
        return false;
    }

    private Event getEventById(long id) {
        return storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().equals(EVENT_PREFIX + id))
                .findFirst()
                .map(entry -> (Event) entry.getValue())
                .get();
    }

    private User getUserById(long id) {
        return storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().equals(USER_PREFIX + id))
                .findFirst()
                .map(entry -> (User) entry.getValue())
                .get();
    }
}
