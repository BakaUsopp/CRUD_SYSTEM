package org.example.crud_system.services;


import org.example.crud_system.entities.User;
import org.example.crud_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByEmailAndPassword(String email, String password) throws UsernameNotFoundException {
        User user= userRepository.findByEmailAndPassword(email, password);

        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;


    }
    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
