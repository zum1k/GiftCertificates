package com.epam.esm.security.jwt;

public interface TokenProvider {
  String generateToken(String username, String password);

  boolean validateToken(String token);

  String getLoginFromToken(String token);
}
