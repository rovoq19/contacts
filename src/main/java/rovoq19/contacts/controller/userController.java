package rovoq19.contacts.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rovoq19.contacts.domain.User;
import rovoq19.contacts.reps.UserRep;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("user")
public class userController {
    private final UserRep userRep;

    @Autowired
    public userController (UserRep userRep) {
        this.userRep = userRep;
    }

    @GetMapping
    public List <User> list(){
        return userRep.findAll ();
    }

    @GetMapping("{id}")
    public User getUserId(@PathVariable("id") User user){
        return user;
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        user.setCreationDate(LocalDateTime.now ());
        return userRep.save(user);
    }

    @PutMapping("{id}")
    public User updUser(@PathVariable("id") User userData, @RequestBody User user){
        BeanUtils.copyProperties (user, userData, "id");

        return userRep.save (userData);
    }

    @DeleteMapping("{id}")
    public void delUser(@PathVariable("id") User user){
        userRep.delete (user);
    }
}
