package org.lambda.io.math.contest.authentication;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 09:10
 */

@ApplicationScoped
public class ApplicationLifecycle {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }

}