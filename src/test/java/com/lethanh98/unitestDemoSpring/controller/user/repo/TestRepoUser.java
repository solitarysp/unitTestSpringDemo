package com.lethanh98.unitestDemoSpring.controller.user.repo;

import com.lethanh98.unitestDemoSpring.MainApp;
import com.lethanh98.unitestDemoSpring.entity.User;
import com.lethanh98.unitestDemoSpring.repo.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MainApp.class)
public class TestRepoUser {
    @Autowired
    private UserRepo repo;

    @Test
    public void testGetAllUser() {
      List<User> users= (List<User>) repo.findAll();
        Assert.assertTrue(users.size()>2);
    }
    @Test
    public void testFindById() {
        Optional<User> users=  repo.findById(1);
        if(users.isPresent()){
            Assert.assertEquals("Dangote", users.get().getLastName());
        }else {
            Assert.fail("Không tìm thấy ID");
        }
    }
    @Test
    public void testFindByIdNotFound() {
        Optional<User> users=  repo.findById(1000);
        Assert.assertTrue(!users.isPresent());
    }
    @Test
    public void testCount() {
        long count=  repo.count();
        Assert.assertTrue(count > 2);
    }
}
