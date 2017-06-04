package com.util;


import com.entity.User;
import com.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.lang.Assert;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private Key key = MacProvider.generateKey();

    /**
     * Takes in a User object and generates a JWT-token holding relevant user information
     * @param user user object
     * @return generated JWT-token
     * @throws UnsupportedEncodingException
     */
    public String generateJWT(User user) {
        // We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
        return Jwts.builder()
                .setSubject("user")
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .claim("role", user.getRole().getName())
                .signWith(SignatureAlgorithm.HS512, this.key)
                .compact();
    }

    /**
     * Takes in a JWT-token and attempts to parses it using our key
     * @param token JWT-token sent by the client
     * @return claims object that holds the parsed information
     * @throws UnsupportedEncodingException if the parsing fails (token is invalid)
     */
    public Claims parseJWT(String token) throws UnsupportedEncodingException {
        System.out.println(Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody().getSubject());
        System.out.println("");
        System.out.println(Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody());

        return Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
    }
}
