package com.example.demo.controller;

import com.example.demo.model.Function;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserRestController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;
    
    @Secured("ROLE_USER")
    @GetMapping("/user/getAll")
    @ResponseBody
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
    
    @RequestMapping(value = "/user/getCurrentFunction", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody String token) {
        return ResponseEntity.ok(userService.getCurrentFunction(token));

    }
    
    @Secured("ROLE_ROLE")
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));

    }

    @Secured("ROLE_ROLE")
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody User user) {
        return ResponseEntity.ok(userService.delete(user.getUserID()));

    }
    
    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    public ResponseEntity chagePass(@RequestBody User user, @RequestHeader("authorization") String token) {

        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (token != null) {
            username = jwtService.getUsernameFromToken(token);
        }
        if (!user.getUserName().equals(username)) {
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        }

        if (userService.chagePass(user)) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUserName());
                User us = userService.loadUserByUsername(user.getUserName());
                us.setToken(result);
                httpStatus = HttpStatus.OK;
                return ResponseEntity.ok(us);
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<String>(result, httpStatus);
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }

    @PostMapping("/checkPass")
    public ResponseEntity checkPass(@RequestBody User user) {
        HttpStatus httpStatus = null;
        try {
            return ResponseEntity.ok(userService.checkLogin(user));

        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Boolean>(false, httpStatus);
    }
}
