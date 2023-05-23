package com.Project.GoodToTrade.Services;

import com.Project.GoodToTrade.Models.Role;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;


    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, Users updatedUser) {
        return usersRepository.findById(id)
                .map(user -> {
                    if (updatedUser.getUsername() != null) {
                        user.setUsername(updatedUser.getUsername());
                    }
                    if (updatedUser.getFullName() != null) {
                        user.setFullName(updatedUser.getFullName());
                    }
                    if (updatedUser.getEmail() != null) {
                        user.setEmail(updatedUser.getEmail());
                    }
                    if (updatedUser.getPhone() != null) {
                        user.setPhone(updatedUser.getPhone());
                    }
                    if (updatedUser.getPassword() != null) {
                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
