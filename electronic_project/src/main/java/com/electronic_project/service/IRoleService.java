package com.electronic_project.service;

import com.electronic_project.model.user.Role;

import java.util.Optional;

public interface IRoleService {
    /**
     * Created by: CuongVV
     * Date created: 28/2/2023
     * Function: get role admin
     * @param:none
     **/
    Optional<Role> roleAdmin();
    /**
     * Created by: CuongVV
     * Date created: 28/2/2023
     * Function: get role customer
     * @param:none
     **/
    Optional<Role> roleCustomer();
    /**
     * Created by: CuongVV
     * Date created: 28/2/2023
     * Function: get role employee
     * @param:none
     **/
    Optional<Role> roleEmployee();
}
