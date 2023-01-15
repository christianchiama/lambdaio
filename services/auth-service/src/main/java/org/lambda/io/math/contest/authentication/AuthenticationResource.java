package org.lambda.io.math.contest.authentication;

import lombok.*;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.lambda.io.math.contest.authentication.domain.TokenEntity;
import org.lambda.io.math.contest.authentication.exception.AuthenticationPasswordException;
import org.lambda.io.math.contest.authentication.exception.AuthenticationUsernameException;
import org.lambda.io.math.contest.authentication.rest.AuthenticationRequest;
import org.lambda.io.math.contest.authentication.rest.AuthenticationResponse;
import org.lambda.io.math.contest.authentication.service.ReactiveUserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;

@Path("/api/authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor(onConstructor = @__(@Inject))
public class AuthenticationResource {

    @Inject ReactiveUserService userService;

    @Inject
    JsonWebToken jwt;

    @PermitAll
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthenticationRequest authRequest) throws AuthenticationUsernameException, AuthenticationPasswordException {
        final String token = userService.authenticate(authRequest);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(new ObjectId());
        tokenEntity.setToken(token);
        tokenEntity.setUsername(authRequest.getUsername());
        tokenEntity.setCreatedAt(Date.from(Instant.now()));
        //tokenEntity.persist().subscribeAsCompletionStage().join();
        return Response.ok(new AuthenticationResponse(tokenEntity)).build();
    }

    @RolesAllowed("ROLE_APPLICATION")
    @GET
    @Path("roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkRolesAllowed(){
        return Response.ok(String.format("User %s has the following roles: %s", jwt.getName(), String.join(", ", jwt.getGroups()))).build();
    }

}