package com.practicerediscache.springredisexample;

import com.practicerediscache.springredisexample.model.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {

    void save (User user);
    Map<String, User> findAll();
    User findByid(String id);
    void update(User user);
    void deleteByid(String id);
}
