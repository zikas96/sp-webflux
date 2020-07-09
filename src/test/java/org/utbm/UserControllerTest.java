package org.utbm;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.utbm.entity.User;
import org.utbm.entity.UserService;
import reactor.core.publisher.Mono;

@Log4j2
@WebFluxTest
public class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void getById(){
        log.info("Running get by Id");
        User user = new User("12345","fati", "fati@gmail.com");
        Mockito.when(userService.findById(user.getId()))
                .thenReturn(Mono.just(user));

        webTestClient.get()
                .uri("/user/"+user.getId())
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void createUser(){
        for(int i=0; i<10000;i++){
            log.info("Running get by Id");
            User user = new User(String.valueOf(i),"fati"+i, "fati"+i+"@gmail.com");
            Mockito.when(userService.findById(user.getId()))
                    .thenReturn(Mono.just(user));

            webTestClient.get()
                    .uri("/user/"+user.getId())
                    .exchange()
                    .expectStatus().isOk();
        }


    }
}
