package org.lambda.io.math.contest.authentication;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.lambda.io.math.contest.authentication.domain.RoleEntity;
import org.lambda.io.math.contest.authentication.domain.RoleType;
import org.lambda.io.math.contest.authentication.exception.AuthenticationPasswordException;
import org.lambda.io.math.contest.authentication.exception.AuthenticationUsernameException;
import org.lambda.io.math.contest.authentication.rest.AuthenticationRequest;
import org.lambda.io.math.contest.authentication.rest.AuthenticationResponse;
import org.lambda.io.math.contest.authentication.service.ReactiveRoleService;
import org.lambda.io.math.contest.authentication.service.ReactiveUserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api/roles")
@AllArgsConstructor(onConstructor = @__(@Inject))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

    @Inject ReactiveRoleService reactiveRoleService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> create(RoleEntity roleEntity)  {
        final Uni<Optional<RoleEntity>> token = reactiveRoleService.create(roleEntity);
        return token.onItem().transform(t -> Response.ok(t).build());
    }



}