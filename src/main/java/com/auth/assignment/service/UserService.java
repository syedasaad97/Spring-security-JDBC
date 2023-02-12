package com.auth.assignment.service;

import com.auth.assignment.aop.UserNotFoundException;
import com.auth.assignment.dto.UserDto;
import com.auth.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    public UserDto getUserDetail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDto::create)
                .orElseThrow(()-> new UserNotFoundException("User "+email + " not found"));
    }

    public List<UserDto> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::create)
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = "2s")
    public void getData(){

    }
    public int randomNumber(int maxValue){
//        maxValues= 5;
        int a = 400;
//        a=a*;
       String abc = String.valueOf(a);
       if(abc.length()>maxValue || abc.length()==maxValue)
        abc = abc.substring(0,maxValue);
       else if(abc.length()<maxValue)

       return Integer.valueOf(abc);

       Random


       String txt = "Mehdi";


        Map<Character,Integer> stringSet = new LinkedHashMap<Character,Integer>();
        for(int i=0;i<txt.length();i++){
            if(txt.indexOf(txt.charAt(i)) == txt.lastIndexOf(txt.charAt(i))){

            }
        }
            if(stringSet.containsKey(txt.charAt(i))){
                stringSet.put(txt.charAt(i),stringSet.get(txt.charAt(i)+1));
            }else{
                stringSet.put(txt.charAt(i),0);
            }

        }
        int idx=0;
        Character firstUnique =
        for(Map.Entry<Character,Integer> entry : stringSet.entrySet()){
            if(idx==0)
            {Character firstUnique=entry.getValue()
        }

    }
}
