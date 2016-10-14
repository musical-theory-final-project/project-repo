package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Brice on 10/13/16.
 */
public interface UserStatusRepository extends CrudRepository<UserStatus, Integer> {
        UserStatus findByUser(User user);
}
