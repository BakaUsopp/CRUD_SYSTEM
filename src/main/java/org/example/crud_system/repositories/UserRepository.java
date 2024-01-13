package org.example.crud_system.repositories;

import lombok.NonNull;
import org.example.crud_system.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    Optional<User> findByEmail(String email);


    public Optional<User> findById(@NonNull Long Id) ;

    public Optional<User> findByEmail(@NonNull String email) ;



//    public User findByEmail(String email) ;
//
//    public User findByPassword(String password) ;
//
//    public User findByRole(String role) ;



}
