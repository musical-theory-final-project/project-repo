package com.tiy.MusicTheoryTrainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
public class ChordLevel {

    @Id
    @GeneratedValue
    int chordLevelId;

    @Column(nullable = false)
    int levelNumber;

    public ChordLevel() {
    }

    public ChordLevel(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getChordLevelId() {
        return chordLevelId;
    }

    public void setChordLevelId(int chordLevelId) {
        this.chordLevelId = chordLevelId;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
