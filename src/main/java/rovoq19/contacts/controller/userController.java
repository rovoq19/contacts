package rovoq19.contacts.controller;

import org.springframework.web.bind.annotation.*;
import rovoq19.contacts.exeptions.notFoundExeption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class userController {
    private int counter = 4;

    private List<Map<String, String>> users = new ArrayList <Map<String, String>> (){{
        add(new HashMap <String, String> (){{put ("id","1"); put ("name", "qwerty 1");}});
        add(new HashMap <String, String> (){{put ("id","2"); put ("name", "qwerty 2");}});
        add(new HashMap <String, String> (){{put ("id","3"); put ("name", "qwerty 3");}});
    }};
    @GetMapping
    public List<Map<String, String>> list(){
        return users;
    }
    @GetMapping("{id}")
    public Map<String, String> getUserId(@PathVariable String id){
        return getUser (id);
    }

    private Map <String, String> getUser (@PathVariable String id) {
        return users.stream ()
                .filter(user -> user.get("id").equals (id))
                .findFirst ()
                .orElseThrow (notFoundExeption::new);
    }

    @PostMapping
    public Map<String, String> addUser(@RequestBody Map<String, String> user){
        user.put ("id", String.valueOf (counter++));

        users.add(user);

        return user;
    }

    @PutMapping("{id}")
    public Map<String, String> updUser(@PathVariable String id, @RequestBody Map<String, String> user){
        Map <String, String> userData = getUser (id);

        userData.putAll (user);
        userData.put ("id", id);

        return userData;
    }

    @DeleteMapping("{id}")
    public void delUser(@PathVariable String id){
        Map<String, String> user = getUser (id);

        users.remove (user);
    }
}
