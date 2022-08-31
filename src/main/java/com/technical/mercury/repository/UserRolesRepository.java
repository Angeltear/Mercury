package com.technical.mercury.repository;

import com.technical.mercury.model.Users.User;
import com.technical.mercury.model.Users.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    List<UserRoles> findUserRolesByUserAndUserRole(User user, String role);

    List<UserRoles> findUserRolesByUser(User user);
    @Transactional
    void deleteAllByUser(User user);

}