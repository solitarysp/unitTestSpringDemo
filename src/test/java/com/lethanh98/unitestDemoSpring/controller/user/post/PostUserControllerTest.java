package com.lethanh98.unitestDemoSpring.controller.user.post;

import com.lethanh98.unitestDemoSpring.controller.UserController;
import com.lethanh98.unitestDemoSpring.entity.User;
import com.lethanh98.unitestDemoSpring.repo.UserRepo;
import com.lethanh98.unitestDemoSpring.request.PostUserRQ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PostUserControllerTest {
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
        when(userRepo1.save(Mockito.any(User.class))).thenReturn(user);
        PostUserRQ postUserRQ = PostUserRQ.builder().firstName(user.getFirstName()).lastName(user.getLastName()).build();

        User userResult = userController.post(postUserRQ, id);

        Assert.assertEquals("So sánh id không giống", userResult.getId(), id);
        Assert.assertEquals("So sánh firstName không giống", userResult.getFirstName(), user.getFirstName());
        Assert.assertEquals("So sánh lastName không giống", userResult.getLastName(), user.getLastName());
    }

    @Test(expected = RuntimeException.class)
    public void postErrorSave() {
        int id = 1111;
        when(userRepo1.save(Mockito.any(User.class))).thenThrow(new RuntimeException());
        PostUserRQ postUserRQ = PostUserRQ.builder().firstName("le").lastName("thanh").build();
        userController.post(postUserRQ, id);
    }
}
