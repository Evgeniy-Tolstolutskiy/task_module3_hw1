package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.EventDao;
import com.epam.cdp.dao.impl.storage.Storage;
import com.epam.cdp.model.Event;
import com.epam.cdp.util.IdGenerator;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class EventDaoImpl implements EventDao {
    private static final Logger LOGGER = Logger.getLogger(EventDaoImpl.class);
    private static final String EVENT_PREFIX = "event:";
    private Storage storage;
    private IdGenerator idGenerator = new IdGenerator();

    /**
     * @param storage storage to be set
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Event getById(long id) {
        String key = EVENT_PREFIX + id;
        return (Event) storage.findByKey(key);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().contains(EVENT_PREFIX) && ((Event) entry.getValue()).getTitle().contains(title))
                .map(entry -> (Event) entry.getValue())
                .skip(pageSize * pageNum - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().contains(EVENT_PREFIX) && ((Event) entry.getValue()).getDate().equals(day))
                .map(entry -> (Event) entry.getValue())
                .skip(pageSize * pageNum - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Event create(Event event) {
        event.setId(idGenerator.generateEventId());
        String key = EVENT_PREFIX + event.getId();
        storage.getStorage().put(key, event);
        return event;
    }

    @Override
    public Event update(Event event) {
        String key = EVENT_PREFIX + event.getId();
        if (storage.getStorage().containsKey(key)) {
            storage.getStorage().put(key, event);
            return event;
        }
        LOGGER.info(String.format("cannot update event: %s", event));
        return null;
    }

    @Override
    public boolean delete(long id) {
        String key = EVENT_PREFIX + id;
        if (storage.getStorage().containsKey(key)) {
            storage.getStorage().remove(key);
            return true;
        }
        LOGGER.info(String.format("cannot delete event with id: %d", id));
        return false;
    }
}
