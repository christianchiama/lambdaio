package org.lambda.io.math.contest.authentication.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lambda.io.math.contest.authentication.domain.UserEntity;
import org.lambda.io.math.contest.authentication.exception.AuthenticationPasswordException;
import org.lambda.io.math.contest.authentication.repository.ReactiveUserEntityRepository;

import org.lambda.io.math.contest.authentication.exception.*;
import org.lambda.io.math.contest.authentication.rest.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@SuppressWarnings("ALL")
@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
public class ReactiveUserService {

    @Inject ReactiveUserEntityRepository reactiveUserEntityRepository;
    @Inject
    AuthenticationTokenService authenticationTokenService;

    public String authenticate(AuthenticationRequest authRequest)
            throws AuthenticationUsernameException, AuthenticationPasswordException {
        final UserEntity user = reactiveUserEntityRepository.findByEmail(authRequest.getUsername())
                .orElseThrow(AuthenticationUsernameException::new);
        if (user.getPassword().equals(authenticationTokenService.encrypt(authRequest.getPassword()))){

            return authenticationTokenService.generateToken(user);
        }
        throw new AuthenticationPasswordException();
    }


}
