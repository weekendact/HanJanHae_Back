package com.back.hanjanhae.users.service;



import com.back.hanjanhae.users.dto.CustomUserDetails;
import com.back.hanjanhae.users.model.entity.UsersEntity;
import com.back.hanjanhae.users.model.repository.UsersRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    public CustomUserDetailsService(UsersRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsersEntity userData = userRepository.findByUsersSocialId(username);

        if (userData != null) {

            return new CustomUserDetails(userData);
        }


        return null;
    }
}
