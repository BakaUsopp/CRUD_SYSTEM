package org.example.crud_system.repositories;

import org.example.crud_system.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    Optional<User> findByEmail(String email);

    public User findByEmailAndPassword(String email, String password) ;

//    public User findByEmail(String email) ;
//
//    public User findByPassword(String password) ;
//
//    public User findByRole(String role) ;



}
