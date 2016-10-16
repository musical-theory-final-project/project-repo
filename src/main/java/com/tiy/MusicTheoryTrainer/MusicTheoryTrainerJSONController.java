package com.tiy.MusicTheoryTrainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brice on 10/14/16.
 */

@RestController
public class MusicTheoryTrainerJSONController {

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

    @Autowired
    UserStatusRepository userStatuses;

    @Autowired
    ScaleLevelRepository scaleLevels;

    @Autowired
    ScaleRepository scales;

    // Login
    @RequestMapping(path = "/login.json", method = RequestMethod.POST)
    public User login(@RequestBody LoginInfoPost loginInfo) {
        User myUser = users.findByEmail(loginInfo.getEmail());
        if (myUser == null) {
            System.out.println("User does not exist.");
        }
        return myUser;
    }

    // Creates a new user and assigns them default UserStatus (includes IntervalLevel, ScaleLevel, and eventually ChordLevel.
    @RequestMapping(path = "/register.json", method = RequestMethod.POST)
    public User register(@RequestBody RegisterInfoPost registerInfo) {
        User myUser = users.findByEmail(registerInfo.getEmail());
        if (myUser == null) {
            myUser = new User(registerInfo.getFirstName(), registerInfo.getLastName(), registerInfo.getEmail(), registerInfo.getPassword());
            IntervalLevel intLevel = intervalLevels.findByLevelNumber(1);
            ScaleLevel scaleLevel = scaleLevels.findByLevelNumber(1);
            UserStatus myStatus = new UserStatus(myUser, intLevel, scaleLevel);
            users.save(myUser);
            userStatuses.save(myStatus);
        } else {
            System.out.println("This email already exists in the database.");
        }
        return myUser;
    }

    // Retrieve IntervalLevel based on user. Allows proper display of available levels for intervals.
    @RequestMapping(path = "/getIntervalLevel.json", method = RequestMethod.POST)
    public IntervalLevel intervalLevel(@RequestBody User myUser) {
        UserStatus userStatus = userStatuses.findByUser(myUser);
        IntervalLevel intLevel = intervalLevels.findByLevelNumber(userStatus.getIntervalLevel().levelNumber);
        return intLevel;
    }



    // POST interval endpoint for app usage
    @RequestMapping(path = "/getInterval.json", method = RequestMethod.POST)
    public ReturnIntervalContainer interval(@RequestBody IntervalLevel intLevel) {
        System.out.println("pinged");
        int levelNumber = intLevel.getLevelNumber();
        ArrayList<Interval> intervalList = new ArrayList<>();
        ArrayList<Octave> octaveList = new ArrayList<>();
        ArrayList<Note> noteList = new ArrayList<>();

        ReturnIntervalContainer returnIntervalContainer = new ReturnIntervalContainer();

        while (levelNumber != 0) {
            intLevel = intervalLevels.findByLevelNumber(levelNumber);

            List<Interval> interval = intervals.findByIntervalLevel(intLevel);
            for (Interval currentInterval : interval) {
                intervalList.add(currentInterval);
            }

            List<Octave> octave = octaves.findByIntervalLevel(intLevel);
            for (Octave currentOctave : octave) {
                octaveList.add(currentOctave);
            }
            levelNumber--;
        }

        Iterable<Note> myNotes = notes.findAll();

        for (Note currentNote : myNotes) {
            noteList.add(currentNote);
        }



        System.out.println(noteList.size());


        
        int intervalRNG = (int)((Math.random() * intervalList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());


        returnIntervalContainer.setInterval(intervalList.get(intervalRNG).getInterval());
        returnIntervalContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnIntervalContainer.setNote(noteList.get(noteRNG).getNote());

        return returnIntervalContainer;
    }

    // GET interval endpoint to test JSON container
    @RequestMapping(path = "/getIntervalEndPoint.json", method = RequestMethod.GET)
    public ReturnIntervalContainer interval() {

        IntervalLevel intLevel = intervalLevels.findByLevelNumber(2);

        int levelNumber = intLevel.getLevelNumber();
        ArrayList<Interval> intervalList = new ArrayList<>();
        ArrayList<Octave> octaveList = new ArrayList<>();
        ArrayList<Note> noteList = new ArrayList<>();

        ReturnIntervalContainer returnIntervalContainer = new ReturnIntervalContainer();

        while (levelNumber != 0) {
            intLevel = intervalLevels.findByLevelNumber(levelNumber);

            List<Interval> interval = intervals.findByIntervalLevel(intLevel);
            for (Interval currentInterval : interval) {
                intervalList.add(currentInterval);
            }

            List<Octave> octave = octaves.findByIntervalLevel(intLevel);
            for (Octave currentOctave : octave) {
                octaveList.add(currentOctave);
            }
            levelNumber--;
        }

        Iterable<Note> myNotes = notes.findAll();

        for (Note currentNote : myNotes) {
            noteList.add(currentNote);
        }



        System.out.println(noteList.size());



        int intervalRNG = (int)((Math.random() * intervalList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());

        returnIntervalContainer.setInterval(intervalList.get(intervalRNG).getInterval());
        returnIntervalContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnIntervalContainer.setNote(noteList.get(noteRNG).getNote());

        return returnIntervalContainer;
    }

    // retrieve ScaleLevel based on user.
    @RequestMapping(path = "/getScaleLevel.json", method = RequestMethod.POST)
    public ScaleLevel scaleLevel(@RequestBody User myUser) {
        UserStatus userStatus = userStatuses.findByUser(myUser);
        ScaleLevel scaleLevel = scaleLevels.findByLevelNumber(userStatus.getScaleLevel().levelNumber);
        return scaleLevel;
    }

    // POST scale endpoint for app usage
    @RequestMapping(path = "/getScale.json", method = RequestMethod.POST)
    public ReturnScaleContainer scale(@RequestBody ScaleLevel scaleLevel) {
        System.out.println("pinged");
        int levelNumber = scaleLevel.getLevelNumber();
        ArrayList<Scale> scaleList = new ArrayList<>();
        ArrayList<Octave> octaveList = new ArrayList<>();
        ArrayList<Note> noteList = new ArrayList<>();

        ReturnScaleContainer returnScaleContainer = new ReturnScaleContainer();

        while (levelNumber != 0) {
            scaleLevel = scaleLevels.findByLevelNumber(levelNumber);

            List<Scale> scale = scales.findByScaleLevel(scaleLevel);
            for (Scale currentScale : scale) {
                scaleList.add(currentScale);
            }

            List<Octave> octave = octaves.findByScaleLevel(scaleLevel);
            for (Octave currentOctave : octave) {
                octaveList.add(currentOctave);
            }
            levelNumber--;
        }

        Iterable<Note> myNotes = notes.findAll();

        for (Note currentNote : myNotes) {
            noteList.add(currentNote);
        }



        System.out.println(noteList.size());



        int scaleRNG = (int)((Math.random() * scaleList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());


        returnScaleContainer.setScale(scaleList.get(scaleRNG).getScale());
        returnScaleContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnScaleContainer.setNote(noteList.get(noteRNG).getNote());

        return returnScaleContainer;
    }

    // GET scale endpoint to test JSON container
    @RequestMapping(path = "/getScaleEndPoint.json", method = RequestMethod.GET)
    public ReturnScaleContainer scale() {

        ScaleLevel scaleLevel = scaleLevels.findByLevelNumber(2);

        int levelNumber = scaleLevel.getLevelNumber();
        ArrayList<Scale> scaleList = new ArrayList<>();
        ArrayList<Octave> octaveList = new ArrayList<>();
        ArrayList<Note> noteList = new ArrayList<>();

        ReturnScaleContainer returnScaleContainer = new ReturnScaleContainer();

        while (levelNumber != 0) {
            scaleLevel = scaleLevels.findByLevelNumber(levelNumber);

            List<Scale> scale = scales.findByScaleLevel(scaleLevel);
            for (Scale currentScale: scale) {
                scaleList.add(currentScale);
            }

            List<Octave> octave = octaves.findByScaleLevel(scaleLevel);
            for (Octave currentOctave : octave) {
                octaveList.add(currentOctave);
            }
            levelNumber--;
        }

        Iterable<Note> myNotes = notes.findAll();

        for (Note currentNote : myNotes) {
            noteList.add(currentNote);
        }



        System.out.println(noteList.size());



        int scaleRNG = (int)((Math.random() * scaleList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());

        returnScaleContainer.setScale(scaleList.get(scaleRNG).getScale());
        returnScaleContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnScaleContainer.setNote(noteList.get(noteRNG).getNote());

        return returnScaleContainer;
    }

}
