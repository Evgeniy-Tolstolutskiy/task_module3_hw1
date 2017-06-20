package com.epam.cdp.dao.impl.storage;

import com.epam.cdp.dao.impl.storageloader.StorageLoader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yevhenii_Tolstolutsk on 12/19/2016.
 */
public class Storage {
    private static final Logger LOGGER = Logger.getLogger(Storage.class);
    private Map<String, Object> storage = new HashMap<>();
    private StorageLoader storageLoader;

    /**
     * @param storageLoader storage loader
     */
    public void setStorageLoader(StorageLoader storageLoader) {
        this.storageLoader = storageLoader;
    }

    /**
     * @param key key of searched object
     * @return object from storage
     */
    public Object findByKey(String key) {
        return storage.get(key);
    }

    /**
     * @return storage itself
     */
    public Map<String, Object> getStorage() {
        return storage;
    }

    /**
     * Allows to fill storage from external source.
     *
     * @param pathToStorage path to external source
     */
    public void loadStorageFromExternalFile(String pathToStorage) {
        try {
            if (storageLoader == null) {
                LOGGER.warn("storage loader was null, storage was not loaded");
                return;
            }
            storage = storageLoader.loadStorage(pathToStorage);
        } catch (IOException e) {
            LOGGER.error("cannot read file, storage was not loaded");
        }
    }
}
