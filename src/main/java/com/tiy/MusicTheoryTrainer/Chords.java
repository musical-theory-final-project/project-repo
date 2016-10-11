package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/11/16.
 */

@Entity
public class Chords {

    @Id
    @GeneratedValue
    int Id;

    @OneToOne
    User user;

    @Column
    boolean level1;

    @Column
    boolean level2;

    @Column
    boolean level3;

    @Column
    boolean level4;

    public Chords() {
    }

    public Chords(User user, boolean level1, boolean level2, boolean level3, boolean level4) {
        this.user = user;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLevel1() {
        return level1;
    }

    public void setLevel1(boolean level1) {
        this.level1 = level1;
    }

    public boolean isLevel2() {
        return level2;
    }

    public void setLevel2(boolean level2) {
        this.level2 = level2;
    }

    public boolean isLevel3() {
        return level3;
    }

    public void setLevel3(boolean level3) {
        this.level3 = level3;
    }

    public boolean isLevel4() {
        return level4;
    }

    public void setLevel4(boolean level4) {
        this.level4 = level4;
    }
}
