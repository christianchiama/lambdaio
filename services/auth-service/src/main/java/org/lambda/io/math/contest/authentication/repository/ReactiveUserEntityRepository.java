package org.lambda.io.math.contest.authentication.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import org.bson.types.ObjectId;
import org.lambda.io.math.contest.authentication.domain.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 01:46
 */

@ApplicationScoped
public class ReactiveUserEntityRepository implements ReactivePanacheMongoRepositoryBase<UserEntity, ObjectId> {
    public Optional<UserEntity> findByEmail(final String email){
        return find("email", email)
                .singleResultOptional()
                .subscribeAsCompletionStage()
                .join();
    }
}
