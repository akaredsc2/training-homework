package org.vitaly.week05.factoryMethod;

import java.util.Random;

/**
 * Created by vitaly on 01.03.17.
 */
public enum TetrisFigure {
    I("####"),
    J("#  " + System.lineSeparator()
            + "###"),
    L("  #" + System.lineSeparator()
            + "###"),
    O("##" + System.lineSeparator()
            + "##"),
    S(" ##" + System.lineSeparator()
            + "## "),
    T(" # " + System.lineSeparator()
            + "###"),
    Z("## " + System.lineSeparator()
            + " ##"),
    SUPER_I("########"),
    SUPER_O("###" + System.lineSeparator()
            + "# #" + System.lineSeparator()
            + "###");

    private String form;

    TetrisFigure(String form) {
        this.form = form;
    }

    public String getForm() {
        return form;
    }

    public static TetrisFigure nextFigure() {
        Random random = new Random();
        int valuesCount = TetrisFigure.values().length;
        int index = Math.abs(random.nextInt() % valuesCount);
        return TetrisFigure.values()[index];
    }
}
