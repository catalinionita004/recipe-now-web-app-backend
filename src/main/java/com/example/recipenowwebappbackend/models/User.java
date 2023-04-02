package com.example.recipenowwebappbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "recipe_user")
@NoArgsConstructor

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @JsonIgnore
    @Column(nullable = false)
    private String username;
    @JsonIgnore
    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    @Column(nullable = false)
    private Role role;
    @JsonIgnore
    @Column(nullable = false)
    private int enabled;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade={CascadeType.MERGE})
    private List<Recipe> recipeList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade={CascadeType.MERGE})
    private List<Interaction> interactionList;

    public User(String firstName, String lastName, String username, String email, String password, Role role, int enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getAuthority()));

        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }

}
