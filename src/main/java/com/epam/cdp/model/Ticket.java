package com.epam.cdp.model;

/**
 * Created by maksym_govorischev.
 */
public interface Ticket {
    /**
     * Ticket Id. UNIQUE.
     *
     * @return Ticket Id.
     */
    long getId();

    /**
     * @param id ticket id
     */
    void setId(long id);

    /**
     * @return ticket id
     */
    long getEventId();

    /**
     * @param eventId event id
     */
    void setEventId(long eventId);

    /**
     * @return user id
     */
    long getUserId();

    /**
     * @param userId user id
     */
    void setUserId(long userId);

    /**
     * @return category
     */
    Category getCategory();

    /**
     * @param category category
     */
    void setCategory(Category category);

    /**
     * @return place
     */
    int getPlace();

    /**
     * @param place place
     */
    void setPlace(int place);

    /**
     * Ticket category
     */
    enum Category {
        STANDARD, PREMIUM, BAR
    }

}
