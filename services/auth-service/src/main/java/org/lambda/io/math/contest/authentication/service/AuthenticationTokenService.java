package org.lambda.io.math.contest.authentication.service;

import io.smallrye.jwt.build.Jwt;
import lombok.*;
import org.eclipse.microprofile.jwt.Claims;
import org.lambda.io.math.contest.authentication.domain.UserEntity;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Singleton;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;

@Singleton
public class AuthenticationTokenService {
    private static final String PADDING = "AES/ECB/PKCS5Padding";
    private static final String KEY = "PdSgVkYp3s6v9y/B";

    public String encrypt(final String dataToEncode) {
        try {
            return Base64
                    .getEncoder()
                    .encodeToString(AuthenticationTokenService
                            .init(Cipher.ENCRYPT_MODE)
                            .doFinal(dataToEncode.getBytes()));
        } catch (Exception e) {
            throw new IllegalStateException("Cannot encrypt text", e);
        }
    }

    public String decrypt(final String encryptedData) {
        try {
            return (Arrays
                    .toString(AuthenticationTokenService
                    .init(Cipher.DECRYPT_MODE)
                    .doFinal(Base64.getDecoder().decode(encryptedData))));
        } catch (Exception e) {
            throw new IllegalStateException("Cannot decrypt text", e);
        }
    }

    public String generateToken(final UserEntity user) {
        return Jwt.issuer("Lambda.io")
                .upn(user.getEmail())
                .expiresIn(Duration.ofDays(30))
                .preferredUserName(user.getUsername())
                .audience("Lambda.io")
                .issuedAt(Instant.now())
                .sign();
    }

    
    
    @SneakyThrows
    public static Cipher init(int opmode) {
        final SecretKey secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        final Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(opmode, secretKey);
        return cipher;
    }

}
