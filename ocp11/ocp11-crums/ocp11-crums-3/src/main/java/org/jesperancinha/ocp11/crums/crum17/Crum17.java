package org.jesperancinha.ocp11.crums.crum17;

import static org.jesperancinha.console.consolerizer.Consolerizer.printBlueGenericTitleLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printMagentaGenericLn;

public class Crum17 {

    public interface Green {

    }

    public class Leave implements Green {

    }

    public class Bush extends Leave {

    }

    public class BlackCurrant extends Bush {

        @Override
        public String toString() {
            return "Oh my god! It's a black currant!";
        }
    }

    public static void main(String[] args) {
        printBlueGenericTitleLn("Crum 17 - Double Casting and how it can be done");

        final Crum17 crum17 = new Crum17();

        final Green blackCurrant = (Green) (Bush) crum17.new BlackCurrant();

        printMagentaGenericLn("This cast does work and we get a Green -> %s" , blackCurrant);

        printGreenGenericLn("Although mostly unnecessary, a double casting is perfectly acceptable in Java");
    }
}