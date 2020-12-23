package org.jesperancinha.jtd.jee.crums.crums1;

import org.jesperancinha.console.consolerizer.Consolerizer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import static org.jesperancinha.console.consolerizer.Consolerizer.printBlueGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printMagentaGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printOrangeGenericLn;

@Named( "Reminders" )
@ApplicationScoped
@Startup
@Singleton
public class Reminders {

    @PostConstruct
    public void postConstruct() {
        Consolerizer.typingWaitGlobal = 0;
        Consolerizer.maxLineCharsGlobal = 150;
        Consolerizer.titleSpread = 150;
        Consolerizer.blackAndWhite = false;
        reminder1();
    }


    private static void reminder1() {
        printBlueGenericLn("Crum1 - Which are Java EE components ?");

        printMagentaGenericLn("Web Components:");
        printOrangeGenericLn("Java Server Faces (JSF)");
        printOrangeGenericLn("Java Servlet");
        printOrangeGenericLn("Java Server Pages (JSP)");
        printMagentaGenericLn("Java EE Components:");
        printOrangeGenericLn("Application clients and applets that run on the client");
        printOrangeGenericLn("Enterprise Java Beans (EJB) and business components that run on the server");
    }
}