package com.example.recipenowwebappbackend.services.impl;


import com.example.recipenowwebappbackend.exceptions.UserAlreadyExistException;
import com.example.recipenowwebappbackend.exceptions.UserException;
import com.example.recipenowwebappbackend.repositories.UserRepository;
import com.example.recipenowwebappbackend.models.*;
import com.example.recipenowwebappbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtTokenUtil;
    private User currentUser;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private ConfigurationRepository configurationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found for " + username));
        currentUser = user;
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public AuthenticationResponse registerUser(User user) throws UserAlreadyExistException {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistException("User already exist");
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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void enableUser(String username) {
        userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found for " + username));
        userRepository.enableUser(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserRole(String username, String role) {
        userRepository.updateUserRole(username, Role.valueOf(role));
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
