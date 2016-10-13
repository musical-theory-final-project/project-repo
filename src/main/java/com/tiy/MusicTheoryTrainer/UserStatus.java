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

    public UserStatus() {
    }

    public UserStatus(User user, IntervalLevel intervalLevel) {
        this.user = user;
        this.intervalLevel = intervalLevel;
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
}
