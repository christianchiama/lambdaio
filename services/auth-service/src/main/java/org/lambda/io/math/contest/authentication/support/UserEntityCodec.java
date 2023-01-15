package org.lambda.io.math.contest.authentication.support;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 08:57
 */

import com.mongodb.MongoClientSettings;
import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;
import org.lambda.io.math.contest.authentication.domain.RoleEntity;
import org.lambda.io.math.contest.authentication.domain.RoleType;
import org.lambda.io.math.contest.authentication.domain.UserEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserEntityCodec implements CollectibleCodec<UserEntity> {

    private final Codec<Document> documentCodec;

    public UserEntityCodec() {
        this.documentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public void encode(BsonWriter writer, UserEntity userEntity, EncoderContext encoderContext) {
        Document doc = new Document();
        doc.put("_id", userEntity.getId());
        doc.put("name", userEntity.getName());
        doc.put("lastname", userEntity.getLastname());
        doc.put("roles", userEntity.getRoles());
        doc.put("username", userEntity.getUsername());
        doc.put("password", userEntity.getPassword());
        doc.put("email", userEntity.getEmail());

        documentCodec.encode(writer, doc, encoderContext);
    }

    @Override
    public Class<UserEntity> getEncoderClass() {
        return UserEntity.class;
    }

    @Override
    public UserEntity generateIdIfAbsentFromDocument(UserEntity document) {
        if (!documentHasId(document)) {
            document.setId(new ObjectId());
        }
        return document;
    }

    @Override
    public boolean documentHasId(UserEntity document) {
        return document.getId() != null;
    }

    @Override
    public BsonValue getDocumentId(UserEntity document) {
        return new BsonString(document.getId().toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserEntity decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        UserEntity userEntity = new UserEntity();
        if (document.getString("id") != null) {
            userEntity.setId(document.getObjectId("_id"));
        }
        userEntity.setName(document.getString("name"));
        userEntity.setLastname(document.getString("lastname"));
        userEntity.setUsername(document.getString("username"));
        userEntity.setPassword(document.getString("password"));
        userEntity.setEmail(document.getString("email"));
        userEntity.setRoles((new HashSet<>((Collection<RoleEntity>) document.get("roles"))));
        return userEntity;
    }
}
