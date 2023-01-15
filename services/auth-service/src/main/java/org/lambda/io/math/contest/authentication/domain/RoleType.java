package org.lambda.io.math.contest.authentication.domain;

import lombok.*;

/**
 * @author: Christian Chiama
 * @project: lambdaio
 * @Date: 13/01/23
 * @Time: 02:05
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public enum RoleType {

    USER("USER"),
    ADMIN("ADMIN"),
    DEVELOPER("DEVELOPER"),
    APPLICATION("APPLICATION"),
    DEVOPS("DEVOPS"),
    STUDENT("STUDENT"),
    STUDENT_GRADED("STUDENT_GRADED");

    private String role;
}
