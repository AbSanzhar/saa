package kz.saa.prototype.services;

import kz.saa.prototype.models.pojos.UsersDepts;
import kz.saa.prototype.models.pojos.impl.DefaultUsersDepts;
import kz.saa.prototype.models.pojos.json.UsersDeptsJson;

import java.util.List;

public interface UsersDeptsService {

    List<UsersDepts> showUsersDepts(Long userId);
    String addUsersDepts(UsersDeptsJson usersDeptsJson);
//    void updateUsersDepts(UsersDeptsJson usersDeptsJson);
}
