package com.example.recipenowwebappbackend.services;


import com.example.recipenowwebappbackend.dtos.UserDto;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.exceptions.UserAlreadyExistException;
import com.example.recipenowwebappbackend.exceptions.UserException;
import com.example.recipenowwebappbackend.exceptions.UserNotFoundException;
import com.example.recipenowwebappbackend.mappers.UserMapper;
import com.example.recipenowwebappbackend.models.auth.AuthenticationResponse;
import com.example.recipenowwebappbackend.models.auth.RegistrationRequest;
import com.example.recipenowwebappbackend.repositories.UserRepository;
import com.example.recipenowwebappbackend.models.*;
import com.example.recipenowwebappbackend.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    private User userCurrent;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found for " + username));
        userCurrent=user;
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public AuthenticationResponse registerUser(User user) throws UserAlreadyExistException {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            //@TODO ACTUALIZARE
            userRepository.save(user);
        } catch (Exception e) {
            if (e.getMessage().contains("recipe_user_username_key")) {
                throw new UserAlreadyExistException("Username already exists");
            } else if (e.getMessage().contains("recipe_user_email_key")) {
                throw new UserAlreadyExistException("Email already exists");
            } else {
                throw new UserAlreadyExistException("User already exists");
            }
        }

        return new AuthenticationResponse(jwtTokenUtil.generateToken(user));
    }

    public AuthenticationResponse register(RegistrationRequest request) throws UserAlreadyExistException {
        if (!Objects.equals(request.password(), request.confirmPassword()))
            throw new UserException("Confirmed password does not match");

        return registerUser(
                new User(
                        request.firstName(),
                        request.lastName(),
                        request.username(),
                        request.email(),
                        request.password(),
                        Role.VIEWER,
                        0
                )
        );
    }

    public UserDto findByUsername(String username) {
        return userMapper.modelToDto(userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username)));
    }

//    public void enableUser(String username) {
//        userRepository.findByUsername(username).orElseThrow(() ->
//                new UsernameNotFoundException("User not found for " + username));
//        userRepository.enableUser(username);
//    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

//    public void updateUserRole(String username, String role) {
//        userRepository.updateUserRole(username, Role.valueOf(role));
//    }

    public UserDto getCurrentUser() {
        return userMapper.modelToDto(userCurrent);
    }

    public UserDto findById(Long userId) {
        return userMapper.modelToDto(userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id " + userId)));
    }

//    public List<User> getOrderByPage(Integer page) {
//        return userRepository.findAll(PageRequest.of(page, Integer.parseInt(configurationRepository.findConfigurationByField("configuration.number-of-users-per-page").get().getValue())))
//                .stream()
//                .collect(Collectors.toList());
//    }
//
//    public SetupPaginator getSetupPaginator() {
//        return new SetupPaginator((int) userRepository.count(), Integer.parseInt(configurationRepository.findConfigurationByField("configuration.number-of-users-per-page").get().getValue()));
//    }
}
