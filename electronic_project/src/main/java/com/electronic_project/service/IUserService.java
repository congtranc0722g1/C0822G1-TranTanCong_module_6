package com.electronic_project.service;

import com.electronic_project.dto.request.UpdateUserForm;
import com.electronic_project.dto.user.UserDto;
import com.electronic_project.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    User findById(int id);

//    void updateUser(UpdateUserForm updateUserForm);

    void changePassword(String password, String username);

    void save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    User userLogin(int id);

    void updateUser(String name, String dateOfBirth, Boolean gender, String phone, String email, String address, Integer id);

    void updateAvatarUser(String avatar, Integer id);
}


