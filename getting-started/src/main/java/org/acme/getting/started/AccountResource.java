package org.acme.getting.started;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/finance")
@Produces(APPLICATION_JSON)
public class AccountResource {
    @Inject
    AccountRepository repository;
    @Inject
    AccountService accountService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GET
    @Path("/account/{name}")
    @Timed(name="seeAccount", description = "A measure of how much time takes to serve a account")
    @Counted(name="countSeeAccount", description = "Count number of check account")
    public Response seeAccount(@PathParam("name") String name) {
        Account account = repository.findByName(name);
        if (account != null){
            log.debug("Found name " + name);
            return Response.ok(account).build();
        } else {
            log.debug("No hero found with name " + name);
            return Response.noContent().build();
        }
    }

    @POST
    @Timed(name="checkCreateAccount", description = "A measure of how much time takes to serve a Create Account")
    @Counted(name="countCreateAccount", description = "Count number of served a Create Account")
    public Response create(@Valid User users){
        users = accountService.create(users);
        log.info("new account has been created: " + users.username);
        return Response.ok(users).build();
    }


    @PUT
    @Path("/saveChanges/{name}")
    public Response saveChanges(@PathParam("name") String name, @Valid Account update){
        update = accountService.saveChanges(name,update);
        log.debug("account {} changes has been saved", name);
        return Response.ok(update).build();
    }

    @PUT
 /*   @Timed(name="updateSaving", description = "A measure of how much time takes to serve update Saving")
    @Counted(name="updateSaving", description = "Count number of update Saving") */
    @Path("/updateSaving/{name}")
    public Response updateSaving(@PathParam("name") String name, @Valid Account update){
        update = accountService.updateSaving(name,update);
        log.debug("account {} saving has been updated", name);
        return Response.ok(update).build();
    }

    @GET
   // @Timed(name="seeSaving", description = "A measure of how much time takes to serve see Saving")
   // @Counted(name="seeSaving", description = "Count number of see Saving")
    @Path("/seeSaving/{name}")
    public Response seeSaving(@PathParam("name") String name) {
        Saving saving = accountService.seeSaving(name);
        return Response.ok(saving).build();
    }

}
