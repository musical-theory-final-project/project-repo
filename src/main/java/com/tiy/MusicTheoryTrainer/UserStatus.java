package com.tiy.MusicTheoryTrainer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
public class UserStatus {

    @Id
    @GeneratedValue
    int userStatusId;

    @ManyToOne
    User user;

    @ManyToOne
    IntervalLevel intervalLevel;

    @ManyToOne
    ScaleLevel scaleLevel;

    @ManyToOne
    ChordLevel chordLevel;

    public UserStatus() {
    }

    public UserStatus(User user, IntervalLevel intervalLevel, ScaleLevel scaleLevel) {
        this.user = user;
        this.intervalLevel = intervalLevel;
        this.scaleLevel = scaleLevel;
    }

    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public ScaleLevel getScaleLevel() {
        return scaleLevel;
    }

    public void setScaleLevel(ScaleLevel scaleLevel) {
        this.scaleLevel = scaleLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IntervalLevel getIntervalLevel() {
        return intervalLevel;
    }

    public void setIntervalLevel(IntervalLevel intervalLevel) {
        this.intervalLevel = intervalLevel;
    }

    public ChordLevel getChordLevel() {
        return chordLevel;
    }

    public void setChordLevel(ChordLevel chordLevel) {
        this.chordLevel = chordLevel;
    }
}
