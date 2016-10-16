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
    ScaleRepository scales;

    @Autowired
    ScaleLevelRepository scaleLevels;

    @Autowired
    ChordRepository chords;

    @Autowired
    ChordLevelRepository chordLevels;

    @Autowired
    UserStatusRepository userStatuses;



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

    // Retrieve ScaleLevel based on user. Allows proper display of available levels for scales.
    @RequestMapping(path = "/getScaleLevel.json", method = RequestMethod.POST)
    public ScaleLevel scaleLevel(@RequestBody User myUser) {
        UserStatus userStatus = userStatuses.findByUser(myUser);
        ScaleLevel scaleLevel = scaleLevels.findByLevelNumber(userStatus.getScaleLevel().levelNumber);
        return scaleLevel;
    }

    // Retrieve ChordLevel based on user. Allows proper display of available levels for chords.
    @RequestMapping(path = "/getChordLevel.json", method = RequestMethod.POST)
    public ChordLevel chordLevel(@RequestBody User myUser) {
        UserStatus userStatus = userStatuses.findByUser(myUser);
        ChordLevel chordLevel = chordLevels.findByLevelNumber(userStatus.getChordLevel().levelNumber);
        return chordLevel;
    }



    // Allow the user to reach next level in Interval training up to level4
    @RequestMapping(path = "/nextIntervalLevel.json", method = RequestMethod.POST)
    public IntervalLevel nextIntervalLevel(@RequestBody User myUser) {
        UserStatus currentUserStatus = userStatuses.findByUser(myUser);
        IntervalLevel currentIntervalLevel = currentUserStatus.getIntervalLevel();
        if (currentIntervalLevel.getLevelNumber() <= 4) {
            int newIntervalLevel = currentIntervalLevel.getLevelNumber() + 1;
            currentIntervalLevel = intervalLevels.findByLevelNumber(newIntervalLevel);
        }
        currentUserStatus.setIntervalLevel(currentIntervalLevel);
        userStatuses.save(currentUserStatus);

        return currentIntervalLevel;
    }

    // Allow the user to reach next level in Scale training up to level4
    @RequestMapping(path = "/nextScaleLevel.json", method = RequestMethod.POST)
    public ScaleLevel nextScaleLevel(@RequestBody User myUser) {
        UserStatus currentUserStatus = userStatuses.findByUser(myUser);
        ScaleLevel currentScaleLevel = currentUserStatus.getScaleLevel();
        if (currentScaleLevel.getLevelNumber() <= 4) {
            int newScaleLevel = currentScaleLevel.getLevelNumber() + 1;
            currentScaleLevel= scaleLevels.findByLevelNumber(newScaleLevel);
        }
        currentUserStatus.setScaleLevel(currentScaleLevel);
        userStatuses.save(currentUserStatus);

        return currentScaleLevel;
    }

    // Allow the user to reach next level in Chord training up to level4
    @RequestMapping(path = "/nextChordLevel.json", method = RequestMethod.POST)
    public ChordLevel nextChordLevel(@RequestBody User myUser) {
        UserStatus currentUserStatus = userStatuses.findByUser(myUser);
        ChordLevel currentChordLevel = currentUserStatus.getChordLevel();
        if (currentChordLevel.getLevelNumber() <= 4) {
            int newChordLevel = currentChordLevel.getLevelNumber() + 1;
            currentChordLevel= chordLevels.findByLevelNumber(newChordLevel);
        }
        currentUserStatus.setChordLevel(currentChordLevel);
        userStatuses.save(currentUserStatus);

        return currentChordLevel;
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


    // POST scale endpoint for app usage
    @RequestMapping(path = "/getChord.json", method = RequestMethod.POST)
    public ReturnChordContainer scale(@RequestBody ChordLevel chordLevel) {
        System.out.println("pinged");
        int levelNumber = chordLevel.getLevelNumber();
        ArrayList<Chord> chordList = new ArrayList<>();
        ArrayList<Octave> octaveList = new ArrayList<>();
        ArrayList<Note> noteList = new ArrayList<>();

        ReturnChordContainer returnChordContainer = new ReturnChordContainer();

        while (levelNumber != 0) {
            chordLevel = chordLevels.findByLevelNumber(levelNumber);

            List<Chord> chord = chords.findByChordLevel(chordLevel);
            for (Chord currentChord : chord) {
                chordList.add(currentChord);
            }

            List<Octave> octave = octaves.findByChordLevel(chordLevel);
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



        int chordRNG = (int)((Math.random() * chordList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());


        returnChordContainer.setChord(chordList.get(chordRNG).getChord());
        returnChordContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnChordContainer.setNote(noteList.get(noteRNG).getNote());

        return returnChordContainer;
    }

    // GET scale endpoint to test JSON container
    @RequestMapping(path = "/getChordEndPoint.json", method = RequestMethod.GET)
    public ReturnChordContainer chord() {

        ChordLevel chordLevel = chordLevels.findByLevelNumber(2);

        int levelNumber = chordLevel.getLevelNumber();
        ArrayList<Chord> chordList = new ArrayList<>();
        ArrayList<Octave> octaveList = new ArrayList<>();
        ArrayList<Note> noteList = new ArrayList<>();

        ReturnChordContainer returnChordContainer = new ReturnChordContainer();

        while (levelNumber != 0) {
            chordLevel = chordLevels.findByLevelNumber(levelNumber);

            List<Chord> chord = chords.findByChordLevel(chordLevel);
            for (Chord currentChord : chord) {
                chordList.add(currentChord);
            }

            List<Octave> octave = octaves.findByChordLevel(chordLevel);
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



        int chordRNG = (int)((Math.random() * chordList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());

        returnChordContainer.setChord(chordList.get(chordRNG).getChord());
        returnChordContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnChordContainer.setNote(noteList.get(noteRNG).getNote());

        return returnChordContainer;
    }

}
