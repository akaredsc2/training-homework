package org.vitaly.homework04.factoryMethod;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-04.
 */
public class TetrisFigureTest {
    @Test
    public void nextFigureIsNotNullAndIsInTetrisFigureEnum() throws Exception {
        TetrisFigure tetrisFigure = TetrisFigure.nextFigure();

        String actualForm = tetrisFigure.getForm();

        TetrisFigure[] values = TetrisFigure.values();
        String[] expectedForms = new String[values.length];
        for (int i = 0; i < expectedForms.length; i++) {
            expectedForms[i] = values[i].getForm();
        }
        assertThat(actualForm, allOf(
                notNullValue(),
                isOneOf(expectedForms)));
    }
}