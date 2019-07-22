package test.demo.service;

import java.util.Date;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String USERNAME = "USERNAME";

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private Environment env;

    public String generateToken(String username) {
        String token = null;
        
        try {

            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USERNAME, username);
            builder.expirationTime(generateExpireDate());

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            JWSSigner signer = new MACSigner(generateKey());
            signedJWT.sign(signer);
            token = TOKEN_PREFIX + signedJWT.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private JWTClaimsSet getClaimSet(String token) {
        JWTClaimsSet claimsSet = null;
        try {
            token = token.replace(TOKEN_PREFIX, "").trim();
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier jwsVerifier = new MACVerifier(generateKey());

            if (signedJWT.verify(jwsVerifier)) {
                claimsSet = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return claimsSet;
    }

    public String getUsername(String token) {
        String username = null;
        try {
            JWTClaimsSet claim = getClaimSet(token);
            username = claim.getStringClaim(USERNAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    private Date getExpirateDate(String token) {
        Date expirateDate = null;
        try {
            JWTClaimsSet claim = getClaimSet(token);
            expirateDate = claim.getExpirationTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expirateDate;
    }

    private boolean isExpirateToken(String token) {
        Date expireDate = getExpirateDate(token);
        return expireDate.before(new Date());
    }

    public boolean isValidateToken(String token) {

        if (token == null || token.trim().length() == 0) 
            return false;

        String username = getUsername(token);

        if (username == null || username.isEmpty())
            return false;
        
        if (isExpirateToken(token)) 
            return false;

        return true;
    }

    private byte[] generateKey() {
        byte[] secret = new byte[32];
        secret = env.getRequiredProperty("secret_key").getBytes();
        return secret;
    }

    private Date generateExpireDate() {
        return new Date(System.currentTimeMillis() + Integer.parseInt(env.getRequiredProperty("expire_time")));
    }
}