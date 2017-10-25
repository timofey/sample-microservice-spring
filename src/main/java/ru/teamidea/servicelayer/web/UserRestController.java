package ru.teamidea.servicelayer.web;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.teamidea.servicelayer.domain.User;
import ru.teamidea.servicelayer.repository.UserRepository;
import ru.teamidea.servicelayer.service.UserService;

import java.util.List;

/**
 * Created by Timofey Klyubin on 26.10.17
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    private UserRepository userRepository;
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> getAllUsers() {
        LOG.info("Getting all users...");
        return Lists.newArrayList(userRepository.findAll());
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable("id") long id) {
        LOG.info("Getting user with id " + id);
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/by-email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getByEmail(@PathVariable("email") String email) {
        LOG.info("Getting user with email " + email);
        return userRepository.findByEmail(email);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User addNewUser(@RequestBody final User newUser) {
        LOG.info("Creating new user...");
        userService.addNewUser(newUser);
        return newUser;
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User updateUser(@PathVariable("id") long userId, @RequestBody final User newUser) {
        LOG.info("Updating user with id " + userId);
        User user = userRepository.findOne(userId);
        if (user == null) {
            return null;
        }
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateUser(@PathVariable("id") long userId) {
        LOG.info("Deleting user with id " + userId);
        userRepository.delete(userId);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
