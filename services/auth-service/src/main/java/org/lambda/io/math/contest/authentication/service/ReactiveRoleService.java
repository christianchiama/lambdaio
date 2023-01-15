package org.lambda.io.math.contest.authentication.service;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lambda.io.math.contest.authentication.domain.RoleEntity;
import org.lambda.io.math.contest.authentication.domain.UserEntity;
import org.lambda.io.math.contest.authentication.exception.AuthenticationPasswordException;
import org.lambda.io.math.contest.authentication.exception.AuthenticationUsernameException;
import org.lambda.io.math.contest.authentication.repository.ReactiveRoleEntityRepository;
import org.lambda.io.math.contest.authentication.repository.ReactiveUserEntityRepository;
import org.lambda.io.math.contest.authentication.rest.AuthenticationRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Optional;

@SuppressWarnings("ALL")
@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
public class ReactiveRoleService {

    @Inject
    ReactiveRoleEntityRepository reactiveRoleEntityRepository;
    @Inject
    AuthenticationTokenService authenticationTokenService;


    public Uni<Optional<RoleEntity>> create(RoleEntity userEntity) {
        return Uni
                .createFrom()
                .item(Optional.ofNullable(userEntity))
                .onItem().transform(user -> {
                    if (user.isPresent()) {
                        assert userEntity != null;
                        RoleEntity entity = user.get();
                        entity.setDescription(userEntity.getDescription());
                        entity.setRole(userEntity.getRole());
                    }
                    return user;
                }).call(entity -> entity.orElseThrow().persist()).log();
    }


}
