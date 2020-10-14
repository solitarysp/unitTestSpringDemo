package com.lethanh98.unitestDemoSpring.controller.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lethanh98.unitestDemoSpring.controller.UserController;
import com.lethanh98.unitestDemoSpring.entity.User;
import com.lethanh98.unitestDemoSpring.repo.UserRepo;
import com.lethanh98.unitestDemoSpring.request.PostUserRQ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration()
public class TestMockControllerHttp {
    @MockBean
    UserRepo userRepo;
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllUser() throws Exception {
        when(userRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(User.builder().firstName("le").lastName("thanh").build(), User.builder().firstName("le").lastName("tuan").build())));
        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data[0].firstName", is("le")))
                .andExpect(jsonPath("$.data[0].lastName", is("thanh")))
                .andExpect(jsonPath("$.data[1].firstName", is("le")))
                .andExpect(jsonPath("$.data[1].lastName", is("tuan")))
        ;
    }
    @Test
    public void testPostAllUser() throws Exception {
        int id = 1111;
        User user = User.builder().firstName("le").lastName("thanh").id(id).build();
        when(userRepo.save(Mockito.any(User.class))).thenReturn(user);

        PostUserRQ postUserRQ = PostUserRQ.builder().firstName(user.getFirstName()).lastName(user.getLastName()).build();
        mvc.perform(post("/api/users")
                .queryParam("id", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(postUserRQ))
        )
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("le")))
                .andExpect(jsonPath("$.lastName", is("thanh")))

        ;
    }
}
