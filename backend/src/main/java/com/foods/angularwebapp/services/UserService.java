package com.foods.angularwebapp.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.foods.angularwebapp.models.User;
import com.foods.angularwebapp.security.Auth;
import com.foods.angularwebapp.security.Encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private Auth auth;
  private EntityManager em;

  private String secretKey;

  @Autowired
  public UserService(@Value("${application.secretkey}") String secretKey, Auth auth,
      EntityManager em) {
    this.auth = auth;
    this.em = em;
    this.secretKey = secretKey;
  }

  /**
   * Authenticates user and returns JWS
   * 
   * @return JWS string if user was found, null if no such user exists
   */
  public String authenticateAndGenerateToken(User user) {
    if (!isAdmin(user))
      return null;
    return this.auth.createTokenForAdmin();
  }

  /**
   * Logs user out
   * 
   * It invalidates the authentication token and returns it
   */
  public String invalidateToken(String authToken) {
    return auth.invalidateToken(authToken);
  }

  /**
   * Authenticates user
   */
  public boolean isAdmin(User user) {
    TypedQuery<User> query = em.createQuery(
        "SELECT u FROM User u WHERE u.isAdmin = 1 AND u.username = :username AND u.password = :password", User.class);
    String hashedPass = new Encryption().encryptPassword(user.getPassword(), secretKey);
    query.setParameter("username", user.getUsername());
    query.setParameter("password", hashedPass);

    try {
      query.getSingleResult();
    } catch (NoResultException e) {
      return false;
    }
    return true;
  }

  public boolean isAuthenticated(String authToken) {
    return auth.userIsAuthenticated(authToken);
  }
}
