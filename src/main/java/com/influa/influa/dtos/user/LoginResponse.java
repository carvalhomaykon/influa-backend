package com.influa.influa.dtos.user;

public record LoginResponse(String token, long expiresIn) {
}
