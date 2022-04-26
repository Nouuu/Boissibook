package org.esgi.boissibook.features.user.infra.config;

import org.esgi.boissibook.features.user.domain.UserService;
import org.esgi.boissibook.features.user.domain.UserRepository;
import org.esgi.boissibook.features.user.infra.repository.JPAUserRepository;
import org.esgi.boissibook.features.user.infra.repository.SpringDataUserRepository;
import org.esgi.boissibook.kernel.event.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUserBeans {
    @Bean
    UserRepository userJPAStore(JPAUserRepository userRepository) {
        return new SpringDataUserRepository(userRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository, EventService eventService) {
        return new UserService(userRepository, eventService);
    }
}
