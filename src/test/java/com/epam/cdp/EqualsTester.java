package com.epam.cdp;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.test.Tester;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

import static nl.jqno.equalsverifier.Warning.INHERITED_DIRECTLY_FROM_OBJECT;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;

/**
 * Created by Yevhenii Tolstolutskyi on 12/29/2016
 */
public class EqualsTester implements Tester {
    private static final Logger LOGGER = Logger.getLogger(EqualsTester.class);

    /**
     * Test
     */
    @Override
    public void run(PojoClass pojoClass) {
        Class<?> pojoClazz = pojoClass.getClazz();

        invokeToStringMethod(pojoClazz);

        EqualsVerifier.forClass(pojoClazz)
                .usingGetClass()
                .suppress(NONFINAL_FIELDS, INHERITED_DIRECTLY_FROM_OBJECT)
                .verify();
    }

    private void invokeToStringMethod(Class<?> pojoClazz) {
        try {
            pojoClazz.getMethod("toString").invoke(pojoClazz.newInstance());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            LOGGER.info("reflection error", e);
        }
    }
}
