package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable("id") Long id) {
        return this.userService.findById(id);
    }

    @GetMapping
    public List<User> findByAccelerationNameOrFindByCompanyId(
            @RequestParam(required = false) String accelerationName,
            @RequestParam(required = false) Long companyId) {
        if (accelerationName != null) {
            return this.userService.findByAccelerationName(accelerationName);
        }
        return this.userService.findByCompanyId(companyId);
    }

}
