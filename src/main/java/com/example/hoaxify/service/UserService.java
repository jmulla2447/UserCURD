package com.example.hoaxify.service;

import com.example.hoaxify.dto.UserDto;
import com.example.hoaxify.mapping.UserMapper;
import com.example.hoaxify.model.User;
import com.example.hoaxify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder =  passwordEncoder;
    }

    public UserDto createUser(UserDto userDto){
        User user = UserMapper.USER_MAPPER.dtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDto saveUser =  UserMapper.USER_MAPPER.entityToDto(userRepository.save(user));
        saveUser.setPassword(null);
        return saveUser;
    }
}
