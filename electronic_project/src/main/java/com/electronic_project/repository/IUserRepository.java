package com.electronic_project.repository;

import com.electronic_project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {


    @Query(value = "select * from user where username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);


//    @Modifying
//    @Query(value = "update user set name = :name,phone_number = :phone_number,email = :email," +
//            " address = :address,age = :age,gender = :gender,date_of_birth = :date_of_birth,avatar = :avatar" +
//            " where username = :username  ", nativeQuery = true)
//    void updateUser(@Param("name") String name, @Param("phone_number") String phoneNumber, @Param("email") String email
//            , @Param("address") String address, @Param("age") Integer age, @Param("gender") Boolean gender
//            , @Param("date_of_birth") String dateOfBirth, @Param("avatar") String avatar, @Param("username") String username);


    @Modifying
    @Query(value = "update user set password = :password where username = :username",nativeQuery = true)
    void changePassword(@Param("password") String password,@Param("username") String username);

    @Query(value = "select * from user", nativeQuery = true)
    List<User> getAllUser();

    @Modifying
    @Query(value = "insert into user (name,username,email,password) values (:name,:username,:email,:password)", nativeQuery = true)
    void save(@Param("name") String name, @Param("username") String username, @Param("email") String email, @Param("password") String password);

    @Modifying
    @Query(value = "insert into user_roles (user_id,roles_id) values (:user,:role)", nativeQuery = true)
    void insertRole(@Param("user") int userId, @Param("role") int roleId);

    @Query(value = "select * from user where username = :username",nativeQuery = true)
    User userLogin(@Param("username") String username);

    @Query(value = "select * from user join user_roles on user.id = user_roles.user_id join role r on user_roles.roles_id = r.id where r.name = 'ROLE_CUSTOMER'", nativeQuery = true)
    List<User> findAllCustomer();

    @Query(value = "select * from user join user_roles on user.id = user_roles.user_id join role r on user_roles.roles_id = r.id where r.name = 'ROLE_EMPLOYEE'", nativeQuery = true)
    List<User> findAllEmployee();

    @Query(value = "select * from user join user_roles on user.id = user_roles.user_id join role r on user_roles.roles_id = r.id where r.name = 'ROLE_ADMIN'", nativeQuery = true)
    List<User> findAllAdmin();

    @Modifying
    @Transactional
    @Query(value = "update `user` set name = :name, date_of_birth = :dateOfBirth, gender = :gender, phone = :phone, email = :email, address = :address where id = :id and flag_delete = true", nativeQuery = true)
    void updateUser(@Param("name") String name, @Param("dateOfBirth") String dateOfBirth, @Param("gender") Boolean gender, @Param("phone") String phone, @Param("email") String email, @Param("address") String address, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "update `user` set avatar = :avatar where id = :id and flag_delete = true", nativeQuery = true)
    void updateAvatarUser(@Param("avatar") String avatar, @Param("id") Integer id);
}
