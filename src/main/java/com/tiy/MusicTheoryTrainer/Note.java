package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue
    int noteId;

    @Column(nullable = false)
    String note;

    public Note() {
    }

    public Note(String note) {
        this.note = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
