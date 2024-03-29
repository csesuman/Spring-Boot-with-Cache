package com.practicerediscache.springredisexample;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequestMapping("/rest/url")
@RestController
public class UrlResources {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id ){

        String url  = redisTemplate.opsForValue().get(id);
        System.out.println("URL received : " + url);

        return url;
    }

    @PostMapping
    public String create(@RequestBody String url){

        String[]   schemes = new String[]{
                "http" , "https"
        };

        UrlValidator urlValidator = new UrlValidator(schemes);
        if(urlValidator.isValid(url)){
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            System.out.println("URL id Generated : " + id);
            redisTemplate.opsForValue().set(id,url);
            return id;
        }

        return null;
    }
}
