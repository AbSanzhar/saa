package kz.saa.prototype.controllers;

import kz.saa.prototype.models.entities.UserEntity;
import kz.saa.prototype.models.pojos.impl.DefaultAuthentication;
import kz.saa.prototype.repositories.UserRepository;
import kz.saa.prototype.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;
    @Autowired(required = false)
    @Qualifier("defaultAuthenticationService")
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody DefaultAuthentication defaultAuthentication) {
//        if (!this.authenticationService.pasCorrect(defaultAuthentication.getUsername(), defaultAuthentication.getPassword()))
//            return ResponseEntity.ok("Incorrect username or password");
        String username = defaultAuthentication.getUsername();
        UserEntity userEntity = this.userRepository.findByLoginEntityUsername(defaultAuthentication.getUsername());
        if (!this.authenticationService.exists(defaultAuthentication.getUsername())) {
            return ResponseEntity.ok("Incorrect username");
        }
        if (!this.passwordEncoder.matches(defaultAuthentication.getPassword(), userEntity.getLoginEntity().getPassword()))
        {
            return ResponseEntity.ok("Incorrect password");
        }
        String token = this.authenticationService.generateToken(userEntity.getUserId(), username, userEntity.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/loginObject")
    public ResponseEntity<?> loginObject(@RequestBody DefaultAuthentication defaultAuthentication) {
        UserEntity userEntity = this.userRepository.findByLoginEntityUsername(defaultAuthentication.getUsername());
        if (!this.passwordEncoder.matches(defaultAuthentication.getPassword(), userEntity.getLoginEntity().getPassword()))
            return ResponseEntity.ok("Incorrect password");
        return ResponseEntity.ok(this.authenticationService.loginObject(defaultAuthentication));
    }

    @GetMapping(value = "/validate/{token}")
    public String validate(@PathVariable String token) {
        return this.authenticationService.validateToken(token);
    }
}
