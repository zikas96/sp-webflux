package org.utbm.config;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.utbm.entity.User;
import org.utbm.entity.UserRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Log4j2
@AllArgsConstructor
@Component
public class DemoDataInit implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        userRepository.deleteAll()
                .thenMany(
                        Flux.just("John","David","Jim")
                        .map(name -> new User(UUID.randomUUID().toString(), name, name.toLowerCase()))
                        .flatMap(userRepository::save)

                )
                .thenMany(
                        userRepository.findAll()
                )
                .subscribe(user -> log.info("Saving User : "+ user));
    }


}
