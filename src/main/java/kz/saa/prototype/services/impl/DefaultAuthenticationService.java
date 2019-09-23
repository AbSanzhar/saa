package kz.saa.prototype.services.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import kz.saa.prototype.models.entities.LoginEntity;
import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.pojos.Login;
import kz.saa.prototype.models.pojos.impl.DefaultAuthentication;
import kz.saa.prototype.models.pojos.impl.DefaultLogin;
import kz.saa.prototype.repositories.LoginRepository;
import kz.saa.prototype.repositories.RolesRepository;
import kz.saa.prototype.services.AuthenticationService;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    private LoginRepository loginRepository;

    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private void setPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) { this.passwordEncoder = passwordEncoder;}

    @Value("${jwt.key}")
    private String jwtKeyString;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Override
    public String getRoleByToken(String token) {
        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(new SecretKeySpec(
                            jwtKeyString.getBytes(),
                            SignatureAlgorithm.HS256.getJcaName()
                    ))
                    .parseClaimsJws(token).getBody();
            return (String) claims.get("roles");
        } catch (Exception e) {
            return "Something goes wrong";
        }
    }

    @Override
    public List<String> getRoles(List<RolesEntity> roles) {
        List<String> result = new ArrayList<>();
        roles.forEach(rolesEntity -> {
            result.add(rolesEntity.getRoleName());
        });

        return result;
    }

    @Override
    public boolean exists(String username) {
        LoginEntity loginEntity = this.loginRepository.findByUsername(username).orElse(null);
        return loginEntity != null;
    }


    @Override
    public Login loginObject(DefaultAuthentication defaultAuthentication) {
        LoginEntity loginEntity = this.loginRepository.findByUsernameAndPassword(defaultAuthentication.getUsername(), defaultAuthentication.getPassword());
        Long userId = 0L;
        List<RolesEntity> roles = loginEntity.getUserEntity().getRoles();

        if (loginEntity.getUserEntity() != null) {
            userId = loginEntity.getUserEntity().getUserId();
        }

        return DefaultLogin.builder()
                .loginId(loginEntity.getLoginId())
                .username(loginEntity.getUsername())
                .password(loginEntity.getPassword())
                .roles(roles)
                .userId(userId)
                .build();
    }

    @Override
    public String generateToken(Long userId, String username, List<RolesEntity> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String jwt = Jwts.builder()
                .setId(userId.toString())
                .setSubject(username)
                .claim("roles", getRoles(roles))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(new SecretKeySpec(
                        jwtKeyString.getBytes(),
                        SignatureAlgorithm.HS256.getJcaName()
                )).compact();
        return jwt;
    }

    @Override
    public String validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(new SecretKeySpec(
                            jwtKeyString.getBytes(),
                            SignatureAlgorithm.HS256.getJcaName()
                    ))
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            return "expired";
        } catch (MalformedJwtException e) {
            return "invalid token";
        } catch (SignatureException e) {
            return "invalid token";
        }
        return "OK";
    }

    @Override
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}