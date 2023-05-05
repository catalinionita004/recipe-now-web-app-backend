package com.example.recipenowwebappbackend.mappers;


import com.example.recipenowwebappbackend.dtos.UserDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper implements BaseMapper<UserDto, User> {
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
        return null;
    }

    @Override
    public List<UserDto> modelsToDtos(Set<User> users) {
        return users.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<User> dtosToModels(List<UserDto> userDtos) {
        return null;
    }
}
