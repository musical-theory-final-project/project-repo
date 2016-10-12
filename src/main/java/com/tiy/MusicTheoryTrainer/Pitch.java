package com.tiy.MusicTheoryTrainer;

/**
 * Created by Brice on 10/11/16.
 */
public class Pitch {

    enum Note {
        C (),
        C_SHARP (),
        D_FLAT (),
        D (),
        D_SHARP (),
        E_FLAT (),
        E (),
        F (),
        F_SHARP (),
        G_FLAT (),
        G (),
        G_SHARP (),
        A_FLAT (),
        A (),
        A_SHARP (),
        B_FLAT (),
        B ()

        private final int noteValue;

        Note(int noteValue) {
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
        FOUR,
        FIVE
    };

    public Pitch(Note note, Interval interval, Octave octave) {

    }
}
