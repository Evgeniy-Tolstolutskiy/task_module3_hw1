package com.epam.cdp.service.impl;

import com.epam.cdp.dao.UserDao;
import com.epam.cdp.model.User;
import com.epam.cdp.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDao dao;

    /**
     * @param dao dao to be set
     */
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User getById(long id) {
        LOGGER.info(String.format("getting user by id: %d", id));
        return dao.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.info(String.format("getting user by email: %s", email));
        return dao.getByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        LOGGER.info(String.format("getting users by name: %s, page size: %d, page number: %d",
                name, pageSize, pageNum));
        return dao.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User create(User user) {
        LOGGER.info(String.format("creating user: %s", user));
        return dao.create(user);
    }

    @Override
    public User update(User user) {
        LOGGER.info(String.format("updating user: %s", user));
        return dao.update(user);
    }

    @Override
    public boolean delete(long id) {
        LOGGER.info(String.format("deleting user with id: %d", id));
        return dao.delete(id);
    }
}
