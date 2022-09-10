package dev.titans.services;

import dev.titans.dtos.LoginCredentials;
import dev.titans.entities.User;
import dev.titans.exceptions.PasswordMismatchException;
import dev.titans.exceptions.UserNotFoundException;
import dev.titans.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtService jwtService;

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public String authenticateUser(LoginCredentials loginCredentials) {
        User user = userRepo.findByUsername(loginCredentials.getUsername());

        if(user == null){
            throw new UserNotFoundException();
        }else if(!user.getPassword().equals(loginCredentials.getPassword())){
            throw new PasswordMismatchException();
        }else {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String message = "A login was performed at "+timeStamp+", by user: "+loginCredentials.getUsername();
            jmsTemplate.convertAndSend("titan-important-events", message);
            return jwtService.createJwtWithUsernameAndRole(user.getUsername(),user.getRole());
        }
    }
}
