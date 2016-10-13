package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/11/16.
 */

@Entity
@Table(name = "interval")
public class Interval {

    @Id
    @GeneratedValue
    int intervalId;

    @Column
    String interval;

    @ManyToOne
    IntervalLevel intervalLevel;

    public Interval() {
    }

    public Interval(String interval, IntervalLevel intervalLevel) {
        this.interval = interval;
        this.intervalLevel = intervalLevel;
    }

    public int getIntervalId() {
        return intervalId;
    }

    public void setIntervalId(int intervalId) {
        this.intervalId = intervalId;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
