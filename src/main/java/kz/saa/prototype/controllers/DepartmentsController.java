package kz.saa.prototype.controllers;

import kz.saa.prototype.models.pojos.json.DepartmentJson;
import kz.saa.prototype.models.pojos.json.UsersDeptsJson;
import kz.saa.prototype.services.DepartmentsService;
import kz.saa.prototype.services.UsersDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/depts")
public class DepartmentsController {

    @Autowired
    private DepartmentsService departmentsService;

    @Autowired
    private UsersDeptsService usersDeptsService;

    @GetMapping("/{deptId}")
    public ResponseEntity<?> showDept(@PathVariable Long deptId) throws Exception{
        return ResponseEntity.ok(this.departmentsService.showDeptUsers(deptId));
    }

    @PostMapping("/add")
    public String addUsers(@RequestBody DepartmentJson departmentJson) {
        return this.departmentsService.addDept(departmentJson);
    }

    @PostMapping("/addUsers")
    public ResponseEntity<?> addUsersDepts(@RequestBody UsersDeptsJson usersDeptsJson) {
        this.usersDeptsService.addUsersDepts(usersDeptsJson);
        return ResponseEntity.ok("Dept added!!!!!!");
    }

    @PostMapping("/addA/{deptId}")
    public ResponseEntity<?> addAnnouncement(@PathVariable Long deptId, @RequestParam String announcement) {
        this.departmentsService.addAnnouncement(deptId, announcement);
        return ResponseEntity.ok("Added announcement");
    }

//    @PatchMapping("/update")
//    public ResponseEntity<?> updateUsersDepts(@RequestBody UsersDeptsJson usersDeptsJson) {
//        this.usersDeptsService.updateUsersDepts(usersDeptsJson);
//        return ResponseEntity.ok("Updated");
//    }
}
