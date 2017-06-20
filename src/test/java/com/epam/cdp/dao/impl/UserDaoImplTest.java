package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.impl.storage.Storage;
import com.epam.cdp.model.User;
import com.epam.cdp.model.impl.UserImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Yevhenii_Tolstolutsk on 12/27/2016.
 */
public class UserDaoImplTest {
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NUM = 1;
    private static final int WRONG_PAGE_NUM = 100;
    private static final String USER_NAME = "name1";
    private static final String USER_EMAIL = "user@email.com";
    private static final String WRONG_USER_EMAIL = "wrong_user@email.com";

    private UserDaoImpl userDao = new UserDaoImpl();
    private Storage storage = new Storage();
    private User user = new UserImpl();

    /**
     * Before
     */
    @Before
    public void init() {
        userDao.setStorage(storage);

        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);

        userDao.create(user);
    }

    /**
     * Test
     */
    @Test
    public void shouldGetUserById() {
        assertNotNull(userDao.getById(user.getId()));
    }

    /**
     * Test
     */
    @Test
    public void shouldGetUserByEmail() {
        assertNotNull(userDao.getByEmail(USER_EMAIL));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotGetUserByEmail() {
        assertNull(userDao.getByEmail(WRONG_USER_EMAIL));
    }

    /**
     * Test
     */
    @Test
    public void shouldGetUsersByName() {
        assertTrue(userDao.getUsersByName(USER_NAME, PAGE_SIZE, PAGE_NUM).contains(user));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotGetUsersByName() {
        assertFalse(userDao.getUsersByName(USER_NAME, PAGE_SIZE, WRONG_PAGE_NUM).contains(user));
    }

    /**
     * Test
     */
    @Test
    public void shouldCreateUser() {
        User user2 = new UserImpl();
        user2.setName("name");
        user2.setEmail("new_email@email.com");
        assertNotNull(userDao.create(user2));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotCreateUser() {
        assertNull(userDao.create(user));
    }

    /**
     * Test
     */
    @Test
    public void shouldUpdateUser() {
        user.setName("another name");
        assertNotNull(userDao.update(user));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotUpdateUser() {
        long newId = user.getId() + 1;
        User user2 = new UserImpl();
        user2.setId(newId);
        user2.setName("name");
        user2.setEmail("new_email@email.com");
        assertNull(userDao.update(user2));
    }

    /**
     * Test
     */
    @Test
    public void shouldDeleteUser() {
        assertTrue(userDao.delete(user.getId()));
    }

    /**
     * Test
     */
    @Test
    public void shouldNotDeleteUser() {
        long newId = user.getId() + 1;
        assertFalse(userDao.delete(newId));
    }
}
