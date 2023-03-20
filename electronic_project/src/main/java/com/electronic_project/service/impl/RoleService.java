package com.electronic_project.service.impl;

import com.electronic_project.model.user.Role;
import com.electronic_project.repository.IRoleRepository;
import com.electronic_project.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    /**
     * Created by: CuongVV
     * Date created: 28/2/2023
     * Function: get role admin
     *
     * @param:none
     **/
    @Override
    public Optional<Role> roleAdmin() {
        return iRoleRepository.roleAdmin();
    }

    /**
     * Created by: CuongVV
     * Date created: 28/2/2023
     * Function: get role customer
     *
     * @param:none
     **/
    @Override
    public Optional<Role> roleCustomer() {
        return iRoleRepository.roleCustomer();
    }

    /**
     * Created by: CuongVV
     * Date created: 28/2/2023
     * Function: get role employee
     *
     * @param:none
     **/
    @Override
    public Optional<Role> roleEmployee() {
        return iRoleRepository.roleEmployee();
    }
}
