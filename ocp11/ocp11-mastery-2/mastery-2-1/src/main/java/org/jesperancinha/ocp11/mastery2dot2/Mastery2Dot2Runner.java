package org.jesperancinha.ocp11.mastery2dot2;

import org.jesperancinha.console.consolerizer.Consolerizer;
import org.jesperancinha.ocp11.mastery2dot2.animals.Animal;
import org.jesperancinha.ocp11.mastery2dot2.animals.Bird;
import org.jesperancinha.ocp11.mastery2dot2.animals.BirdCharacter;
import org.jesperancinha.ocp11.mastery2dot2.animals.CatCharacter;
import org.jesperancinha.ocp11.mastery2dot2.animals.DuckCharacter;
import org.jesperancinha.ocp11.mastery2dot2.animals.Feline;
import org.jesperancinha.ocp11.mastery2dot2.animals.Wolf;
import org.jesperancinha.ocp11.mastery2dot2.animals.WolfCharacter;
import org.jesperancinha.ocp11.mastery2dot2.ost.manager.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jesperancinha.console.consolerizer.Consolerizer.printBlueGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGeneric;
import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printRainbowLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printYellowGeneric;
import static org.jesperancinha.console.consolerizer.Consolerizer.printYellowGenericLn;

public class Mastery2Dot2Runner {
    public static void main(String[] args) {
        Consolerizer.typingWaitGlobal = 0;
        // 1. Interface Inheritance
        printRainbowLn("==");
        printYellowGeneric("### Creating wolf. Interface cannot access everything\n");
        Animal wolf = new WolfCharacter("The Wolf");
        wolf.saySomething();
        printGreenGeneric("The wolf name is: %s. Of course now we use down casting\n",
                ((Wolf) wolf).name);
        // 2. Comparing with `thenComparing`
        printRainbowLn("==");
        printYellowGeneric("### Creating Bird. Interface cannot access everything\n");
        Animal bird = new BirdCharacter("Sasha");
        var list = List.of(wolf, bird);
        Comparator<Animal> comparator = Comparator.comparing(Animal::getName);
        Stream<Animal> animalStream = list.stream().sorted(comparator.thenComparing(Animal::found));
        printGreenGeneric("%s\n", list.toString());
        printGreenGeneric("The wolf name is: %s. Of course now we use down casting\n",
                ((Wolf) wolf).name);
        printGreenGeneric("The bird name is: %s. Of course now we use down casting\n",
                ((BirdCharacter) bird).name);
        printGreenGeneric("The new collection should be reordered: %s\n",
                animalStream.collect(Collectors.toList()));

        // 3. AccessController and Permissions
        printRainbowLn("==");
        printYellowGeneric("### We will save our OST in /tmp/ost.txt.\n");
        printYellowGeneric("### After running there should be just one TEST text written on that file.\n");
        var filenameManager = new FileManager();
        filenameManager.testFile();

        // 4. Marking with `markSupported`s
        printRainbowLn("==");
        printYellowGenericLn("### Checking how marking works with a BufferedReader");
        printYellowGenericLn("### Don't forget that readAhead is an optimization parameter");
        printYellowGenericLn("### It has no logic influence");
        try (Reader r = new BufferedReader(
                new FileReader(
                        new File(Mastery2Dot2Runner
                                .class.getResource("/lyrics.txt").toURI())))) {
            Consolerizer.printBrightCyanGenericLn("The Reader class does not support mark %s", new Reader() {
                @Override
                public int read(char[] cbuf, int off, int len) throws IOException {
                    return 0;
                }

                @Override
                public void close() throws IOException {

                }
            }.markSupported());
            Consolerizer.printBlueGenericLn("The BufferedReader class does support mark %s", r.markSupported());
            if (r.markSupported()) {
                BufferedReader in = (BufferedReader) r;
                Consolerizer.printGreenGenericLn(in.readLine());
                in.mark(5);
                Consolerizer.printBlueGenericLn(in.readLine());
                Consolerizer.printGreenGenericLn(in.readLine());
                in.reset();
                Consolerizer.printBlueGenericLn(in.readLine());
                in.reset();
                Consolerizer.printBlueGenericLn(in.readLine());
                Consolerizer.printGreenGenericLn(in.readLine());
                Consolerizer.printGreenGenericLn(in.readLine());
                Consolerizer.printGreenGenericLn(in.readLine());
                Consolerizer.printGreenGenericLn(in.readLine());
                Consolerizer.printGreenGenericLn(in.readLine());
                in.reset();
                Consolerizer.printBlueGenericLn(in.readLine());
            } else {
                Consolerizer.printRedGenericLn("Mark Not Supported");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        // 5. `StringBuilder` vs `StringBuffer`
        printRainbowLn("==");
        printYellowGenericLn("### StringBuilder and StringBuffer have the same methods but their implementations are different.");
        printYellowGenericLn("### StringBuilder and StringBuffer do not have a trim() method like String does!.");

        var sBuilder = new StringBuilder();
        sBuilder.ensureCapacity(10);
        sBuilder.append(true);
        sBuilder.append("Okay, um, hmm, in that case, the part of the Grandfather will be played by, huh, a bassoon...\n");
        sBuilder.reverse();
        sBuilder.setLength(20);
        printGreenGenericLn(sBuilder.toString());
        sBuilder.trimToSize();
        printGreenGenericLn(sBuilder.toString());

        var sBuffer = new StringBuffer();
        sBuffer.ensureCapacity(10);
        sBuffer.append(true);
        sBuffer.append("Okay, um, hmm, in that case, the part of the Grandfather will be played by, huh, a bassoon...\n");
        sBuffer.reverse();
        sBuffer.setLength(20);
        printGreenGenericLn(sBuffer.toString());
        sBuffer.trimToSize();
        printGreenGenericLn(sBuffer.toString());

        // 6. Super constructors
        printRainbowLn("==");
        printYellowGenericLn("### When you develop a subclass, its constructors must know which super constructor to call");
        printYellowGenericLn("### If none is available, then the default, zero argument constructor is called");
        printYellowGenericLn("### One or more are available, then the subclass constructor must determine which constructor to use using super");
        printYellowGenericLn("### One or more constructors are implemented and none default is available, then the default is no longer available");

        var birdCharacter = new BirdCharacter("Sasha");
        printGreenGenericLn(birdCharacter.getName());
        printGreenGenericLn(birdCharacter.getInstrument());

        // 7. Abstraction: Interfaces vs Classes
        printRainbowLn("==");
        printYellowGenericLn("### Know a few things about Interfaces");
        printYellowGenericLn("### All methods without a body are implicitly abstract and public");
        printYellowGenericLn("### public and abstract are redundant for these implicit properties");
        printGreenGenericLn(wolf.getName());
        printGreenGenericLn(wolf.getInstrument());

        // 8. Stream filters
        printRainbowLn("==");
        printYellowGenericLn("### A parallel stream can change to be a sequential stream");
        printYellowGenericLn("### BaseStream.sequential() vs BaseStream.parallel");
        printYellowGenericLn("### Also in partitionBy the way to distinguish is using Boolean");
        var duck = new DuckCharacter("Bruce");
        var cat = new CatCharacter("Louis");

        var streamOfCharacters1 = Stream.of(wolf, cat, bird, duck);
        var streamFull1 = streamOfCharacters1.filter(animal -> animal instanceof Bird);
        streamFull1.forEach(Consolerizer::printGreenGenericLn);
        var streamOfCharacters2 = Stream.of(wolf, cat, bird, duck);
        var streamFull2 = streamOfCharacters2.parallel()
                .filter(animal -> !(animal instanceof Wolf))
                .filter(animal -> !(animal instanceof Feline))
                .sequential();
        streamFull2.forEach(Consolerizer::printGreenGenericLn);
        var streamOfCharacters3 = Stream.of(wolf, cat, bird, duck);
        Map<Boolean, List<Animal>> partitionMap = streamOfCharacters3.collect(Collectors.partitioningBy(animal -> animal instanceof Bird));
        var streamFull3 = partitionMap.get(Boolean.TRUE).stream();
        streamFull3.forEach(Consolerizer::printGreenGenericLn);

        // 9. Question mark in Mappings (left vs right)
        printRainbowLn("==");
        printYellowGenericLn("### You do not need to know the type on the left operand of an assignment operation");
        printYellowGenericLn("### You also cannot use diamond notation. You can use question marks as placeholders");
        printYellowGenericLn("### Question marks are not allowed on the right");
        printYellowGenericLn("### On a practical note it seems to be because a question mark is a wildcard");
        printYellowGenericLn("### Wildcards do not make sense during creation.");
        printYellowGenericLn("### Diamond notation always knows that its type is defined on the left ");

        Map<String, List<Animal>> mapOfAnimals1 = new HashMap<String, List<Animal>>();
        Map<String, List<Animal>> mapOfAnimals2 = new HashMap<>();
        Map<?, List<Animal>> mapOfAnimals3 = new HashMap<>();
        Map<?, List<?>> mapOfAnimals4 = new HashMap<>();
        Map<?, ?> mapOfAnimals5 = new HashMap<>();
        var mapOfAnimals6 = new HashMap<>();
        var mapOfAnimals7 = new HashMap<Animal, List<Animal>>();

        printBlueGenericLn("Map<String, List<Animal>> mapOfAnimals1 = new HashMap<String, List<Animal>>(); -> %s", mapOfAnimals1.getClass());
        printBlueGenericLn("Map<String, List<Animal>> mapOfAnimals2= new HashMap<>();", mapOfAnimals2.getClass());
        printBlueGenericLn("Map<?, List<Animal>> mapOfAnimals3 = new HashMap<>(); -> %s", mapOfAnimals3.getClass());
        printBlueGenericLn("Map<?, List<?>> mapOfAnimals4 = new HashMap<>(); -> %s", mapOfAnimals4.getClass());
        printBlueGenericLn("Map<?,?> mapOfAnimals5 = new HashMap<>(); -> %s", mapOfAnimals5.getClass());
        printBlueGenericLn("var mapOfAnimals6 = new HashMap<>(); -> %s", mapOfAnimals6.getClass());
        printBlueGenericLn("var mapOfAnimals7 = new HashMap<Animal, List<Animal>>(); -> %s", mapOfAnimals7.getClass());

        // 10. provider() in modularity
        printRainbowLn("==");
    }

}