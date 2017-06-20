package com.epam.cdp.model;

/**
 * Created by maksym_govorischev on 14/03/14.
 */
public interface User {
    /**
     * User Id. UNIQUE.
     *
     * @return User Id.
     */
    long getId();

    /**
     * @param id user id
     */
    void setId(long id);

    /**
     * @return user name
     */
    String getName();

    /**
     * @param name user name
     */
    void setName(String name);

    /**
     * User email. UNIQUE.
     *
     * @return User email.
     */
    String getEmail();

    /**
     * @param email user email
     */
    void setEmail(String email);
}
