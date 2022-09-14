package br.com.brn.feedrapi.adapters.inbound.controllers;

import br.com.brn.feedrapi.application.domain.models.User;
import br.com.brn.feedrapi.application.exception.DuplicateUserException;
import br.com.brn.feedrapi.application.ports.services.UserServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServicePort userService;

    public UserController(UserServicePort userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
       User user = userService.findById(id);
        if (!Objects.isNull(user)) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping()
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (DuplicateUserException duplicateUserException) {
            return new ResponseEntity<>(duplicateUserException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
