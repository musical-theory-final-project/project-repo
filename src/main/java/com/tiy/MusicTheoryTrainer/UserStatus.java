package com.tiy.MusicTheoryTrainer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Brice on 10/13/16.
 */

@Entity
public class UserStatus {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @ManyToOne
    Level level;
}
