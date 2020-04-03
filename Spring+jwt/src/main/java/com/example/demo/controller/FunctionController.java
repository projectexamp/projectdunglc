package com.example.demo.controller;

import com.example.demo.model.Function;
import com.example.demo.service.FunctionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/function")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @Secured({"ROLE_ROLE", "ROLE_FUNCTION"})
    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity getAll() {
        return ResponseEntity.ok(functionService.getAll());
    }

    @Secured("ROLE_FUNCTION")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Function func) {
        return ResponseEntity.ok(functionService.save(func));

    }

    @Secured("ROLE_FUNCTION")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody Function func) {
        return ResponseEntity.ok(functionService.delete(func));

    }
}
