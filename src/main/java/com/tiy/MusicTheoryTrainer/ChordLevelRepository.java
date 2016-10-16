package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Brice on 10/13/16.
 */

public interface ChordLevelRepository extends CrudRepository<ChordLevel, Integer>{
    ChordLevel findByLevelNumber(Integer levelNumber);
}
