package kz.saa.prototype.services.impl;

import kz.saa.prototype.models.entities.LoginEntity;
import kz.saa.prototype.models.entities.RolesEntity;
import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.entities.UsersDeptsEntity;
import kz.saa.prototype.models.pojos.User;
import kz.saa.prototype.models.pojos.UserDis;
import kz.saa.prototype.models.pojos.UsersDepts;
import kz.saa.prototype.models.pojos.impl.DefaultUser;
import kz.saa.prototype.models.pojos.impl.DefaultUserDis;
import kz.saa.prototype.models.pojos.impl.DefaultUsersDepts;
import kz.saa.prototype.models.pojos.json.UserJson;
import kz.saa.prototype.models.pojos.json.UsersDeptsJson;
import kz.saa.prototype.repositories.LoginRepository;
import kz.saa.prototype.repositories.RolesRepository;
import kz.saa.prototype.repositories.UserRepository;
import kz.saa.prototype.repositories.UsersDeptsRepository;
import kz.saa.prototype.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class DefaultUserService implements UserService {

    private UserRepository userRepository;
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsersDeptsRepository usersDeptsRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setLoginRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }





    @Override
    public List<User> showAllUsers() {
        List<LoginEntity> entities = this.loginRepository.findByUserEntityUserIdIsNotNull();

        return entities.stream().map(userEntity -> DefaultUser.builder()
            .loginId(userEntity.getLoginId())
            .username(userEntity.getUsername())
            .password(userEntity.getPassword())
            .userId(userEntity.getUserEntity().getUserId())
            .firstName(userEntity.getUserEntity().getFirstName())
            .lastName(userEntity.getUserEntity().getLastName())
            .email(userEntity.getUserEntity().getEmail())
            .description(userEntity.getUserEntity().getDescription())
            .roles(userEntity.getUserEntity().getRoles())
            .usersDepts(showUsersDepts(userEntity.getUserEntity().getUserId()))
            .build()).collect(Collectors.toList());
    }

    @Override
    public List<User> showAll() {
        List<UserEntity> userEntities = this.userRepository.findAll();

        return userEntities.stream().map(userEntity -> DefaultUser.builder()
            .loginId(userEntity.getLoginEntity().getLoginId())
            .username(userEntity.getLoginEntity().getUsername())
            .password(userEntity.getLoginEntity().getPassword())
            .userId(userEntity.getUserId())
            .firstName(userEntity.getFirstName())
            .lastName(userEntity.getLastName())
            .email(userEntity.getEmail())
            .description(userEntity.getDescription())
            .roles(userEntity.getRoles())
            .usersDepts(showUsersDepts(userEntity.getUserId()))
            .build()).collect(Collectors.toList());
    }

    @Override
    public DefaultUser showUser(Long userId) throws Exception {
        UserEntity userEntity = this.userRepository.findById(userId).orElse(null);

        if (userEntity == null) {
            throw new Exception("User id not found");
        }
            return DefaultUser.builder()
                    .userId(userEntity.getUserId())
                    .loginId(userEntity.getLoginEntity().getLoginId())
                    .username(userEntity.getLoginEntity().getUsername())
                    .password(userEntity.getLoginEntity().getPassword())
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .email(userEntity.getEmail())
                    .description(userEntity.getDescription())
                    .roles(userEntity.getRoles())
                    .usersDepts(showUsersDepts(userId))
                    .build();


    }

    @Override
    public List<UserDis> showForDis() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        return userEntities.stream().map(userEntity ->
                DefaultUserDis.builder()
                .userId(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<RolesEntity> showRoles(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElse(null);
        List<RolesEntity> roles = userEntity.getRoles();
        return roles;

    }

    @Override
    public boolean exists(String username) {
        LoginEntity loginEntity = this.loginRepository.findByUsername(username).orElse(null);
        return loginEntity != null;
    }

    @Override
    public String addUserJson(UserJson userJson) {
        if (exists(userJson.getUsername())) {
            return "This username isn't available";
        }
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(userJson.getUsername());
        loginEntity.setPassword(passwordEncoder.encode(userJson.getPassword()));

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userJson.getFirstName());
        userEntity.setLastName(userJson.getLastName());
        userEntity.setEmail(userJson.getEmail());
        userEntity.setDescription(userJson.getDescription());
        userEntity.setRoles(userJson.getRoles());
        userEntity.setOwn(true);

        this.userRepository.save(userEntity);
        loginEntity.setUserEntity(userEntity);

        this.loginRepository.save(loginEntity);

        log.info("Added new user, username: {} . {}", userJson.getUsername(), new Date());
        return "User added!";
    }

    @Override
    public String updateUser(Long userId, UserJson userJson) {
        UserEntity userEntity = this.userRepository.findById(userId).orElse(null);
        LoginEntity loginEntity = this.loginRepository.findById(userEntity.getLoginEntity().getLoginId()).orElse(null);
        if (Objects.nonNull(userEntity)) {
            if (userJson.getFirstName() != null)
                userEntity.setFirstName(userJson.getFirstName());
            if (userJson.getLastName() != null)
                userEntity.setLastName(userJson.getLastName());
            if (userJson.getEmail() != null)
                userEntity.setEmail(userJson.getEmail());
            if (userJson.getDescription() != null)
                userEntity.setDescription(userJson.getDescription());
            if (userJson.getUsername() != null)
                loginEntity.setUsername(userJson.getUsername());
            if (userJson.getPassword() != null)
                loginEntity.setPassword(userJson.getPassword());
            if (userJson.getRoles() != null)
                loginEntity.getUserEntity().setRoles(userJson.getRoles());
            this.loginRepository.save(loginEntity);
            this.userRepository.save(userEntity);
            log.info("Updated: user with ID: {} / {}", userId, new Date());
            return "Updated";
        }
        else {
            return "Oops! Something goes wrong";
        }
    }

    @Override
    public String deleteUser(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElse(null);
        LoginEntity loginEntity = this.loginRepository.findById(userEntity.getLoginEntity().getLoginId()).orElse(null);
        this.loginRepository.deleteById(loginEntity.getLoginId());
        this.userRepository.deleteById(userEntity.getUserId());
        log.info("User with ID: {} deleted / {}", userId, new Date());
        return "Deleted successfully!";
    }











    private List<UsersDepts> showUsersDepts(Long userId) {
        List<UsersDeptsEntity> usersDeptsEntities = this.usersDeptsRepository.selectByUserId(userId);
        return usersDeptsEntities.stream().map(usersDeptsEntity -> DefaultUsersDepts.builder()
                .userId(usersDeptsEntity.getPrimaryKey().getUserEntity().getUserId())
                .deptId(usersDeptsEntity.getPrimaryKey().getDepartmentsEntity().getDeptId())
                .userType(usersDeptsEntity.getUserType())
                .deptName(usersDeptsEntity.getPrimaryKey().getDepartmentsEntity().getDeptName())
                .announcement(usersDeptsEntity.getPrimaryKey().getDepartmentsEntity().getAnnouncement())
                .build()).collect(Collectors.toList());
    }
}
