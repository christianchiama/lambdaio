package org.lambda.io.math.contest.authentication.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import org.bson.types.ObjectId;
import org.lambda.io.math.contest.authentication.domain.RoleEntity;
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
public class ReactiveRoleEntityRepository implements ReactivePanacheMongoRepositoryBase<RoleEntity, ObjectId> {
    public Optional<RoleEntity> findByEmail(final String email){
        return find("email", email).singleResultOptional().subscribeAsCompletionStage().join();
    }
}
