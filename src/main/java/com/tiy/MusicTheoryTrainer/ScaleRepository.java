package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 10/16/16.
 */
public interface ScaleRepository extends CrudRepository<Scale, Integer> {
    List<Scale> findByScaleLevel(ScaleLevel scaleLevel);

}
