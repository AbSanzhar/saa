package kz.saa.prototype.services.impl;

import kz.saa.prototype.models.entities.ExternalUserEntity;
import kz.saa.prototype.models.entities.LoginEntity;
import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.pojos.impl.DefaultExternalUser;
import kz.saa.prototype.models.pojos.json.ExternalUserJson;
import kz.saa.prototype.repositories.ExternalUserRepository;
import kz.saa.prototype.repositories.LoginRepository;
import kz.saa.prototype.repositories.RolesRepository;
import kz.saa.prototype.repositories.UserRepository;
import kz.saa.prototype.services.ExternalUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Slf4j
public class DefaultExternalUserService implements ExternalUserService {

    @Autowired
    private ExternalUserRepository externalUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public DefaultExternalUser showExternalUser(Long userId) throws Exception {
        ExternalUserEntity externalUserEntity = this.externalUserRepository.findByUserId(userId).orElse(null);
        UserEntity userEntity = this.userRepository.findById(userId).orElse(null);
        if (externalUserEntity == null)
            throw new Exception("External user not found!");
        return DefaultExternalUser.builder()
                .exId(externalUserEntity.getExId())
                .iin(externalUserEntity.getIin())
                .placeOfWork(externalUserEntity.getPlaceOfWork())
                .userId(externalUserEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getLoginEntity().getUsername())
                .build();
    }

    @Override
    public String addExternalUser(ExternalUserJson externalUserJson) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(externalUserJson.getUsername());
        loginEntity.setPassword(passwordEncoder.encode(externalUserJson.getPassword()));

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(externalUserJson.getFirstName());
        userEntity.setLastName(externalUserJson.getLastName());
        userEntity.setEmail(externalUserJson.getEmail());
        userEntity.setDescription(externalUserJson.getDescription());
        userEntity.setRoles(externalUserJson.getRoles());
        userEntity.setOwn(false);

        this.userRepository.save(userEntity);
        loginEntity.setUserEntity(userEntity);
        this.loginRepository.save(loginEntity);

        ExternalUserEntity externalUserEntity = new ExternalUserEntity();
        externalUserEntity.setIin(externalUserJson.getIin());
        externalUserEntity.setPlaceOfWork(externalUserJson.getPlaceOfWork());
        externalUserEntity.setUserId(userEntity.getUserId());
        this.externalUserRepository.save(externalUserEntity);

        log.info("External user added. {}", new Date());
        return "Added";
    }
}
