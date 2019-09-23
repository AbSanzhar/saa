package kz.saa.prototype.controllers;

import kz.saa.prototype.models.pojos.json.DissovetJson;
import kz.saa.prototype.models.pojos.json.DissovetMemberJson;
import kz.saa.prototype.services.DissovetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dissovet")
public class DissovetController {

    @Autowired
    private DissovetService dissovetService;

    @GetMapping("/{disId}")
    public ResponseEntity<?> showOne(@PathVariable Long disId) throws Exception{
        return ResponseEntity.ok(this.dissovetService.showDissovet(disId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> showAll() {
        return ResponseEntity.ok(this.dissovetService.showAllDissovets());
    }

    @GetMapping("/member/{userId}")
    public ResponseEntity<?> showByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.dissovetService.showByUser(userId));
    }

    @GetMapping("/secretary/{id}")
    public ResponseEntity<?> showBySecretary(@PathVariable Long id) {
        return ResponseEntity.ok(this.dissovetService.showBySecretary(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDissovet(@RequestBody DissovetJson dissovetJson) {
        return ResponseEntity.ok(this.dissovetService.addDissovet(dissovetJson));
    }

    @PostMapping("/add/member/{disId}")
    public ResponseEntity<?> addMember(@PathVariable Long disId,  @RequestBody DissovetMemberJson dissovetMemberJson) {
        this.dissovetService.addDissovetMember(disId, dissovetMemberJson);
        return ResponseEntity.ok("Added member");
    }

    @PostMapping("/add/memberUser/{disId}")
    public String addUserMember(@PathVariable Long disId,  @RequestBody DissovetMemberJson dissovetMemberJson) {
        return this.dissovetService.addDissovetUserMember(disId, dissovetMemberJson);
    }

    @PatchMapping("/update/{disId}")
    public String updateDissovet(@PathVariable Long disId, @RequestBody DissovetJson dissovetJson) {
        return this.dissovetService.updateDissovet(disId,dissovetJson);
    }

    @PatchMapping("/update/member/{disId}")
    public ResponseEntity<?> updateMember(@PathVariable Long disId, @RequestBody DissovetMemberJson dissovetMemberJson) {
        this.dissovetService.updateDissovetMember(disId, dissovetMemberJson);
        return ResponseEntity.ok("Updated!");
    }

    @DeleteMapping("/delete/{disId}")
    public ResponseEntity<?> deleteDissovet(@PathVariable Long disId) {
        this.dissovetService.deleteDissovet(disId);
        return ResponseEntity.ok("Deleted");
    }

    @DeleteMapping("/delete/member/{memberId}")
    public String deleteMember(@PathVariable Long memberId) {
        return this.dissovetService.deleteDisMember(memberId);
    }

}
