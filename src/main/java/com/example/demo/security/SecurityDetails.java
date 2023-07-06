package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class SecurityDetails implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        System.out.println("hey i am here");e
      //  try {
            Optional<User> user = userRepo.findByName(name);
            System.out.println("yes i am");
            return user.map(Userinfo::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + name));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
    }

}
