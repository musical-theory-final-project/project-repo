package com.tiy.MusicTheoryTrainer;


import javax.persistence.*;

/**
 * Created by Brice on 10/11/16.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    int userId;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    int currentIntervalLevel = 1;

    @Column(nullable = false)
    int currentScaleLevel = 1;

    @Column(nullable = false)
    int currentChordLevel = 1;

    public User() {

    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrentIntervalLevel() {
        return currentIntervalLevel;
    }

    public void setCurrentIntervalLevel(int currentIntervalLevel) {
        this.currentIntervalLevel = currentIntervalLevel;
    }

    public int getCurrentScaleLevel() {
        return currentScaleLevel;
    }

    public void setCurrentScaleLevel(int currentScaleLevel) {
        this.currentScaleLevel = currentScaleLevel;
    }

    public int getCurrentChordLevel() {
        return currentChordLevel;
    }

    public void setCurrentChordLevel(int currentChordLevel) {
        this.currentChordLevel = currentChordLevel;
    }
}
