package Com.test.JWTConfig;

import Com.test.Config.CustomPrincipal;
import Com.test.Model.user;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class generateToken {

    @Value("${JWT-secret}")
    public  String  secret;

    public  String  generate(user u){

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

       return Jwts.builder()
                .claims()
                .subject(u.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*2*1000))
                .and()
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    //Verify token......


    // 2. Extract name/email from token
    public String extractName(String token) {
        return extractClaim(token, Claims::getSubject);  // subject = email
    }

    // Helper to extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 3. Validate token
    public boolean valid(String token, CustomPrincipal user) {
        final String name = extractName(token);
        return (name.equals(user.getUsername()) && !isTokenExpired(token));
    }

    // Helper: check if expired
    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    // Helper: extract all claims
    private Claims extractAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
