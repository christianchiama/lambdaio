package org.lambda.io.math.contest.authentication.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 02:04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MongoEntity(database = "lambdaio", collection = "roles")
public class RoleEntity extends ReactivePanacheMongoEntityBase {

    @BsonProperty("id")
    @BsonId
    private ObjectId id;
    private RoleType role;
    private String description;


}