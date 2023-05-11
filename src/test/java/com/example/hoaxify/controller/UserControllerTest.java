package com.example.hoaxify.controller;

import com.example.hoaxify.model.User;
import com.example.hoaxify.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    public void postUser_whenUserValid_responseOK(){
        User user = getUser();

        ResponseEntity<Object> response =  testRestTemplate.postForEntity("/users",user, Object.class );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private static User getUser() {
        User user = new User();
        user.setUserName("test-user");
        user.setDispalyName("test-disaply");
        user.setPassword("P4ssword");
        return user;
    }

    @Test
    public void postUser_whenUserValid_userSavedDB(){
        User user = getUser();
        ResponseEntity<Object> response =  testRestTemplate.postForEntity("/users",user, Object.class );
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void postUser_whenUserValid_PassowrdSaveInHash(){
        User user = getUser();
        ResponseEntity<User> response =  testRestTemplate.postForEntity("/users",user, User.class );
        assertThat(response.getBody().getPassword() ).isNotEqualTo(user.getPassword());
    }
}
