package org.vitaly.homework04.composite;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-18.
 */
public class NumberTest {
    @Test
    public void computingNumberReturnsItsValue() throws Exception {
        int value = 10;
        Component number = new Number(value);

        long computationResult = number.compute();

        assertThat(computationResult, equalTo((long) value));
    }
}