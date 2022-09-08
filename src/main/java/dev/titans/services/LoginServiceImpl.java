package dev.titans.services;

import dev.titans.dtos.LoginCredentials;
import dev.titans.entities.User;
import dev.titans.exceptions.PasswordMismatchException;
import dev.titans.exceptions.UserNotFoundException;
import dev.titans.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtService jwtService;

    @Override
    public String authenticateUser(LoginCredentials loginCredentials) {
        User user = userRepo.findByUsername(loginCredentials.getUsername());

        if(user == null){
            throw new UserNotFoundException();
        }
        if(!user.getPassword().equals(loginCredentials.getPassword())){
            throw new PasswordMismatchException();
        }
        return jwtService.createJwtWithUsernameAndRole(user.getUsername(),user.getRole());
    }
}
