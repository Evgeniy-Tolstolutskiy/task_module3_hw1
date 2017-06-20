package com.epam.cdp.service;

import com.epam.cdp.model.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public interface EventService {
    /**
     * @param id event id
     * @return event by id
     */
    Event getById(long id);

    /**
     * @param title    event title
     * @param pageSize number of events per page
     * @param pageNum  page number
     * @return list of events by title
     */
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    /**
     * @param day      event day
     * @param pageSize number of events per page
     * @param pageNum  page number
     * @return list of events for day
     */
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    /**
     * @param event event to be created
     * @return created event
     */
    Event create(Event event);

    /**
     * @param event to be updated
     * @return updated event
     */
    Event update(Event event);

    /**
     * @param id id of event to be deleted
     * @return result of deleting
     */
    boolean delete(long id);
}
