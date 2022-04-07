package com.gavinfitzgerald.socialNetworkTDD.Controllers;

import com.gavinfitzgerald.socialNetworkTDD.MessageTooLongException;
import com.gavinfitzgerald.socialNetworkTDD.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/api/post")
    public ResponseEntity post(
    @RequestParam(value = "user", required = true) String user,
    @RequestParam(value = "message", required = true) String message) throws MessageTooLongException {
        try {
            userService.post(user, message);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (MessageTooLongException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}