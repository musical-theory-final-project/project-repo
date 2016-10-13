package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 10/13/16.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByEmail(String email);
}
