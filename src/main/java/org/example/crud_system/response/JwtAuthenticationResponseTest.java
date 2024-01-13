package org.example.crud_system.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JwtAuthenticationResponseTest {

    @Test
    void noArgsConstructorTest() {
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        assertNull(response.getToken());
    }

    @Test
    void allArgsConstructorTest() {
        String token = "testToken";
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token);
        assertEquals(token, response.getToken());
    }

    @Test
    void builderTest() {
        String token = "testToken";
        JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                .token(token)
                .build();
        assertEquals(token, response.getToken());
    }
}