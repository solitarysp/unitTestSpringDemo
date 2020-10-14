package com.lethanh98.unitestDemoSpring.controller;

import com.lethanh98.unitestDemoSpring.entity.User;
import com.lethanh98.unitestDemoSpring.repo.UserRepo;
import com.lethanh98.unitestDemoSpring.reponse.UsersRP;
import com.lethanh98.unitestDemoSpring.reponse.dto.UsersDtoRp;
import com.lethanh98.unitestDemoSpring.request.PostUserRQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(value = "/api/users")
public class UserController extends BaseResource {

    final
    UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping()
    public UsersRP get() {
        List<UsersDtoRp> usersDTOPR = new ArrayList<>();
        UsersRP usersRP = new UsersRP();
        try {
            List<User> users = (List<User>) userRepo.findAll();
            users.stream().forEach(user -> {
                UsersDtoRp usersDtoRp = new UsersDtoRp();
                BeanUtils.copyProperties(user, usersDtoRp);
                usersDTOPR.add(usersDtoRp);
            });
            usersRP.setStatus(200);
        } catch (Exception e) {
            usersRP.setStatus(500);
        }
        usersRP.setData(usersDTOPR);
        return usersRP;

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public User post(@Valid @RequestBody() PostUserRQ userRQ,
                     @PathVariable(value = "id", required = false) Integer id) {
        log.info("test post");
        User user = new User();
        user.setFirstName(userRQ.getFirstName());
        user.setLastName(userRQ.getFirstName());
        user = userRepo.save(user);
        log.info("User new post is {}", user);

        return user;
    }

    @PostMapping(value = "/{id}")
    @Transactional
    public User findById(@PathVariable(value = "id", required = false) Integer id) {
        Optional<User> userOp = userRepo.findById(id);
        return userOp.orElse(null);
    }

}
