package org.lambda.io.math.contest.authentication.rest;

import lombok.*;
import org.lambda.io.math.contest.authentication.domain.TokenEntity;

@Getter
@Setter
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private TokenEntity tokenEntity;
}