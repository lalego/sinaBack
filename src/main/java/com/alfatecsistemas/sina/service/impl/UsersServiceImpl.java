package com.alfatecsistemas.sina.service.impl;

import com.alfatecsistemas.sina.dao.ProfessionalDao;
import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.domain.SecuUsers;
import com.alfatecsistemas.sina.dto.UserDto;
import com.alfatecsistemas.sina.mapper.ProfessionalMapper;
import com.alfatecsistemas.sina.mapper.UserMapper;
import com.alfatecsistemas.sina.repository.UsersRepository;
import com.alfatecsistemas.sina.service.UsersService;
import com.alfatecsistemas.sina.utils.EncryptUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProfessionalDao professionalDao;

    @Override
    public List<UserDto> getUsers() {
        List<SecuUsers> users = usersRepository.findAll();
        return users.stream().map(UserMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Integer userId) {
        SecuUsers user = usersRepository.findOne(userId);
        return UserMapper.entityToDto(user);
    }

    @Override
    public UserDto getLogin(String name, String password) {
        String passwordSha1 = EncryptUtils.sha1(password);
        SecuUsers user = usersRepository.getLogin(name, passwordSha1);
        return UserMapper.entityToDto(user);
    }

    @Override
    public UserDto getUserAndProfessional(Integer userId, Integer profId) {
        SecuUsers user = usersRepository.getUserAndProfessional(userId, profId);
        UserDto dto = null;
        if (user != null) {
            dto = UserMapper.entityToDto(user);
            dto.setProfessional(ProfessionalMapper.entityToDto(user.getOrmaProfessionals()));
        }
        return dto;
    }

    @Override
    public UserDto updateUser(Integer userId, String name, String password) throws NotFoundException {
        SecuUsers user = usersRepository.findOne(userId);

        if (user != null) {
            String passwordSha1 = EncryptUtils.sha1(password);

            user.setUserLogin(name);
            user.setUserPassword(passwordSha1);
            usersRepository.save(user);
        } else {
            throw new NotFoundException(String.format("The user with name %s not exists", name));
        }

        return UserMapper.entityToDto(user);
    }

    @Override
    public UserDto insertUser(Integer profId, String name, String password) throws Exception {
        OrmaProfessionals professional = professionalDao.findOne(profId);
        SecuUsers user = null;

        if (professional == null) {
            String passwordSha1 = EncryptUtils.sha1(password);

            user = new SecuUsers();
            user.setProfId(profId);
            user.setUserLogin(name);
            user.setUserPassword(passwordSha1);

            usersRepository.save(user);
        } else {
            throw new Exception(String.format("The user with profId %s already exists", profId));
        }

        return UserMapper.entityToDto(user);
    }

    @Override
    public UserDto deleteUser(Integer userId) throws NotFoundException {
        SecuUsers user = usersRepository.findOne(userId);

        if (user != null) {
            usersRepository.delete(user);
        } else {
            throw new NotFoundException(String.format("The user with id %s not exists", userId));
        }

        return UserMapper.entityToDto(user);
    }
}
