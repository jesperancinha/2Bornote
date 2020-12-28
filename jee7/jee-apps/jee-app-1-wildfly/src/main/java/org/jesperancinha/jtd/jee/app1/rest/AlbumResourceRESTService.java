package org.jesperancinha.jtd.jee.app1.rest;

import org.jesperancinha.jtd.jee.app1.controllers.AlbumController;
import org.jesperancinha.jtd.jee.app1.data.AlbumRepository;
import org.jesperancinha.jtd.jee.app1.domain.Album;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jesperancinha.console.consolerizer.Consolerizer.printMagentaGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.setupFastDefault;

@Path("/album/resource")
@RequestScoped
public class AlbumResourceRESTService {

    @Inject
    private Validator validator;

    @Inject
    private AlbumRepository albumRepository;

    @Inject
    private AlbumController albumController;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Album> listAllAlbums() {
        return albumRepository.findAllOrderedByName();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(APPLICATION_JSON)
    public Album lookupAlbumById(
        @PathParam("id")
            long id) {
        Album album = albumRepository.findById(id);
        if (album == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return album;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createAlbum(Album album) {
        setupFastDefault();
        Response.ResponseBuilder builder = null;
        try {
            Set<ConstraintViolation<Album>> violations = validator.validate(album);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
            albumController.createAlbum(album);
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            Set<ConstraintViolation<?>> violations = ce.getConstraintViolations();

            printMagentaGenericLn("Validation completed. violations found: " + violations.size());
            builder = Response.status(Response.Status.BAD_REQUEST)
                .entity(violations.stream()
                    .collect(Collectors.toMap(t -> t.getPropertyPath()
                        .toString(), ConstraintViolation::getMessage)));
        } catch (Exception e) {
            Map<String, String> stringHashMap = new HashMap<>();
            stringHashMap.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST)
                .entity(stringHashMap);
        }

        return builder.build();
    }

}