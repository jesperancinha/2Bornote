package org.jesperancinha.jtd.jee.the.netherlands.rest;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printOrangeGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.setupFastDefault;

@Path("/arrival")
@SessionScoped
public class KingsAndQueensArrival implements Serializable {

    @Inject
    private Event<ArrivalEvent> arrivalEventEmitter;

    @Inject
    private Event<ArrivalForCleanerEvent> arrivalForCleanerEventEmitter;

    @Inject
    private EventProcessor eventProcessor;

    @Inject
    private CleanerProcessor cleanerProcessor;

    @Inject
    private UserTransaction userTransaction;

    @GET
    @Path("{monarch}")
    @Produces(MediaType.APPLICATION_JSON)
    public String processArrival(
        @PathParam("monarch")
            String monarch) throws InterruptedException {
        setupFastDefault();
        printOrangeGenericLn("We will now see what will happen with our %s with hash %s", eventProcessor.getClass()
            .getCanonicalName(), eventProcessor.hashCode());
        printOrangeGenericLn("In the same way we'll se what happens with %s with hash %s", cleanerProcessor.getClass()
            .getCanonicalName(), cleanerProcessor.hashCode());
        final ArrivalEvent arrivalEvent = new ArrivalEvent();
        final String message = String.format("Your Majesty King/Queen %s is arriving to the Palace", monarch);
        arrivalEvent.setMessage(message);
        setupFastDefault();
        arrivalEventEmitter.fire(arrivalEvent);
        final ArrivalForCleanerEvent event = new ArrivalForCleanerEvent();
        event.setMessage(message);
        setupFastDefault();
        arrivalForCleanerEventEmitter.fire(event);
        printGreenGenericLn("Note that if you want to simulate  IN_PROGRESS, IF_EXISTS you HAVE TO debug in this method");
        printGreenGenericLn("This will stop the instance and the context in its tracks and upon resuming, the events will be catched by the processors.");
        return message;
    }

    @GET
    @Path("transaction/{monarch}")
    @Produces(MediaType.APPLICATION_JSON)
    public String processTransactionArrival(
        @PathParam("monarch")
            String monarch) throws Exception {
        userTransaction.begin();
        setupFastDefault();
        printOrangeGenericLn("We will now see what will happen with our %s with hash %s", eventProcessor.getClass()
            .getCanonicalName(), eventProcessor.hashCode());
        final ArrivalEvent arrivalEvent = new ArrivalEvent();
        final String message = String.format("Your Majesty King/Queen %s is arriving to the Palace", monarch);
        arrivalEvent.setMessage(message);
        arrivalEventEmitter.fire(arrivalEvent);
        final ArrivalForCleanerEvent event = new ArrivalForCleanerEvent();
        event.setMessage(message);
        arrivalForCleanerEventEmitter.fire(event);
        userTransaction.commit();
        return message;
    }
}
