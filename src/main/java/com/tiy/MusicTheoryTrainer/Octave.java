package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
@Table(name = "octaves")
public class Octave {

    @Id
    @GeneratedValue
    int octaveId;

    @Column(nullable = false)
    String octave;

    @ManyToOne
    IntervalLevel intervalLevel;

    public Octave(String octave, IntervalLevel intervalLevel) {
        this.octave = octave;
        this.intervalLevel = intervalLevel;
    }

    public Octave(String octave) {
        this.octave = octave;
    }

    public Octave() {
    }

    public int getOctaveId() {
        return octaveId;
    }

    public void setOctaveId(int octaveId) {
        this.octaveId = octaveId;
    }

    public IntervalLevel getIntervalLevel() {
        return intervalLevel;
    }

    public void setIntervalLevel(IntervalLevel intervalLevel) {
        this.intervalLevel = intervalLevel;
    }

    public String getOctave() {
        return octave;
    }

    public void setOctave(String octave) {
        this.octave = octave;
    }
}
