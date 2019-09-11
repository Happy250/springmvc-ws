package net.antra.restful.mobileappws.ui.controller;

import net.antra.restful.mobileappws.exception.UserServiceException;
import net.antra.restful.mobileappws.service.UserService;
import net.antra.restful.mobileappws.shared.dto.UserDto;
import net.antra.restful.mobileappws.ui.model.request.UserDetailsRequestModel;
import net.antra.restful.mobileappws.ui.model.response.ErrorMessages;
import net.antra.restful.mobileappws.ui.model.response.UserRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")  // http://localhost:8080/users
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping(path="/{id}",
                produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    /*
     It seems like postman does not take Accept/application json as the Paramaters
     */
    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
            )
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws UserServiceException {
        logger.info("Get inside the post method!");

        UserRest returnValue = new UserRest();

        if (userDetails.getFirstName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

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
