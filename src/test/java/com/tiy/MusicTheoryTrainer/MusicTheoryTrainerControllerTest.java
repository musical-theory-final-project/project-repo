package com.tiy.MusicTheoryTrainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Brice on 10/13/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicTheoryTrainerControllerTest {

    @Autowired
    UserRepository users;

    @Autowired
    NoteRepository notes;

    @Autowired
    OctaveRepository octaves;

    @Autowired
    IntervalRepository intervals;

    @Autowired
    IntervalLevelRepository intervalLevels;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }



    @Test
    public void pullNotes() {
        Iterable<Note> noteList = notes.findAll();
        ArrayList<Note> myNotes = new ArrayList<>();
        for (Note currentNote : noteList) {
            myNotes.add(currentNote);
            System.out.println(currentNote.getNote());
        }
    }

    @Test
    public void pullOctaves() {
        Iterable<Octave> octaveList = octaves.findAll();
        ArrayList<Octave> myOctaves = new ArrayList<>();
        for (Octave currentOctave : octaveList) {
            myOctaves.add(currentOctave);
            System.out.println(currentOctave.getOctave());
        }
    }

    @Test
    public void pullIntervals() {
        Iterable<Interval> intervalList = intervals.findAll();
        ArrayList<Interval> myIntervals = new ArrayList<>();
        for (Interval currentInterval : intervalList) {
            myIntervals.add(currentInterval);
            System.out.println(currentInterval.getInterval());
        }
    }

    @Test
    public void intervalLevelFunctionality() {
        UserStatus myStatus = new UserStatus();
        
    }

    @Test
    public void userFunctionality() {
        User myUser = new User();
        myUser.setLastName("tester");
        myUser.setFirstName("Johny");
        myUser.setEmail("user@test");
        myUser.setPassword("pword");

        try {
            myUser = users.save(myUser);
            User testUser = users.findByEmail(myUser.getEmail());
            System.out.println("myUser: " + myUser.getUserId() + " - " + myUser.getEmail() + " - " + myUser.getFirstName() + " - " + myUser.getLastName() + " - " + myUser.getPassword());
            System.out.println("testUser: " + testUser.getUserId() + " - " + testUser.getEmail() + " - " + testUser.getFirstName() + " - " + testUser.getLastName() + " - " + testUser.getPassword());


            assertEquals(myUser.getEmail(), testUser.getEmail());
        } finally {
            users.delete(myUser.getUserId());
        }
    }

}