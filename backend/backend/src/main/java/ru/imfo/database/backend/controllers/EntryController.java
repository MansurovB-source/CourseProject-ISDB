package ru.imfo.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.imfo.database.backend.entities.User;
import ru.imfo.database.backend.services.UserService;

import java.security.Principal;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */

@RestController
public class EntryController {
    private UserService userService;

    @Autowired
    public EntryController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        User user;
        if (principal == null) {
            return new ResponseEntity<>("Wrong username or password, bitch", HttpStatus.UNAUTHORIZED);
        } else {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody User user) {
        Pattern pattern = Pattern.compile(
                "[" +                   //начало списка допустимых символов
                        "а-яА-ЯёЁ" +    //буквы русского алфавита
                        "\\p{Punct}" +  //знаки пунктуации
                        "]" +                   //конец списка допустимых символов
                        "*");                   //допускается наличие указанных символов в любом количестве
        if (pattern.matcher(user.getUsername()).matches()) {
            return new ResponseEntity<>("Unacceptable symbols", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } else {
            if (userService.findByUsername(user.getUsername()) != null) {
                return new ResponseEntity<>(new RuntimeException("User with username " + user.getUsername() + " already exist"), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
