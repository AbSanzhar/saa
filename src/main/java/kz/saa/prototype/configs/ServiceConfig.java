package kz.saa.prototype.configs;

import kz.saa.prototype.services.impl.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    @Qualifier("defaultUserService")
    public DefaultUserService userService() {
        return new DefaultUserService();
    }

    @Bean
    @Qualifier("defaultAuthenticationService")
    public DefaultAuthenticationService authenticationService() {
        return new DefaultAuthenticationService();
    }

    @Bean
    @Qualifier("defaultDepartmentService")
    public DefaultDepartmentService departmentService() {
        return new DefaultDepartmentService();
    }

    @Bean
    @Qualifier("defaultUsersDeptsService")
    public DefaultUsersDeptsService usersDeptsService() { return new DefaultUsersDeptsService(); }

    @Bean
    @Qualifier("defaultDissovetService")
    public DefaultDissovetService dissovetService() {
        return new DefaultDissovetService();
    }
}
