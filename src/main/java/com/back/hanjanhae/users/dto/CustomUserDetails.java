package com.back.hanjanhae.users.dto;

import com.back.hanjanhae.users.model.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final UsersSocialSaveDTO users;

    public CustomUserDetails(UsersSocialSaveDTO users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return users.getUsersSocialId();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return users.getUsersEmail();
    }

    @Override
    public String getUsername() {
        return users.getUsersSocialId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
