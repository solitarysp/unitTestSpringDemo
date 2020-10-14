package com.lethanh98.unitestDemoSpring.controller.user.get;

import com.lethanh98.unitestDemoSpring.controller.UserController;
import com.lethanh98.unitestDemoSpring.entity.User;
import com.lethanh98.unitestDemoSpring.repo.UserRepo;
import com.lethanh98.unitestDemoSpring.reponse.UsersRP;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Slf4j
public class GetUserControllerTest {
    @MockBean
    UserRepo userRepo1;
    UserController userController;

    @Before
    public void startConfig() {
        userController = new UserController(userRepo1);
    }

    @Test
    public void testGetAllUserOK() {
        when(userRepo1.findAll()).thenReturn(new ArrayList<>(Collections.singletonList(User.builder().firstName("le").lastName("thanh").build())));
        UsersRP data = userController.get();
        Assert.assertFalse(data.getData().size() <= 0);
        Assert.assertEquals("le", data.getData().get(0).getFirstName());
        Assert.assertEquals("thanh", data.getData().get(0).getLastName());
    }

    @Test
    public void testGetAllUserEmpty() {
        when(userRepo1.findAll()).thenReturn(new ArrayList<>(Collections.emptyList()));
        UsersRP data = userController.get();
        Assert.assertTrue(data.getData().size() <= 0);
    }

    @Test
    public void testGetAllUserFindUserError() {
        when(userRepo1.findAll()).thenThrow(new RuntimeException());
        UsersRP data = userController.get();
        Assert.assertEquals(500, (int) data.getStatus());
    }
}
