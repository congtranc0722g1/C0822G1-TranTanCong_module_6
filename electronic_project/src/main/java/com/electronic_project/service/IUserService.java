package com.electronic_project.service;

import com.electronic_project.dto.request.UpdateUserForm;
import com.electronic_project.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {

   List<User> findAll();
    Optional<User> findByUsername(String username);


    void updateUser(UpdateUserForm updateUserForm);

    void changePassword(String password,String username);

    void save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    User userLogin(int id);
}


