package springvascomm.restful.util;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
  private static final String SECRET = "ikoafianando";

  private static final long EXPIRATION_TIME = 3_600_000; // 1 hours
  private static final long REFRESH_EXPIRATION_TIME = 86_400_000; // 1 days
  public static String accessToken(String username) {
    return Jwts.builder()
      .setSubject(username)
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact();
  }

  public static String refreshToken (String username) {
    return Jwts.builder()
      .setSubject(username)
      .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact();
  }
  public static boolean validateToken(String token) {
    try {
      Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}
