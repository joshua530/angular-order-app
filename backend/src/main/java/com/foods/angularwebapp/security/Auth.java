package com.foods.angularwebapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.security.auth.message.AuthException;

@Component
public class Auth {
  /** Secret key used for JWS signing */
  @Value("${application.secretkey}")
  private String secretKey;

  /** The web server's domain name */
  @Value("${application.domain}")
  private String domain;

  private String adminUser = "admin";

  public Auth() {
  }

  public boolean userIsAuthenticated(String token) {
    try {
      Claims claims = getClaimsFromToken(token);
      long tokenExpirationTime = claims.getExpiration().getTime();
      String issuer = claims.getIssuer();
      String subject = claims.getSubject();

      if (tokenExpirationTime < currentTime())
        throw new AuthException("Session has expired, please log in again to continue");
      if (!issuer.equals(domain))
        throw new AuthException("Invalid authentication details, log in to continue");
      if (!subject.equals(adminUser))
        throw new AuthException("Invalid authentication details, log in to continue");
    } catch (JwtException e) {
      throw new RuntimeException("Invalid authentication details, login to continue");
    } catch (AuthException e) {
      throw new RuntimeException(e.getMessage(), e);
    }

    return true;
  }

  public String invalidateToken(String token) {
    Map<String, Object> newDetails = new HashMap<>();
    newDetails.put("sub", "");
    newDetails.put("exp", addToCurrentTime(-1));
    newDetails.put("iss", "");
    return updateToken(token, newDetails);
  }

  /** Updates JWS details */
  public String updateToken(String token, Map<String, Object> details) {
    userIsAuthenticated(token);
    Claims body = getClaimsFromToken(token);
    details.forEach((k, v) -> {
      body.replace(k, v);
    });
    Map<String, Object> header = getHeader();
    return generateToken(header, body);
  }

  /** Adds more time to the authentication token to avoid its expiration */
  public String updateTokenEpirationTime(String token) {
    userIsAuthenticated(token);
    Claims body = getClaimsFromToken(token);
    body.replace("exp", addToCurrentTime(1));
    Map<String, Object> header = getHeader();
    return generateToken(header, body);
  }

  public Claims getClaimsFromToken(String token) {
    Jws<Claims> claims = Jwts.parserBuilder()
        .setSigningKey(getSecretKey()).build().parseClaimsJws(token);
    return claims.getBody();
  }

  /** Creates JWS for given user */
  public String createTokenForAdmin() {
    Map<String, Object> header = getHeader();
    Claims payload = getPayload();
    return generateToken(header, payload);
  }

  private String generateToken(Map<String, Object> header, Claims payload) {
    String jws = Jwts.builder()
        .setHeader(header)
        .setClaims(payload)
        .signWith(getSecretKey()).compact();
    return jws;
  }

  /** Generates header to use with JWS */
  private Map<String, Object> getHeader() {
    Map<String, Object> header = new HashMap<>();
    header.put("typ", "jwt");
    header.put("alg", "HS256");
    return header;
  }

  /** returns number of seconds elapsed since the epoch */
  private long currentTime() {
    return new Date().getTime() / 1000;
  }

  /**
   * adds given number of days to the current time
   * 
   * @param numDays the number of days to add to the current time
   */
  private long addToCurrentTime(int numDays) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DATE, numDays);
    long expiryTime = calendar.getTime().getTime() / 1000;
    return expiryTime;
  }

  /** Creates payload and fills it with claims for creating the JWS */
  private Claims getPayload() {
    Claims claims = Jwts.claims();
    claims.put("sub", "admin");
    claims.put("exp", addToCurrentTime(1));
    claims.put("iss", domain);

    return claims;
  }

  private SecretKey getSecretKey() {
    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    return key;
  }
}
