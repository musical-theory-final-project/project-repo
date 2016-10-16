package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Brice on 10/16/16.
 */

public interface ScaleLevelRepository extends CrudRepository<ScaleLevel, Integer>{
    ScaleLevel findByLevelNumber(Integer levelNumber);
}
