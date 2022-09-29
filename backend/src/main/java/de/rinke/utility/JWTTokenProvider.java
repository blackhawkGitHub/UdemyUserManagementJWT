package de.rinke.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.rinke.constant.SecurityConstant;
import de.rinke.domain.UserPrincipals;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static de.rinke.constant.SecurityConstant.*;

public class JWTTokenProvider {

    @Value("jwt.secret")
    private String secret;

    public String generateJwtToken(UserPrincipals userPrincipal) {
        String[] claims = getClaimsFromUser(userPrincipal);
        return JWT.create()
                .withIssuer(GET_ARRAYS_LLC)
                .withAudience(GET_ARRAYS_ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(secret.getBytes(StandardCharsets.UTF_8)));
    }

    private String[] getClaimsFromUser(UserPrincipals userPrincipal) {
    }
}