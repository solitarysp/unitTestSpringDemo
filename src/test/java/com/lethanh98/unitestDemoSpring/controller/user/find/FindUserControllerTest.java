package com.lethanh98.unitestDemoSpring.controller.user.find;

import com.lethanh98.unitestDemoSpring.controller.UserController;
import com.lethanh98.unitestDemoSpring.entity.User;
import com.lethanh98.unitestDemoSpring.repo.UserRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FindUserControllerTest {
    @MockBean
    UserRepo userRepo1;
    UserController userController;

    @Before
    public void startConfig() {
        userController = new UserController(userRepo1);

    }

    @Test
    public void postOK() {
        int id = 1111;
        User user = User.builder().firstName("le").lastName("thanh").id(id).build();
        when(userRepo1.findById(id)).thenReturn(Optional.of(user));
        User userResult = userController.findById(id);
        Assert.assertEquals("So sánh id không giống", userResult.getId(), id);
        Assert.assertEquals("So sánh firstName không giống", userResult.getFirstName(), user.getFirstName());
        Assert.assertEquals("So sánh lastName không giống", userResult.getLastName(), user.getLastName());
    }

    @Test
    public void postFindNull() {
        int id = 1111;
        when(userRepo1.findById(id)).thenReturn(Optional.empty());
        User userResult = userController.findById(id);
        Assert.assertTrue(Objects.isNull(userResult));
    }

    @Test(expected = RuntimeException.class)
    public void postFindErrorRuntime() {
        int id = 1111;
        when(userRepo1.findById(id)).thenThrow(new RuntimeException());
        User userResult = userController.findById(id);
        Assert.assertTrue(Objects.isNull(userResult));
    }

}
