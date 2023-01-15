package org.lambda.io.math.contest.authentication.rest;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}