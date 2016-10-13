package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 10/13/16.
 */
public interface IntervalRepository extends CrudRepository<Interval, Integer>{
    List<Interval> findByIntervalLevel(IntervalLevel intervalLevel);
}
