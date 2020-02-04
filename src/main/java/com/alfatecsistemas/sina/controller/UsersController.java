package com.alfatecsistemas.sina.controller;

import com.alfatecsistemas.sina.dto.UserDto;
import com.alfatecsistemas.sina.service.UsersService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getUsers() {
        return usersService.getUsers();
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable Integer userId) {
        return usersService.getUser(userId);
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public UserDto getLogin(@RequestBody UserDto user) {
        return usersService.getLogin(user.getName(), user.getPassword());
    }

    @RequestMapping(path = "/{userId}/professionals/{profId}", method = RequestMethod.GET)
    public UserDto getUserAndProfessional(@PathVariable Integer userId, @PathVariable Integer profId) {
        return usersService.getUserAndProfessional(userId, profId);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto dto) {

        ResponseEntity response = null;
        try {
            UserDto user = usersService.updateUser(userId, dto.getName(), dto.getPassword());
            response = ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            LOGGER.error(e.toString());
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity<UserDto> insertUser(@PathVariable Integer userId, @RequestBody UserDto dto) {
        ResponseEntity response = null;
        try {
            UserDto user = usersService.insertUser(userId, dto.getName(), dto.getPassword());
            response = ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer userId) {
        ResponseEntity response = null;
        try {
            UserDto user = usersService.deleteUser(userId);
            response = ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            LOGGER.error(e.toString());
            response = ResponseEntity.notFound().build();
        }

        return response;
    }
}
