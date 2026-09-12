package com.linkedlite.backend.serviceimpl;

import com.linkedlite.backend.entity.User;
import com.linkedlite.backend.repository.UserRepository;
import com.linkedlite.backend.security.JwtUtil;
import com.linkedlite.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import com.linkedlite.backend.exception.UnauthorizedException;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public List<User> searchUsers(String fullName) {

        return userRepository.findByFullNameContainingIgnoreCase(fullName);

    }
    @Override
    public User getUserById(Integer userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new RuntimeException("User not found");
    }
    @Override
    public User updateUser(Integer userId, User updatedUser, String loggedInEmail) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            User existingUser = optionalUser.get();
            if (!existingUser.getEmail().equals(loggedInEmail)) {
                throw new UnauthorizedException("You can update only your own profile");
            }

            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setHeadline(updatedUser.getHeadline());
            existingUser.setAbout(updatedUser.getAbout());
            existingUser.setLocation(updatedUser.getLocation());
            existingUser.setProfileImage(updatedUser.getProfileImage());

            return userRepository.save(existingUser);
        }

        throw new RuntimeException("User not found");
    }
    @Override
    public void deleteUser(Integer userId,String loggedInEmail){
        Optional<User>optionalUser=userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User existingUser=optionalUser.get();
            if(!existingUser.getEmail().equals(loggedInEmail)){
                throw new UnauthorizedException("You can delete only your own profile");
            }
            userRepository.deleteById(userId);
            return;
        }
        throw new RuntimeException("User not found");
    }
    @Override
    public String loginUser(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return token;
            }
        }

        throw new RuntimeException("Invalid email or password");
    }
}

