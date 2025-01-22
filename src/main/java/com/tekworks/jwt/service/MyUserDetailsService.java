package com.tekworks.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tekworks.jwt.model.UserPrinciple;
import com.tekworks.jwt.model.Users;
import com.tekworks.jwt.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userRepo.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrinciple(user);
    }
}
