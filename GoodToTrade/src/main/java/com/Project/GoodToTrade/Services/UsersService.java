package com.Project.GoodToTrade.Services;

import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Users getUser(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public Users getUserByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, Users updatedUser) {
        return usersRepository.findById(id)
                .map(user -> {
                    if(updatedUser.getUsername() != null){
                        user.setUsername(updatedUser.getUsername());
                    }
                    if(updatedUser.getFullName() != null){
                        user.setFullName(updatedUser.getFullName());
                    }
                    if(updatedUser.getEmail() != null){
                        user.setEmail(updatedUser.getEmail());
                    }
                    if(updatedUser.getPhone() != null){
                        user.setPhone(updatedUser.getPhone());
                    }
                    if(updatedUser.getPassword() != null){
                        user.setPassword(updatedUser.getPassword());
                    }
                    return usersRepository.save(user);
                })
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }
    public void deleteUser(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }
}
