package net.antra.restful.mobileappws.ui.controller;

import net.antra.restful.mobileappws.service.UserService;
import net.antra.restful.mobileappws.shared.dto.UserDto;
import net.antra.restful.mobileappws.ui.model.request.UserDetailsRequestModel;
import net.antra.restful.mobileappws.ui.model.response.UserRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")  // http://localhost:8080/users/
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "get user was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        logger.info("Get inside the post method!");

        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }
}
