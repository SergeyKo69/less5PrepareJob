package ru.kogut.repository;

import ru.kogut.model.UserEntity;

import java.util.List;

/**
 * @author S.Kogut on 2019-03-25
 */
public interface UserRepository {

    void create(UserEntity user);

    List<UserEntity> findAll();

    UserEntity findOne(String id);

    void update(UserEntity user);

    void deleteById(String id);

    void delete(UserEntity user);

}
