package org.jesperancinha.java11.crums.crum17;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jesperancinha.console.consolerizer.ConColor.BLUE;
import static org.jesperancinha.console.consolerizer.ConColor.BRIGHT_MAGENTA;
import static org.jesperancinha.console.consolerizer.ConColor.MAGENTA;
import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGenericLn;

public class Crum17 {
    public static void main(String[] args) {
        BLUE.printGenericTitleLn("Crum 17 - Terminating functions in lambda sequences");

        final Stream<String> names = Stream.of("Isla Fisher", "Amy Adams", "Jessica Chastain", "Bryce Dallas");

        final Stream<Character> characterStream = names.map(name -> {
            BRIGHT_MAGENTA.printGenericLn("This is an indication that mapping of name %s is following next", name);
            return name.charAt(0);
        });

        MAGENTA.printGenericLn("Nothing has happened yet to the stream.");
        MAGENTA.printGenericLn("Now we call the terminating function.");

        final List<Character> collect = characterStream.collect(Collectors.toList());

        MAGENTA.printGenericLn("And this is the character list -> %s", collect);

        printGreenGenericLn("Lambda functions are only called once the terminating function is called");
        printGreenGenericLn(
            "Mapping is valid as long as uses an element of the stream  as parameter and then returns another, which can be of the desired type");

        printGreenGenericLn("Mapping takes an implementation of a Function interface, which we implement on the fly");
    }
}
