package org.esgi.boissibook.features.user.infra.config;

import org.esgi.boissibook.features.user.domain.UserCommandHandler;
import org.esgi.boissibook.features.user.domain.UserQueryHandler;
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
    public UserCommandHandler userCommandHandler(UserRepository userRepository, EventService eventService) {
        return new UserCommandHandler(userRepository, eventService);
    }
    @Bean
    public UserQueryHandler userQueryHandler(UserRepository userRepository) {
        return new UserQueryHandler(userRepository);
    }
}
