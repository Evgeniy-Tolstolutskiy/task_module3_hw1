package com.epam.cdp.model;

import java.util.Date;

/**
 * Created by maksym_govorischev.
 */
public interface Event {
    /**
     * Event id. UNIQUE.
     *
     * @return Event Id
     */
    long getId();

    /**
     * @param id event id
     */
    void setId(long id);

    /**
     * @return event title
     */
    String getTitle();

    /**
     * @param title event title
     */
    void setTitle(String title);

    /**
     * @return event date
     */
    Date getDate();

    /**
     * @param date event date
     */
    void setDate(Date date);
}
