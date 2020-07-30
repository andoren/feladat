package helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import core.model.Role;
import core.model.User;

public class JWTHelper {
    Algorithm algorithmHS = Algorithm.HMAC256("secret");
    public String generateJWT(User user){
        try {

            String token = JWT.create()
                    .withClaim("userId",user.getId())
                    .withClaim("username",user.getUsername())
                    .withClaim("realname",user.getRealname())
                    .withClaim("email",user.getEmail())
                    .withClaim("role",user.getRole().name())
                    .withIssuer("auth0")
                    .sign(algorithmHS);
            return token;
        } catch (JWTCreationException exception){
            System.out.println("JWT Generálási hiba");
            return "";
        }
    }
    public User decodeJWT(String token){
        try {

            JWTVerifier verifier = JWT.require(algorithmHS)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            User user = new User();
            user.setId(jwt.getClaim("userId").asInt());
            user.setRole(Role.valueOf(jwt.getClaim("role").toString()));
            return user;
        } catch (JWTVerificationException exception){
            return new User();
        }

    }
}
