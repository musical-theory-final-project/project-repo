package com.tiy.MusicTheoryTrainer;

import javax.persistence.*;

/**
 * Created by Brice on 10/16/16.
 */

@Entity
public class Scale {

    @Id
    @GeneratedValue
    int scaleId;

    @Column
    String scale;

    @ManyToOne
    ScaleLevel scaleLevel;

    public Scale() {
    }

    public Scale(String scale) {
        this.scale = scale;
    }

    public Scale(String scale, ScaleLevel scaleLevel) {
        this.scale = scale;
        this.scaleLevel = scaleLevel;
    }

    public ScaleLevel getScaleLevel() {
        return scaleLevel;
    }

    public void setScaleLevel(ScaleLevel scaleLevel) {
        this.scaleLevel = scaleLevel;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
