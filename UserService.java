
package com.linkedlite.backend.service;

import com.linkedlite.backend.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);
    List<User> getAllUsers();
    List<User> searchUsers(String fullName);
    User getUserById(Integer userId);
    User updateUser(Integer userId, User updatedUser,String loggedInEmail);
    void deleteUser(Integer userId,String loggedInEmail);
    String loginUser(String email, String password);
}