package org.moonframework.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.moonframework.entity.User;
import org.moonframework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // protected final Logger logger = LogManager.getLogger(UserController.class);
    private static final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        logger.info("hello hello");
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }

}

