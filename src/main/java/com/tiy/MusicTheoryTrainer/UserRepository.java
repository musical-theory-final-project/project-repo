package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by RdDvls on 10/11/16.
 */
public interface UserRepository extends CrudRepository{
    User findByEmail(String email);
}
