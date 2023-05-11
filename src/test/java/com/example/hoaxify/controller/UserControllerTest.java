package com.example.hoaxify.controller;

import com.example.hoaxify.dto.UserDto;
import com.example.hoaxify.error.APIError;
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
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void postUser_whenUserValid_responseOK() {
        UserDto user = getUser();

        ResponseEntity<Object> response = testRestTemplate.postForEntity("/users", user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private static UserDto getUser() {
        UserDto userDto = new UserDto();
        userDto.setUserName(null);
        userDto.setDispalyName("test-disaply");
        userDto.setPassword("P4ssword");
        return userDto;
    }

    @Test
    public void postUser_whenUserValid_userSavedDB() {
        UserDto user = getUser();
        ResponseEntity<Object> response = testRestTemplate.postForEntity("/users", user, Object.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void postUser_whenUserNameIsNull_BadRequest() {
        UserDto userDto = new UserDto();
        userDto.setUserName(null);
        userDto.setDispalyName("test-disaply");
        userDto.setPassword("P4ssword");
        ResponseEntity<User> response = testRestTemplate.postForEntity("/users", userDto, User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    public void postUser_whenUserValid_PassowrdSaveInHash() {
        UserDto user = new UserDto();
        ResponseEntity<APIError> response = testRestTemplate.postForEntity("/users", user, APIError.class);
        assertThat(response.getBody().getUri()).isEqualTo("/users");
    }

    @Test
    public void postUser_whenUserObjectNull_StatusBadReqesut() {
        UserDto user = new UserDto();
        ResponseEntity<APIError> response = testRestTemplate.postForEntity("/users", user, APIError.class);
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void postUser_whenUserNameAlreadyPresent_StatusBadReqesut() {
        UserDto user = new UserDto();
        ResponseEntity<APIError> response = testRestTemplate.postForEntity("/users", user, APIError.class);
        ResponseEntity<APIError> lastResponse = testRestTemplate.postForEntity("/users", user, APIError.class);
        assertThat(lastResponse.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
