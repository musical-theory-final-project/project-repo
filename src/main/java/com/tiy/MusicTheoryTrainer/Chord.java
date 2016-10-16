package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/11/16.
 */

@Entity
@Table(name = "chords")
public class Chord {

    @Id
    @GeneratedValue
    int chordId;

    @Column(nullable = false)
    String chord;

    @ManyToOne
    ChordLevel chordLevel;

    public Chord() {
    }

    public Chord(String chord, ChordLevel chordLevel) {
        this.chord = chord;
        this.chordLevel = chordLevel;
    }

    public int getChordId() {
        return chordId;
    }

    public void setChordId(int chordId) {
        this.chordId = chordId;
    }

    public String getChord() {
        return chord;
    }

    public void setChord(String chord) {
        this.chord = chord;
    }

    public ChordLevel getChordLevel() {
        return chordLevel;
    }

    public void setChordLevel(ChordLevel chordLevel) {
        this.chordLevel = chordLevel;
    }
}
