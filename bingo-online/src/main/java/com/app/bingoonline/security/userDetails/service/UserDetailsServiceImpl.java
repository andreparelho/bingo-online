package com.app.bingoonline.security.userDetails.service;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.security.userDetails.repository.UserDetailsRepositoryImpl;
import com.app.bingoonline.exception.user.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        return new UserDetailsRepositoryImpl(user);
    }
}
