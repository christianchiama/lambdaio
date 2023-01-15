package org.lambda.io.math.contest.authentication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 03/01/23
 * @Time: 22:08
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MongoEntity(database = "lambdaio", collection = "users")
public class UserEntity extends ReactivePanacheMongoEntityBase {

    @BsonProperty("id")
    @BsonId
    private ObjectId id;
    private String name;
    private String lastname;
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private Set<RoleEntity> roles = new HashSet<>();

}
