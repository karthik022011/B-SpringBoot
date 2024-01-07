package com.kartheek.learning.springboot.login;

import com.kartheek.learning.springboot.registration.entity.RegisterUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class RegisterUserDetails implements UserDetails {
    private String name;
    private String password;

    public RegisterUserDetails(RegisterUser registerUser) {
        this.name = registerUser.getUserName();
        this.password= registerUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return true;
    }
}
