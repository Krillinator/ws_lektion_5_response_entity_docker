package com.krillinator.ws_lektion_5_response_entity_docker.controller;

import com.krillinator.ws_lektion_5_response_entity_docker.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final List<User> userList = new ArrayList<>(
            List.of(
                    new User(0, "Benny", "123"),
                    new User(1, "Frida", "456")
            )
    );

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok(userList);
    }

    @PostMapping("/{username}")
    public ResponseEntity<User> postUser(@PathVariable("username") String username ) {

        User user = new User(userList.size(), username, "");
        userList.add(user);

        // TODO - .created() HTTP URI.create()
        // return ResponseEntity.created(URI.create("localhost:8080/user")).body(user);
        return ResponseEntity.status(201).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {

        User userToBeDeleted;

        // Optional - could be an alternative solution
        // If everything goes well
        // TODO - When will this code stop working as intended?
        if (id <= userList.size() && id >= 0) {
            userToBeDeleted = userList.get(id);
            userList.remove(id);

            return ResponseEntity.ok(userToBeDeleted);
        }

        return ResponseEntity.status(204).build();
    }

}
