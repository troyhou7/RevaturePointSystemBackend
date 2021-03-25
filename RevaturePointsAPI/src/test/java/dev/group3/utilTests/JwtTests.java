package dev.group3.utilTests;


import com.auth0.jwt.interfaces.DecodedJWT;
import dev.group3.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTests {

    @Test
    void generate_jwt() {
        String jwt = JwtUtil.generate(1, "Adam", "Ranieri", "trainer");
        System.out.println(jwt);
    }

    @Test
    void decode_jwt() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJBZGFtIiwibGFzdE5hbWUiOiJSYW5pZXJpIiwicm9sZSI6InRyYWluZXIiLCJlbXBsb3llZUlkIjoxfQ.QPEqf62fv3_fg8OycIMc9A2qvfBSL7ioMtNKBAZsk6U";
        DecodedJWT jwt = JwtUtil.verifyToken(token);
        String role = jwt.getClaim("role").asString();
        String firstName = jwt.getClaim("firstName").asString();
        Assertions.assertTrue(role.equals("trainer"));
        Assertions.assertTrue(firstName.equals("Adam"));
    }

    @Test
    void decode_incorrect_jwt() {
        String token = "eyJ0eXAiOiJQiLCJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJBZGFtIiwibGFzdE5hbWUiOiJSYW5pZXJpIiwicm9sZSI6InRyYWluZXIiLCJlbXBsb3llZUlkIjoxfQ.QPEqf62fv3_fg8OycIMc9A2qvfBSL7ioMtNKBAZsk6U";
        DecodedJWT jwt = JwtUtil.verifyToken(token);

        Assertions.assertNull(jwt);
    }
}
