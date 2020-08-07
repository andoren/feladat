package helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.Role;
import core.model.User;

import javax.ejb.Stateless;

@Stateless
public class JWTHelper implements IJWTHelper{
    static Algorithm algorithmHS = Algorithm.HMAC256("secret");
    public  String generateJWT(User user){
        try {

            String token = JWT.create()
                    .withClaim("userId",user.getId())
                    .withClaim("username",user.getUsername())
                    .withClaim("realname",user.getRealname())
                    .withClaim("email",user.getEmail())
                    .withClaim("role",user.getRole().name())
                    .sign(algorithmHS);
            return token;
        } catch (JWTCreationException exception){
            System.out.println("JWT Generálási hiba");
            return "";
        }
    }
    public User decodeJWT(String token) throws JWTVerificationException, InvalidEmailException, InvalidRealnameException, InvalidUsernameException {
        token = token.split(":")[1] ;
        token = token.trim();
            JWTVerifier verifier = JWT.require(algorithmHS)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        System.out.println(jwt.getClaim("realname").asString());
            User user = new User();
            user.setId(jwt.getClaim("userId").asInt());
            user.setEmail(jwt.getClaim("email").asString());
            user.setRealname(jwt.getClaim("realname").asString());
            user.setRole(Role.valueOf(jwt.getClaim("role").asString()));
            user.setUsername(jwt.getClaim("username").asString());
            return user;
    }
}
