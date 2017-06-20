package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.UserDao;
import com.epam.cdp.dao.impl.storage.Storage;
import com.epam.cdp.model.User;
import com.epam.cdp.util.IdGenerator;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private static final String USER_PREFIX = "user:";
    private Storage storage;
    private IdGenerator idGenerator = new IdGenerator();

    /**
     * @param storage storage to be set
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public User getById(long id) {
        String key = USER_PREFIX + id;
        return (User) storage.findByKey(key);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> userOptional = storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().contains(USER_PREFIX) && ((User) entry.getValue()).getEmail().equals(email))
                .map(entry -> (User) entry.getValue())
                .findFirst();
        return userOptional.orElse(null);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return storage.getStorage().entrySet().stream()
                .filter(entry -> entry.getKey().contains(USER_PREFIX) && ((User) entry.getValue()).getName().contains(name))
                .map(entry -> (User) entry.getValue())
                .skip(pageSize * pageNum - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public User create(User user) {
        user.setId(idGenerator.generateUserId());
        String key = USER_PREFIX + user.getId();
        if (getByEmail(user.getEmail()) == null) {
            storage.getStorage().put(key, user);
            return user;
        }
        LOGGER.info(String.format("cannot create user: %s", user));
        return null;
    }

    @Override
    public User update(User user) {
        String key = USER_PREFIX + user.getId();
        if (storage.getStorage().containsKey(key)) {
            storage.getStorage().put(key, user);
            return user;
        }
        User userByEmail = getByEmail(user.getEmail());
        if (userByEmail != null) {
            storage.getStorage().put(key, user);
            String keyForDeleting = USER_PREFIX + userByEmail.getId();
            storage.getStorage().remove(keyForDeleting);
            return user;
        }
        LOGGER.info(String.format("cannot update user: %s", user));
        return null;
    }

    @Override
    public boolean delete(long id) {
        String key = USER_PREFIX + id;
        if (storage.getStorage().containsKey(key)) {
            storage.getStorage().remove(key);
            return true;
        }
        LOGGER.info(String.format("cannot delete user with id: %d", id));
        return false;
    }
}
