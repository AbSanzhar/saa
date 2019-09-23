package kz.saa.prototype.controllers;

import kz.saa.prototype.models.pojos.json.UserJson;
import kz.saa.prototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> showAllUsers() {
        return ResponseEntity.ok(this.userService.showAll());
    }

    @GetMapping("/dis")
    public ResponseEntity<?> showForDis() {
        return ResponseEntity.ok(this.userService.showForDis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(this.userService.showUser(id));
    }

    @GetMapping("/role/{userId}")
    public ResponseEntity<?> showRole(@PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.showRoles(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserJson userJson) {
        this.userService.addUserJson(userJson);
        return ResponseEntity.ok(userService.showAllUsers());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserJson userJson) {
        this.userService.updateUser(id, userJson);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}
