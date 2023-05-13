package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(usersService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(usersService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        return new ResponseEntity<>(usersService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        Users currentUser = usersService.getUser(id);
        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setFullName(updatedUser.getFullName());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPhone(updatedUser.getPhone());
        currentUser.setPassword(updatedUser.getPassword());
        currentUser.setProducts(updatedUser.getProducts());
        currentUser.setLikes(updatedUser.getLikes());
        return new ResponseEntity<>(usersService.saveUser(currentUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
