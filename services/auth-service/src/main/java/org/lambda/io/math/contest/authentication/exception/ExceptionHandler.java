package org.lambda.io.math.contest.authentication.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception exception) {
        if (exception instanceof AuthenticationPasswordException) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Wrong password")
                    .build();
        }
        if (exception instanceof AuthenticationUsernameException) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Wrong username")
                    .build();
        }
        if (exception instanceof UserNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found")
                    .build();
        }
        if (exception instanceof TokenNotFoundException) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("token not found")
                    .build();
        }
        if (exception instanceof TokenNotValidException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("token not valid")
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getMessage())
                .build();
    }

}