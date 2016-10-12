package com.tiy.MusicTheoryTrainer;

/**
 * Created by Brice on 10/11/16.
 */
public class Pitch {

    enum Note {
        C ("C"),
        C_SHARP ("C#"),
        D_FLAT ("Db"),
        D ("D"),
        D_SHARP ("D#"),
        E_FLAT ("Eb"),
        E ("E"),
        F ("F"),
        F_SHARP ("F#"),
        G_FLAT ("Gb"),
        G ("G"),
        G_SHARP ("G#"),
        A_FLAT ("Ab"),
        A ("A"),
        A_SHARP ("A#"),
        B_FLAT ("Bb"),
        B ("B");

        private final String noteValue;

        Note(String noteValue) {
            this.noteValue = noteValue;
        }

    };

    enum Interval {
        PERFECT_UNISON,
        MINOR_SECOND,
        MAJOR_SECOND,
        MINOR_THIRD,
        MAJOR_THIRD
    }

    enum Octave {
        FOUR (),
        FIVE
    };

    public Pitch(Note note, Interval interval, Octave octave) {

    }
}
