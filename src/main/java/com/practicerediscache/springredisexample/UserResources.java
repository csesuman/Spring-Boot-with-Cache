package com.practicerediscache.springredisexample;

import com.practicerediscache.springredisexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("rest/user")
public class UserResources {

    private UserRepository userRepository;

    public UserResources(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add/{id}/{name}")
    public User add (@PathVariable("id") final String id,
                     @PathVariable("name") final String name){
        userRepository.save(new User(id,name,20000L));
        return userRepository.findByid(id);
    }

    @GetMapping("/update/{id}/{name}")
    public User update (@PathVariable("id") final String id,
                     @PathVariable("name") final String name){
        userRepository.update(new User(id,name,10000L));
        return userRepository.findByid(id);
    }

    @GetMapping("/all")
        public Map<String,User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    public Map<String,User> delete(@PathVariable final String id){

        userRepository.deleteByid(id);
        return userRepository.findAll();
    }




}
