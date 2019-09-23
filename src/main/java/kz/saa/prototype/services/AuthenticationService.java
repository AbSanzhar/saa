package kz.saa.prototype.services;

import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.pojos.Login;
import kz.saa.prototype.models.pojos.impl.DefaultAuthentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AuthenticationService {

    String generateToken(Long userId, String username, List<RolesEntity> roles);

    String validateToken(String token);

    boolean exists(String username);

    Login loginObject(DefaultAuthentication defaultAuthentication);

    List<String> getRoles(List<RolesEntity> roles);

    String getRoleByToken(String token);

    String resolveToken(HttpServletRequest request);

}