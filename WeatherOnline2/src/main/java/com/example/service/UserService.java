package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admarcu on 11/24/2016.
 */
@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public boolean validateUser(String user, String password) {
        return user.equalsIgnoreCase("adrian") && password.equals("dummy");
    }

    public boolean validateUser2(String user, String password) {
        User dbuser = userRepository.findByUsernameAndPassword(user, password);//daca se gaaseste in baza de date
        if(dbuser != null){
            return user.equalsIgnoreCase(dbuser.getUsername()) && password.equals(dbuser.getPassword());
        }
        return false;
        /*System.out.println(user + "----" + password);
        System.out.println(dbuser.getUsername() + "****" + dbuser.getPassword());
        System.out.println(user.equalsIgnoreCase(dbuser.getUsername()) && password.equals(dbuser.getPassword()));*/
        //de validat userul cu baza de date
    }

}
