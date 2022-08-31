package com.technical.mercury.services;

import com.technical.mercury.model.Users.MercuryUserDetails;
import com.technical.mercury.model.Users.User;
import com.technical.mercury.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MercuryUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUserName(username);

        if (user!= null){
            return new MercuryUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException("Not found: " + username);
        }
    }
}
