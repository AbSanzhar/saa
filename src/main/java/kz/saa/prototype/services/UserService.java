package kz.saa.prototype.services;

import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.pojos.User;
import kz.saa.prototype.models.pojos.UserDis;
import kz.saa.prototype.models.pojos.impl.DefaultUser;
import kz.saa.prototype.models.pojos.json.UserJson;
import java.util.List;

public interface UserService {

    List<User> showAllUsers();

    List<User> showAll();

    List<UserDis> showForDis();

    DefaultUser showUser(Long userId) throws Exception;

    List<RolesEntity> showRoles(Long userId);

    String addUserJson(UserJson userJson);

    boolean exists(String username);

    String updateUser(Long userId, UserJson userJson);

    String deleteUser(Long userId);

}
