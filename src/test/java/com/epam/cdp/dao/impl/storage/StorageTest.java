package com.epam.cdp.dao.impl.storage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yevhenii_Tolstolutsk on 1/4/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:config.xml")
public class StorageTest {
    @Autowired
    private Storage storage;
    @Value("${path-to-external-file}")
    private String pathToStorage;

    /**
     * Test
     */
    @Test
    public void testStorageLoader() {
        storage.loadStorageFromExternalFile(pathToStorage);
        assertEquals(3, storage.getStorage().size());
    }
}
