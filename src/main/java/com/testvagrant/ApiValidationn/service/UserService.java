package com.testvagrant.ApiValidationn.service;

import com.testvagrant.ApiValidationn.dto.UserRequest;
import com.testvagrant.ApiValidationn.entity.User;
import com.testvagrant.ApiValidationn.exception.UserNotFoundException;
import com.testvagrant.ApiValidationn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserRequest userRequest){
        User user = User.builder(0, userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getMobile(),
                userRequest.getGender(),
                userRequest.getAge(),
                userRequest.getNationality());
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return user;
    }
    public User updateUser(int id, UserRequest userRequest) throws UserNotFoundException {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userToUpdate.setName(userRequest.getName());
        userToUpdate.setEmail(userRequest.getEmail());
        userToUpdate.setMobile(userRequest.getMobile());
        userToUpdate.setGender(userRequest.getGender());
        userToUpdate.setAge(userRequest.getAge());
        userToUpdate.setNationality(userRequest.getNationality());

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(int id) throws UserNotFoundException {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(userToDelete);
    }

}
