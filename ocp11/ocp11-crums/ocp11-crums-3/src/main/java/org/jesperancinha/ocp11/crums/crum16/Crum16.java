package org.jesperancinha.ocp11.crums.crum16;

import static org.jesperancinha.console.consolerizer.Consolerizer.printBlueGenericTitleLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printMagentaGenericLn;

public class Crum16 {

    public static void main(String[] args) {
        printBlueGenericTitleLn("Crum 16 - Advanced ways of using super between interfaces and classes");

        var class2 = new Class2();
        printMagentaGenericLn(class2.howDoesItFeel());

        printGreenGenericLn("The point is that super is not valid to use in interfaces directly");
        printGreenGenericLn("We can use super, but we need to use the Interface name as a reference first");
        printGreenGenericLn("This is also valid between class and interface");
        printGreenGenericLn("Between classes only super alone is valid");
        printGreenGenericLn("Using the reference name before will refer to a possible instance enclosing class");
    }
}