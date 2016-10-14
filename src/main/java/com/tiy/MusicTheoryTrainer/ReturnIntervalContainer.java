package com.tiy.MusicTheoryTrainer;

/**
 * Created by Brice on 10/14/16.
 */
public class ReturnIntervalContainer {

    String Octave;

    String Note;

    String Interval;

    public ReturnIntervalContainer() {
    }

    public ReturnIntervalContainer(String octave, String note, String interval) {
        Octave = octave;
        Note = note;
        Interval = interval;
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

    public String getInterval() {
        return Interval;
    }

    public void setInterval(String interval) {
        Interval = interval;
    }
}
