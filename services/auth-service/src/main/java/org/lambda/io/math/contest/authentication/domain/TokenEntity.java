package org.lambda.io.math.contest.authentication.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 07:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MongoEntity(database = "lambdaio", collection = "tokens")
public class TokenEntity extends ReactivePanacheMongoEntityBase {

    @BsonProperty("id")
    @BsonId
    private ObjectId id;
    private String token;
    private String username;
    private Date createdAt;
    private Date expiresAt;
}
