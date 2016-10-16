package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 10/13/16.
 */
public interface OctaveRepository extends CrudRepository<Octave, Integer>{
    List<Octave> findByIntervalLevel(IntervalLevel intervalLevel);
    List<Octave> findByScaleLevel(ScaleLevel scaleLevel);
//    List<Octave> findByChordLevel(ChordLevel chordLevel);
}