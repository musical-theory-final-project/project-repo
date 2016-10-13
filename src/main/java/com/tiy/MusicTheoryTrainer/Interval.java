package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/11/16.
 */

@Entity
public class Interval {

    @Id
    @GeneratedValue
    int id;

    @Column
    String interval;

    @ManyToOne
    IntervalLevel intervalLevel;

    public Interval() {
    }

    public Interval(String interval) {
        this.interval = interval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
