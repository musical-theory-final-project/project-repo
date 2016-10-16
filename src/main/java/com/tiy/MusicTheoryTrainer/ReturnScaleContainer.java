package com.tiy.MusicTheoryTrainer;

/**
 * Created by Brice on 10/14/16.
 */
public class ReturnScaleContainer {

    String Octave;

    String Note;

    String Scale;

    public ReturnScaleContainer() {
    }

    public ReturnScaleContainer(String octave, String note, String scale) {
        Octave = octave;
        Note = note;
        Scale = scale;
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

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }
}
