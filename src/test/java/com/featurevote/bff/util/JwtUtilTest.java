package com.featurevote.bff.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void generateToken_shouldReturnNonBlankToken() {
        // when
        String token = jwtUtil.generateToken("alice");

        // then
        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    void extractUsername_shouldReturnOriginalUsername() {
        // given
        String username = "alice";
        String token = jwtUtil.generateToken(username);

        // when
        String extractedUsername = jwtUtil.extractUsername(token);

        // then
        assertEquals(username, extractedUsername);
    }

    @Test
    void extractUsername_shouldThrowForInvalidToken() {
        // when & then
        assertThrows(RuntimeException.class, () -> jwtUtil.extractUsername("invalid.token.value"));
    }
}