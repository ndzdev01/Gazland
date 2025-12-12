package com.ndz.gazland.service;


import com.ndz.gazland.config.PasswordEncoder;
import com.ndz.gazland.dto.UserResponseDTO;
import com.ndz.gazland.mapper.UserMapper;
import com.ndz.gazland.models.GasBottle;
import com.ndz.gazland.models.User;
import com.ndz.gazland.repository.GasBottleRepository;
import com.ndz.gazland.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public UserService() {
    }


    //CREATE
    public ResponseEntity createUser(User user)
    {
        log.info("CREATING NEW USER NAMED {} ", user.getFirstname() +" "+user.getLastname());
        user.setPassword(passwordEncoder.encoder(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
//        return UserMapper.mapToUserResponseDTO(userRepository.save(user));

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
    public ResponseEntity readUserByID(int id)
    {
        log.info("SEARCHING A USER BY HIS ID {}", id);
        Map<String,Object> response = new HashMap<>();
        if(!userRepository.existsById(id))
        {
            response.put("message", "USER NOT FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id).get());
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
