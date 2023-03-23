package com.electronic_project.service.impl;

import com.electronic_project.dto.request.UpdateUserForm;
import com.electronic_project.model.user.Role;
import com.electronic_project.model.user.User;
import com.electronic_project.repository.IUserRepository;
import com.electronic_project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public Optional<User> findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return iUserRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(UpdateUserForm updateUserForm) {
        iUserRepository.updateUser(updateUserForm.getName(),
                updateUserForm.getPhoneNumber(),
                updateUserForm.getEmail(),
                updateUserForm.getAddress(),
                updateUserForm.getAge(),
                updateUserForm.getGender(),
                updateUserForm.getDateOfBirth(),
                updateUserForm.getAvatar(),
                updateUserForm.getUsername());
    }

    @Override
    public void changePassword(String password, String username) {
        iUserRepository.changePassword(password,username);
    }



    @Override
    public void save(User user) {
        iUserRepository.save(user.getName(),user.getUsername(), user.getEmail(), user.getPassword());
        User user1 = iUserRepository.findByUsername(user.getUsername()).orElse(null);
        for (Role x: user.getRoles()) {
            assert user1 != null;
            iUserRepository.insertRole(user1.getId(), x.getId());
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        for (int i = 0; i < iUserRepository.getAllUser().size(); i++) {
            if (iUserRepository.getAllUser().get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean existsByEmail(String email) {
        for (int i = 0; i < iUserRepository.getAllUser().size(); i++) {
            if (iUserRepository.getAllUser().get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User userLogin(int id) {
        return iUserRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return iUserRepository.findAll();
    }



}

