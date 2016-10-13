package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
public class Octave {

    @Id
    @GeneratedValue
    int id;

    @Column
    String octave;

    @ManyToOne
    IntervalLevel intervalLevel;

    public Octave(String octave) {
        this.octave = octave;
    }

    public Octave() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOctave() {
        return octave;
    }

    public void setOctave(String octave) {
        this.octave = octave;
    }
}
