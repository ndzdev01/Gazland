package com.ndz.gazland.service;


import com.ndz.gazland.dto.UserResponseDTO;
import com.ndz.gazland.mapper.UserMapper;
import com.ndz.gazland.models.User;
import com.ndz.gazland.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    UserRepository userRepository;

    public UserService() {
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE
    public UserResponseDTO createUser(User user)
    {
        log.info("CREATING NEW USER NAMED {} ", user.getFirstname() +" "+user.getLastname());
        return UserMapper.mapToUserResponseDTO(userRepository.save(user));
    }

    //READ
    public List<UserResponseDTO> readUsers()
    {
        log.info("READING ALL USERS");
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserResponseDTO)
                .collect(Collectors.toList());

    }

    //READ BY ID
    public Optional<UserResponseDTO> readUserByID(int id)
    {
        log.info("SEARCHING A USER BY HIS ID {}", id);
        return Optional.of(UserMapper.mapToUserResponseDTO(userRepository.findById(id).orElseThrow()));
    }

    //UPDATE
    public UserResponseDTO updateUser(int id, User user)
    {
        User userFound = userRepository.findById(id).get();
        userFound.setEmailAdress(user.getEmailAdress());
        userFound.setFirstname(user.getFirstname());
        userFound.setLastname(user.getLastname());
        userFound.setPassword(user.getPassword());
        userFound.setPhoneNumber(user.getPhoneNumber());
        userFound.setRole(user.getRole());
        log.info("UPDATING USER ", user.getFirstname()+" "+user.getLastname());
        return UserMapper.mapToUserResponseDTO(userRepository.save(userFound));
    }

    //DELETE
    public void deleteUser(int id)
    {
        User userFound = userRepository.findById(id).get();
        log.info("DELETING USER ", userFound.getFirstname() +" "+userFound.getLastname());
        userRepository.delete(userFound);
    }

}
