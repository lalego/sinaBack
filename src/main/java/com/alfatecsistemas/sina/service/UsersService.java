package com.alfatecsistemas.sina.service;

import com.alfatecsistemas.sina.dto.UserDto;
import javassist.NotFoundException;

import java.util.List;

public interface UsersService {

    List<UserDto> getUsers();

    UserDto getUser(Integer userId);

    UserDto getLogin(String name, String password);

    UserDto getUserAndProfessional(Integer userId, Integer profId);

    UserDto updateUser(Integer userId, String name, String password) throws NotFoundException;

    UserDto insertUser(Integer userId, String name, String password) throws Exception;

    UserDto deleteUser(Integer userId) throws NotFoundException;
}
