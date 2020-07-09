package org.utbm.entity;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.management.MonitorInfo;

@Log4j2
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Flux<User> findAll(){
        return  userRepository.findAll();
    }

    public Mono<User> findById(String id){
        return userRepository.findById(id);
    }

    public Mono<User> save(User user){
        return userRepository.save(user);
    }

    public Mono<User> delete(String id){
        return userRepository.findById(id)
                .flatMap(user -> userRepository.delete(user).thenReturn(user));
    }

}
