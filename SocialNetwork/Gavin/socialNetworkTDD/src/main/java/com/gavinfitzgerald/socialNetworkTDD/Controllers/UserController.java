package com.gavinfitzgerald.socialNetworkTDD.Controllers;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
import com.gavinfitzgerald.socialNetworkTDD.DTOs.MessageTooLongException;
import com.gavinfitzgerald.socialNetworkTDD.DTOs.UserSubscriptions;
import com.gavinfitzgerald.socialNetworkTDD.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/api/post")
    public ResponseEntity post(
            @RequestBody Message message) throws MessageTooLongException {
        try {
            if(message.getUser() == null || message.getMessage() == null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            userService.post(message);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (MessageTooLongException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/timeline")
    public ResponseEntity getTimeline(@RequestParam(value = "user", required = true) String user) {
        try {
            return new ResponseEntity(userService.getTimeline(user), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/timelineWithSubscriptions")
    public ResponseEntity getTimelineWithSubscriptions(@RequestParam(value = "user", required = true) String user) {
        try {
            List<String> subscriptions = userService.getSubscriptions(user);
            if (subscriptions != null){
                return new ResponseEntity(userService.getTimelineWithSubscriptions(user, subscriptions), HttpStatus.OK);
            }
            else {
                return new ResponseEntity(userService.getTimeline(user), HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/subscribe")
    public ResponseEntity subscribe(@RequestBody UserSubscriptions userSubs) {
        try {
            if(userSubs.getUser() == null || userSubs.getSubscriptions() == null){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            userService.subscribes(userSubs.getSubscriptions(), userSubs.getUser());
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/getSubscriptions")
    public ResponseEntity getSubscriptions(@RequestParam(value = "user", required = true) String user) {
        try {
            return new ResponseEntity(userService.getSubscriptions(user), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}