package com.tiy.MusicTheoryTrainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Brice on 10/16/16.
 */

@Entity
public class ScaleLevel {

    @Id
    @GeneratedValue
    int scaleLevelId;

    @Column(nullable = false)
    int levelNumber;

    public ScaleLevel() {
    }

    public ScaleLevel(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getScaleLevelId() {
        return scaleLevelId;
    }

    public void setScaleLevelId(int scaleLevelId) {
        this.scaleLevelId = scaleLevelId;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
