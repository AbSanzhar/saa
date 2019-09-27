package kz.saa.prototype.controllers;

import kz.saa.prototype.models.pojos.json.ExternalUserJson;
import kz.saa.prototype.services.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/levye")
public class ExternalUserController {
    @Autowired
    private ExternalUserService externalUserService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getExternalUser(@PathVariable Long userId) throws Exception{
        return ResponseEntity.ok(this.externalUserService.showExternalUser(userId));
    }

    @PostMapping("/add")
    public String addExUser(@RequestBody ExternalUserJson externalUserJson) {
        return this.externalUserService.addExternalUser(externalUserJson);
    }
}
