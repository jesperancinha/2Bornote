package org.jesperancinha.java11.crums.crum4;

import org.jesperancinha.console.consolerizer.Consolerizer;

import java.util.Arrays;

import static org.jesperancinha.console.consolerizer.ConColor.BLUE;
import static org.jesperancinha.console.consolerizer.ConColor.MAGENTA;
import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGenericLn;

public class Crum4 {

    @Boat(value = "Armonense", type = BoatType.PASSENGER)
    @Boat(value = "Ferreirinha", type = BoatType.FISHING)
    private class ArmonaBoat {

    }

    @Boats({ @Boat(value = "Armonense", type = BoatType.PASSENGER),
        @Boat(value = "Ferreirinha", type = BoatType.FISHING) })
    private class TourBoat {

    }

    public static void main(String[] args) {
        BLUE.printGenericTitleLn("Crum 4 - Repeatable annotations");

        final Crum4 crum4 = new Crum4();

        final ArmonaBoat armonaBoat = crum4.new ArmonaBoat();

        final TourBoat tourBoat = crum4.new TourBoat();

        MAGENTA.printGenericLn("For the %s boat we have:", armonaBoat.getClass());
        Arrays.stream(armonaBoat.getClass()
            .getAnnotations())
            .forEach(Consolerizer::printBrightCyanGenericLn);
        MAGENTA.printGenericLn("For the %s boat we have:", tourBoat.getClass());
        Arrays.stream(tourBoat.getClass()
            .getAnnotations())
            .forEach(Consolerizer::printBrightCyanGenericLn);

        printGreenGenericLn(
            "We need to be aware that although we made our annotation Boat repeatable, it is not dependent on Boats");
        printGreenGenericLn(
            "The code declaration of Boats allow us to use the Boat annotation independently and repeatedly");
    }
}
