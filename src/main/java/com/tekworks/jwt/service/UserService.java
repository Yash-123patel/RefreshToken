package com.tekworks.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tekworks.jwt.model.Users;
import com.tekworks.jwt.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager auth;       //represent in the Bean in SecurityPkg

    //BCrypting the password by using the class Then BCryting the password
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


    public Users registre(Users user){
        //In BCrypting the password one- method as a (encode) then chage the password BCrypted
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication=auth
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            System.out.println("Authentication is : "+authentication);
            return jwtService.genetateToken(user.getUsername());
        }
        System.out.println("Failed");
        return "Failed";

    }
}
