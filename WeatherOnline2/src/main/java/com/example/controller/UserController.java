package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admarcu on 11/26/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User findByUsername(@PathVariable @RequestBody String username) {
      //  User user=userRepository.findByUsername(username);
       // java.util.Collections.sort(user.getCities());
        return userRepository.findByUsername(username);
    }



    @RequestMapping(path="/login",method = RequestMethod.POST)
    public String authenticate(@RequestBody User user) {
        if(!userService.validateUser2(user.getUsername(),user.getPassword())){
            return "false";
        }
        return "true";

    }


    @RequestMapping(path="/register",method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        //String safePass = encryptionService.encrypt(user.getPassword());
        //user.setPassword(safePass);
        //user.setGravatarHash(MD5Util.md5Hex(user.getEmail()));
        //user.setFullName(user.getFirstName() + " " + user.getLastName());
        System.out.println(user.getUsername() + "---" + user.getPassword() );

        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }
}
