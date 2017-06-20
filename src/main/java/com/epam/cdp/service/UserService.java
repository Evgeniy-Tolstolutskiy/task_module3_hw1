package com.epam.cdp.service;

import com.epam.cdp.model.User;

import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public interface UserService {
    /**
     * @param id user id
     * @return user by id
     */
    User getById(long id);

    /**
     * @param email user email
     * @return user by email
     */
    User getByEmail(String email);

    /**
     * @param name     user name
     * @param pageSize number of users per page
     * @param pageNum  page number
     * @return user list
     */
    List<User> getUsersByName(String name, int pageSize, int pageNum);

    /**
     * @param user user to be created
     * @return created user
     */
    User create(User user);

    /**
     * @param user user to be updated
     * @return updated user
     */
    User update(User user);

    /**
     * @param id id of user to be deleted
     * @return result of deleting
     */
    boolean delete(long id);
}
