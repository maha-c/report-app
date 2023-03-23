package com.revature.reportapp.controller;
import com.revature.reportapp.dto.Login;
import com.revature.reportapp.entity.User;
import com.revature.reportapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        User insertedUser = userService.insert(user);
        return new ResponseEntity<>(insertedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userIdentifier}")
    public ResponseEntity<User> getByIdOrName(@PathVariable("userIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            return ResponseEntity.ok(userService.getById(id));
        } catch (Exception e) {
            try {
                return ResponseEntity.ok(userService.getByUserName(identifier).get(0));
            } catch (IllegalStateException i) {
                return ResponseEntity.status(404).build();
            }
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping
    public ResponseEntity<User> login(@RequestBody Login login) {
        User user = null;
        try {
            user = userService.getByUserName(login.getUserName()).get(0);
        } catch (IndexOutOfBoundsException i) {
            return ResponseEntity.status(404).build();
        }
        if (!user.getPassword().equals(login.getPassword())) {
            return ResponseEntity.status(422).build();
        }
        return ResponseEntity.ok((user));
    }
//    @PatchMapping
//    public User getUser(@RequestBody Login login){
//        return userService.getByNameAndPassword(login);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

