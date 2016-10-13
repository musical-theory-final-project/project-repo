package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
public class IntervalLevel implements Level {

    @Id
    @GeneratedValue
    int id;

    @Column
    String levelNumber;

    public IntervalLevel() {
    }

    public IntervalLevel(String levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(String levelNumber) {
        this.levelNumber = levelNumber;
    }
}
