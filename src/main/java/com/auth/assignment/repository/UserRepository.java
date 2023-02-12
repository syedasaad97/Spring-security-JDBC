package com.auth.assignment.repository;

import com.auth.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select p from User p where p.email = :email")
    Optional<User> findByEmail(@Param(value = "email") String email);

    Optional<User> findByEmailAndIsActiveTrue(@Param(value = "email") String email);


}
