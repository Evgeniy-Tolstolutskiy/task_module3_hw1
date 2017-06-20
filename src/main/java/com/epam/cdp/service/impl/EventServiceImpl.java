package com.epam.cdp.service.impl;

import com.epam.cdp.dao.EventDao;
import com.epam.cdp.model.Event;
import com.epam.cdp.service.EventService;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = Logger.getLogger(EventServiceImpl.class);
    private EventDao dao;

    /**
     * @param dao dao to be set
     */
    public void setDao(EventDao dao) {
        this.dao = dao;
    }

    @Override
    public Event getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        LOGGER.info(String.format("getting events by title '%s', page size: %d, page number: %d",
                title, pageSize, pageNum));
        return dao.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        LOGGER.info(String.format("getting events for day '%s', page size: %d, page number: %d",
                day, pageSize, pageNum));
        return dao.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event create(Event event) {
        LOGGER.info(String.format("creating event: %s", event));
        return dao.create(event);
    }

    @Override
    public Event update(Event event) {
        LOGGER.info(String.format("updating event: %s", event));
        return dao.update(event);
    }

    @Override
    public boolean delete(long id) {
        LOGGER.info(String.format("deleting event with id: %d", id));
        return dao.delete(id);
    }
}
