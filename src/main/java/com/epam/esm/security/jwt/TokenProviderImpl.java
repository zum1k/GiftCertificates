package com.epam.esm.security.jwt;

import com.epam.esm.security.jwt.TokenProvider;
import com.epam.esm.service.user.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenProviderImpl implements TokenProvider {
  @Value("${spring.security.oauth2.client.secret}")
  private String jwtSecret;

  @Override
  public String generateToken(String username, String password) {
    Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
    return Jwts.builder()
        .setSubject(username)
        .setSubject(password)
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      log.error("Token expired");
    } catch (UnsupportedJwtException unsEx) {
      log.error("Unsupported jwt");
    } catch (MalformedJwtException mjEx) {
      log.error("Malformed jwt");
    } catch (SignatureException sEx) {
      log.error("Invalid signature");
    } catch (Exception e) {
      log.error("invalid token");
    }
    return false;
  }

  @Override
  public String getLoginFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }
}
