package com.ndz.gazland.mapper;

import com.ndz.gazland.dto.UserRequestDTO;
import com.ndz.gazland.dto.UserResponseDTO;
import com.ndz.gazland.models.User;

public class UserMapper {
    public static UserResponseDTO mapToUserResponseDTO(User user)
    {
        return new UserResponseDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getPhoneNumber(), user.getEmailAdress(), user.getRole());
    }

    public static UserRequestDTO mapToUserRequestDTO(User user)
    {
        return new UserRequestDTO(user.getFirstname(), user.getLastname(), user.getEmailAdress(), user.getPhoneNumber(), user.getPassword(), user.getRole());
    }

}
