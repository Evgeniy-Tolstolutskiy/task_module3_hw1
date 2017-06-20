package com.epam.cdp.dao;

import com.epam.cdp.model.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public interface EventDao {
    /**
     * @param id event id
     * @return event by id
     */
    Event getById(long id);

    /**
     * @param title    title of event
     * @param pageSize number of events per page
     * @param pageNum  page number
     * @return event list
     */
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    /**
     * @param day      day of event
     * @param pageSize number of events per page
     * @param pageNum  page number
     * @return event list
     */
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    /**
     * @param event event to be created
     * @return created event
     */
    Event create(Event event);

    /**
     * @param event event to be updated
     * @return updated event, or null, if event is not exist
     */
    Event update(Event event);

    /**
     * @param id id of event to be deleted
     * @return result of deleting, successful (true) or not (false)
     */
    boolean delete(long id);
}
