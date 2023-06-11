package com.example.recipenowwebappbackend.mappers;


import com.example.recipenowwebappbackend.dtos.UserDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.User;
import com.example.recipenowwebappbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper implements BaseMapper<UserDto, User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto modelToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }

    @Override
    public User dtoToModel(UserDto userDto) {
        if (userDto.getId() != null) {
            Optional<User> userOptional = userRepository.findById(userDto.getId());
            if (userOptional.isPresent())
                return userOptional.get();
        }
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUsername(),
                userDto.getEmail()
        );
    }

    @Override
    public List<UserDto> modelsToDtos(Set<User> users) {
        return users.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public Set<User> dtosToModels(List<UserDto> userDtos) {
        return null;
    }
}
