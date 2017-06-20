package com.epam.cdp;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterChain;
import com.openpojo.reflection.filters.FilterClassName;
import com.openpojo.reflection.filters.FilterNonConcrete;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoNestedClassRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yevhenii Tolstolutskyi on 12/29/2016
 */
public class PojoTest {
    private static final String[] POJO_PACKAGES = {
            "com.epam.cdp.model.impl"
    };

    private Set<PojoClass> pojoClasses = new HashSet<>();
    private Validator pojoValidator;

    /**
     * Before
     */
    @Before
    public void setUp() {
        preparePojoClasses();
        preparePojoValidator();
    }

    private void preparePojoValidator() {
        pojoValidator = ValidatorBuilder.create()
                .with(new NoPublicFieldsRule())
                .with(new NoStaticExceptFinalRule())
                .with(new NoFieldShadowingRule())
                .with(new GetterMustExistRule())
                .with(new NoNestedClassRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .with(new EqualsTester())
                .build();
    }

    private void preparePojoClasses() {
        for (String pojoPackage : POJO_PACKAGES) {
            pojoClasses.addAll(PojoClassFactory.getPojoClasses(pojoPackage,
                    new FilterChain(new FilterClassName("^((?!Test$).)*$"), new FilterNonConcrete())));
        }
    }

    /**
     * Test
     */
    @Test
    public void testPojos() {
        for (PojoClass pojoClass : pojoClasses) {
            pojoValidator.validate(pojoClass);
        }
    }
}