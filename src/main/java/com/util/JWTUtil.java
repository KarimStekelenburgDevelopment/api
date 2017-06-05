package com.util;


import com.entity.User;
import com.entity.UserRole;
import com.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.Properties;

@javax.annotation.Resource
public class JWTUtil {
    private Resource resource = new ClassPathResource("/application.properties");
    private Properties props = PropertiesLoaderUtils.loadProperties(resource);
    private final byte[] key = props.getProperty("JWTKey").getBytes("UTF-8");

    @Autowired
    private UserModel userModel;

    public JWTUtil() throws IOException {
    }

    /**
     * Takes in a User object and generates a JWT-token holding relevant user information
     * @param user user object
     * @return generated JWT-token
     * @throws UnsupportedEncodingException
     */
    public String generateJWT(User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject("user")
                .setIssuedAt(now)
                .claim("distortion", nowMillis)
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .claim("role", user.getRole().getName())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

    }

    /**
     * Takes in a JWT-token and attempts to parses it using our key
     * @param token JWT-token sent by the client
     * @return claims object that holds the parsed information
     * @throws UnsupportedEncodingException if the parsing fails (token is invalid)
     */
    public Claims parseJWT(String token) throws UnsupportedEncodingException {
        System.out.println(key);
        System.out.println(token);
        System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject());
        System.out.println("");
        System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().get("userId"));
        User user = userModel.


        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().get("okdok");
    }

}
