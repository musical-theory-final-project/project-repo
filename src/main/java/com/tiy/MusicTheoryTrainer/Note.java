package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
public class Note {

    @Id
    @GeneratedValue
    int id;

    @Column
    String note;

    public Note() {
    }

    public Note(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
