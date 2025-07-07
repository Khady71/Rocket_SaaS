package com.example.rocket_saas.association;

import com.example.rocket_saas.user.User;
import com.example.rocket_saas.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssociationController {
    private final AssociationService associationService;

    public AssociationController(UserService userService) {
        this.associationService = associationService;
    }

    @GetMapping
    public List<User> getUsers(){
        return UserService.getAllUsers();
    }
}
