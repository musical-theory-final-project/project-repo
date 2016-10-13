package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Brice on 10/13/16.
 */

public interface IntervalLevelRepository extends CrudRepository<IntervalLevel, Integer>{
    IntervalLevel findByLevelNumber(Integer levelNumber);
}
