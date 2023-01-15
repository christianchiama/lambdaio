package org.lambda.io.math.contest.authentication.support;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.lambda.io.math.contest.authentication.domain.RoleEntity;
import org.lambda.io.math.contest.authentication.domain.UserEntity;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 09:03
 */

public class UserEntityCodecProvider implements CodecProvider {
    @Override
    @SuppressWarnings("unchecked")
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz.equals(UserEntity.class)) {
            return (Codec<T>) new UserEntityCodec();
        }
        return null;
    }

}
