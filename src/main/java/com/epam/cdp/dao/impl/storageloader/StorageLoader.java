package com.epam.cdp.dao.impl.storageloader;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Yevhenii_Tolstolutsk on 1/3/2017.
 */
public interface StorageLoader {
    /**
     * Loads information from external file.
     *
     * @param pathToStorage path to storage
     * @return filled storage
     * @throws IOException if cannot read file
     */
    Map<String, Object> loadStorage(String pathToStorage) throws IOException;
}
