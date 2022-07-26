package org.esgi.boissibook.features.achievement.infra.config;

import org.esgi.boissibook.features.achievement.domain.AchievementCommandHandler;
import org.esgi.boissibook.features.achievement.domain.AchievementQueryHandler;
import org.esgi.boissibook.features.achievement.domain.AchievementRepository;
import org.esgi.boissibook.features.achievement.domain.UserStatisticsCommandHandler;
import org.esgi.boissibook.features.achievement.domain.UserStatisticsRepository;
import org.esgi.boissibook.features.achievement.infra.SpringUserStatisticsEventListener;
import org.esgi.boissibook.features.achievement.infra.repository.JpaAchievementRepository;
import org.esgi.boissibook.features.achievement.infra.repository.JpaUserStatisticsRepository;
import org.esgi.boissibook.features.achievement.infra.repository.SpringDataAchievementRepository;
import org.esgi.boissibook.features.achievement.infra.repository.SpringDataUserStatisticsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAchievementBeans {

    @Bean
    public AchievementCommandHandler achievementCommandHandler(AchievementRepository achievementRepository) {
        return new AchievementCommandHandler(achievementRepository);
    }

    @Bean
    public AchievementQueryHandler achievementQueryHandler(AchievementRepository achievementRepository) {
        return new AchievementQueryHandler(achievementRepository);
    }

    @Bean
    public UserStatisticsCommandHandler userStatisticsCommandHandler(UserStatisticsRepository userStatisticsRepository) {
        return new UserStatisticsCommandHandler(userStatisticsRepository);
    }

    @Bean
    public SpringUserStatisticsEventListener springUserStatisticsEventListener(UserStatisticsCommandHandler userStatisticsCommandHandler, AchievementCommandHandler achievementCommandHandler) {
        return new SpringUserStatisticsEventListener(userStatisticsCommandHandler, achievementCommandHandler);
    }

    @Bean
    public AchievementRepository achievementRepository(JpaAchievementRepository jpaAchievementRepository) {
        return new SpringDataAchievementRepository(jpaAchievementRepository);
    }

    @Bean
    public UserStatisticsRepository userStatisticsRepository(JpaUserStatisticsRepository jpaUserStatisticsRepository) {
        return new SpringDataUserStatisticsRepository(jpaUserStatisticsRepository);
    }
}
