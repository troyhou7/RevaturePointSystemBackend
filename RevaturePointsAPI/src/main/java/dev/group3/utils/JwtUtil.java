package dev.group3.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public static String generate(int employeeId, String firstName, String lastName, String role) {
        String token = JWT.create()
                .withClaim("role", role)
                .withClaim("firstName", firstName)
                .withClaim("lastName", lastName)
                .withClaim("employeeId", employeeId)
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT verifyToken(String token) {
        DecodedJWT jwt;
        try {
            jwt = JWT.require(algorithm).build().verify(token);
            return jwt;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
