package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public void register(User user){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String hasPassword = bCrypt.encode(user.getPassword());
        user.setRole("USER");
        user.setPassword(hasPassword);
        authRepository.save(user);
    }

    public List<User> getAllUser(){
        return authRepository.findAll();
    }

    public void updateUser(Integer id,User user){
        User oldUser = authRepository.findUserById(id);

        if(oldUser == null){
            throw new ApiException("User not found");
        }

        oldUser.setUsername(user.getUsername());
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String hasPassword = bCrypt.encode(user.getPassword());
        oldUser.setPassword(hasPassword);
        authRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = authRepository.findUserById(id);

        if(user == null){
            throw new ApiException("User not found");
        }

        authRepository.delete(user);
    }
}
