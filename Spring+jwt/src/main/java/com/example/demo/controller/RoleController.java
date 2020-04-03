package com.example.demo.controller;

import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Secured({"ROLE_ROLE", "ROLE_USER"})
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @Secured("ROLE_ROLE")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.save(role));

    }
    @Secured("ROLE_ROLE")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.delete(role));

    }

}
