package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.DTOs.RoleToUserDTO;
import com.Project.GoodToTrade.Models.Role;
import com.Project.GoodToTrade.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final UsersService usersService;

    @Autowired
    public RoleController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody Role role) {
        usersService.saveRole(role);
    }

    @PostMapping("/roles/addtouser")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        usersService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }
}
