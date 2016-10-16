package com.tiy.MusicTheoryTrainer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 10/13/16.
 */
public interface ChordRepository extends CrudRepository<Chord, Integer>{
    List<Chord> findByChordLevel(ChordLevel chordLevel);
}
