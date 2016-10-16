package com.tiy.MusicTheoryTrainer;

/**
 * Created by Brice on 10/14/16.
 */
public class ReturnChordContainer {

    String Octave;

    String Note;

    String Chord;

    public ReturnChordContainer() {
    }

    public ReturnChordContainer(String octave, String note, String chord) {
        Octave = octave;
        Note = note;
        Chord = chord;
    }

    public String getOctave() {
        return Octave;
    }

    public void setOctave(String octave) {
        Octave = octave;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getChord() {
        return Chord;
    }

    public void setChord(String chord) {
        Chord = chord;
    }
}
