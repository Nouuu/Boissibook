package org.esgi.boissibook.features.achievement.infra.repository;

import java.util.List;
import org.esgi.boissibook.features.achievement.domain.Achievement;
import org.esgi.boissibook.features.achievement.domain.AchievementRepository;
import org.esgi.boissibook.features.achievement.infra.AchievementMapper;
import org.esgi.boissibook.features.achievement.kernel.exception.AchievementExceptionMessage;
import org.esgi.boissibook.features.achievement.kernel.exception.AchievementNotFoundException;
import org.esgi.boissibook.kernel.exception.ConflictException;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.AchievementId;
import org.esgi.boissibook.kernel.repository.UserId;

public class SpringDataAchievementRepository implements AchievementRepository {
    private final JpaAchievementRepository achievementRepository;

    public SpringDataAchievementRepository(JpaAchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public AchievementId save(Achievement entity) throws ConflictException {
        achievementRepository.save(AchievementMapper.mapAchievementToAchievementEntity(entity));
        return entity.id();
    }

    @Override
    public long count() {
        return achievementRepository.count();
    }

    @Override
    public List<Achievement> findAll() {
        return achievementRepository.findAll().stream()
            .map(AchievementMapper::mapAchievementEntityToAchievement)
            .toList();
    }

    @Override
    public Achievement find(AchievementId id) throws NotFoundException {
        return AchievementMapper.mapAchievementEntityToAchievement(achievementRepository.findById(id.value())
            .orElseThrow(() -> new AchievementNotFoundException(String.format("%s : %s", AchievementExceptionMessage.ACHIEVEMENT_NOT_FOUND, id))));
    }

    @Override
    public void delete(Achievement entity) throws NotFoundException {
        achievementRepository.delete(AchievementMapper.mapAchievementToAchievementEntity(entity));
    }

    @Override
    public void deleteAll() {
        achievementRepository.deleteAll();
    }

    @Override
    public AchievementId nextId() {
        return AchievementId.nextId();
    }

    @Override
    public List<Achievement> findByUserId(UserId userId) {
        return achievementRepository.findByUserId(userId.value()).stream()
            .map(AchievementMapper::mapAchievementEntityToAchievement)
            .toList();
    }
}
