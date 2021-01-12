package org.jesperancinha.jtd.jee.portugal.rest.messages;

import org.jesperancinha.console.consolerizer.Consolerizer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static org.jesperancinha.console.consolerizer.Consolerizer.printOrangeGenericLn;

@Path("/history/messages")
public class RoyalPost {

    @POST
    @Path("/sendMessage")
    @Consumes("application/text")
    public Response createAccount(@Context
        HttpServletRequest req, KingdomMessage kingdomMessage) {
        printOrangeGenericLn(kingdomMessage);
        return Response.ok().build();
    }
}


