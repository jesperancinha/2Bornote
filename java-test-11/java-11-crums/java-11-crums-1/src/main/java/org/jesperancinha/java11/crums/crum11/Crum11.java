package org.jesperancinha.java11.crums.crum11;

import static org.jesperancinha.console.consolerizer.ConColor.BLUE;
import static org.jesperancinha.console.consolerizer.ConColor.BRIGHT_MAGENTA;
import static org.jesperancinha.console.consolerizer.Consolerizer.printNewLine;

public class Crum11 {
    public static void main(String[] args) {
        BLUE.printGenericTitleLn("Crum 11 - All english letters fit a byte");

        for (byte i = 'A'; i <= 122; i++) {
            BRIGHT_MAGENTA.printGeneric(i);
            BRIGHT_MAGENTA.printGeneric((char)i);
        }

        printNewLine();

    }
}
