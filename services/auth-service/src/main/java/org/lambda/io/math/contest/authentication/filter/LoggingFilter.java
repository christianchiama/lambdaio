package org.lambda.io.math.contest.authentication.filter;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 05/01/23
 * @Time: 03:48
 */
import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter {

    private static final Logger LOGGGER = Logger.getLogger(LoggingFilter.class);

    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext context) {

        final String method = context.getMethod();
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();
        final String host = request.host();
        LOGGGER.infof("\r\n[Request ->" + path + "] \r\n host: %s \r\n method: %s \r\n path: %s \r\n IP: %s", host, method, path, address);
        request.body().onSuccess(s-> {
            LOGGGER.infof("\r\n[Request Body] %s", s.toJsonObject().encodePrettily());
        });
    }
}
