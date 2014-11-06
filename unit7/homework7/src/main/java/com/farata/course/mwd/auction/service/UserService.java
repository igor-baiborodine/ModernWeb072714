package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.entity.User;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    void init() {
        initUsers();
    }

    private List<User> users;

    private void initUsers() {

        users = Lists.newArrayListWithExpectedSize(3);

        User user = new User(1, "philip.fry", "philip.fry@planet-express.com", true);
        users.add(user);

        User user2 = new User(2, "bender.rodriguez", "bender.rodriguez@planet-express.com", true);
        users.add(user2);

        User user3 = new User(3, "amy.wong", "amy.wong@planet-express.com", true);
        users.add(user3);
    }

    public User findUserById(int id) {

        List<User> filteredUsers = users.stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());
        User user = filteredUsers.size() == 1 ? filteredUsers.get(0) : null;
        logger.info("Found for id[{}] user[{}]", id, user);

        return user;
    }

}
