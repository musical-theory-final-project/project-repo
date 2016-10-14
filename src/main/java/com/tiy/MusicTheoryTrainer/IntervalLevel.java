package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
public class IntervalLevel {

    @Id
    @GeneratedValue
    int intervalLevelId;

    @Column(nullable = false)
    int levelNumber;

    public IntervalLevel() {
    }

    public IntervalLevel(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getId() {
        return intervalLevelId;
    }

    public void setId(int id) {
        this.intervalLevelId = id;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getIntervalLevelId() {
        return intervalLevelId;
    }

    public void setIntervalLevelId(int intervalLevelId) {
        this.intervalLevelId = intervalLevelId;
    }
}
