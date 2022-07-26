package org.esgi.boissibook.features.achievement.infra.repository;

import java.util.List;
import org.esgi.boissibook.features.achievement.domain.UserStatistics;
import org.esgi.boissibook.features.achievement.domain.UserStatisticsRepository;
import org.esgi.boissibook.features.achievement.infra.UserStatisticsMapper;
import org.esgi.boissibook.features.achievement.kernel.exception.AchievementExceptionMessage;
import org.esgi.boissibook.features.achievement.kernel.exception.AchievementNotFoundException;
import org.esgi.boissibook.kernel.exception.ConflictException;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.UserId;
import org.esgi.boissibook.kernel.repository.UserStatisticsId;

public class SpringDataUserStatisticsRepository implements UserStatisticsRepository {
    private final JpaUserStatisticsRepository jpaUserStatisticsRepository;

    public SpringDataUserStatisticsRepository(JpaUserStatisticsRepository jpaUserStatisticsRepository) {
        this.jpaUserStatisticsRepository = jpaUserStatisticsRepository;
    }


    @Override
    public UserStatistics findByUserId(UserId userId) {
        return UserStatisticsMapper.mapUserStatisticsEntityToUserStatistics(jpaUserStatisticsRepository.findByUserId(userId.value())
            .orElseThrow(() -> new AchievementNotFoundException(String.format("%s : %s", AchievementExceptionMessage.USER_STATS_NOT_FOUND, userId.value()))));
    }

    @Override
    public UserStatisticsId save(UserStatistics entity) throws ConflictException {
        jpaUserStatisticsRepository.save(UserStatisticsMapper.mapUserStatisticsToUserStatisticsEntity(entity)).id();
        return entity.id();
    }

    @Override
    public long count() {
        return jpaUserStatisticsRepository.count();
    }

    @Override
    public List<UserStatistics> findAll() {
        return jpaUserStatisticsRepository.findAll().stream().map(UserStatisticsMapper::mapUserStatisticsEntityToUserStatistics).toList();
    }

    @Override
    public UserStatistics find(UserStatisticsId id) throws NotFoundException {
        return UserStatisticsMapper.mapUserStatisticsEntityToUserStatistics(jpaUserStatisticsRepository.findById(id.value())
            .orElseThrow(() -> new AchievementNotFoundException(String.format("%s : %s", AchievementExceptionMessage.USER_STATS_NOT_FOUND, id))));
    }

    @Override
    public void delete(UserStatistics entity) throws NotFoundException {
        jpaUserStatisticsRepository.delete(UserStatisticsMapper.mapUserStatisticsToUserStatisticsEntity(entity));
    }

    @Override
    public void deleteAll() {
        jpaUserStatisticsRepository.deleteAll();
    }

    @Override
    public UserStatisticsId nextId() {
        return UserStatisticsId.nextId();
    }
}
