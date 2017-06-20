package com.epam.cdp.model.impl;

import com.epam.cdp.model.Event;

import java.util.Date;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class EventImpl implements Event {
    private long id;
    private String title;
    private Date date;

    /**
     *
     */
    public EventImpl() {
    }

    /**
     * @param id    id
     * @param title title
     * @param date  date
     */
    public EventImpl(long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date == null ? null : new Date(date.getTime());
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    @Override
    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventImpl event = (EventImpl) o;

        if (id != event.id) {
            return false;
        }
        if (title != null ? !title.equals(event.title) : event.title != null) {
            return false;
        }
        return date != null ? date.equals(event.date) : event.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventImpl{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", date=" + date
                + '}';
    }
}
