package org.lambda.io.math.contest.authentication;

import com.mongodb.MongoClientSettings;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.lambda.io.math.contest.authentication.support.UserEntityCodec;
import org.lambda.io.math.contest.authentication.support.UserEntityCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 09:10
 */
@QuarkusMain
public class Application {

    public static void main(String... args) {
    fromRegistries(
                CodecRegistries.fromCodecs(new UserEntityCodec()), // <---- this is the custom codec
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(new UserEntityCodecProvider())
        );

        Quarkus.run(AuthenticationQuarkusApp.class, args);
    }

    public static class AuthenticationQuarkusApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
