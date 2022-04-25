package org.esgi.boissibook.features.user.infra.config;

import org.esgi.boissibook.features.user.domain.UserService;
import org.esgi.boissibook.features.user.domain.UserStore;
import org.esgi.boissibook.features.user.infra.repository.JPAUserRepository;
import org.esgi.boissibook.features.user.infra.repository.JPAUserStore;
import org.esgi.boissibook.kernel.event.EventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUserBeans {
    @Bean
    UserStore userJPAStore(JPAUserRepository userRepository) {
        return new JPAUserStore(userRepository);
    }

    @Bean
    public UserService userService(UserStore userStore, EventService eventService) {
        return new UserService(userStore, eventService);
    }
}
