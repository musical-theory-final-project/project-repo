package com.tiy.MusicTheoryTrainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public ReturnLoginStatus login(@RequestBody LoginInfoPost loginInfo, HttpSession session) {
        User myUser = users.findByEmail(loginInfo.getEmail());
        ReturnLoginStatus loginStatus = new ReturnLoginStatus();
        if (myUser == null) {
            System.out.println("User does not exist.");
        }
        String incomingPassword = loginInfo.getPassword();
        if (!myUser.getPassword().equals(incomingPassword)) {
            loginStatus.setErrorMessage("Password does not match");
            loginStatus.setUserStatus(null);
            return loginStatus;
        }
        UserStatus myStatus = userStatuses.findByUser(myUser);
        loginStatus.setUserStatus(myStatus);
        session.setAttribute("user", myUser);
        return loginStatus;
    }

    // Creates a new user and assigns them default UserStatus (includes IntervalLevel, ScaleLevel, and eventually ChordLevel.
    @RequestMapping(path = "/register.json", method = RequestMethod.POST)
    public UserStatus register(@RequestBody RegisterInfoPost registerInfo, HttpSession session) {
        User myUser = users.findByEmail(registerInfo.getEmail());
        UserStatus myStatus = new UserStatus();
        if (myUser == null) {
            myUser = new User(registerInfo.getFirstName(), registerInfo.getLastName(), registerInfo.getEmail(), registerInfo.getPassword());
            IntervalLevel intLevel = intervalLevels.findByLevelNumber(1);
            ScaleLevel scaleLevel = scaleLevels.findByLevelNumber(1);
            myStatus = new UserStatus(myUser, intLevel, scaleLevel);
            users.save(myUser);
            userStatuses.save(myStatus);
            session.setAttribute("user", myUser);
        } else {
            System.out.println("This email already exists in the database.");
        }
        return myStatus;
    }

    //returns a User object that is currently in the session
    @RequestMapping (path = "/getUserFromSession.json", method = RequestMethod.POST)
    public UserStatus getUserFromSession (HttpSession session) {
        User myUser = (User) session.getAttribute("user");
        UserStatus myStatus = userStatuses.findByUser(myUser);
        return myStatus;
    }

    //Retrieve desired IntervalLevel based on user input
    @RequestMapping(path = "/getDesiredLevel.json", method = RequestMethod.POST)
    public IntervalLevel getDesiredLevel(@RequestBody User user) {
        users.save(user);
        IntervalLevel intLevel = intervalLevels.findByLevelNumber(user.currentIntervalLevel);
        return intLevel;

    }

    // Retrieve max IntervalLevel based on user. Allows proper display of available levels for intervals.
    @RequestMapping(path = "/getIntervalLevel.json", method = RequestMethod.POST)
    public IntervalLevel intervalLevel(@RequestBody User myUser) {
        IntervalLevel intLevel = intervalLevels.findByLevelNumber(myUser.currentIntervalLevel);
        return intLevel;
    }

    // Retrieve ScaleLevel based on user. Allows proper display of available levels for scales.
    @RequestMapping(path = "/getScaleLevel.json", method = RequestMethod.POST)
    public ScaleLevel scaleLevel(@RequestBody User myUser) {
        ScaleLevel scaleLevel = scaleLevels.findByLevelNumber(myUser.currentScaleLevel);
        return scaleLevel;
    }

    // Retrieve ChordLevel based on user. Allows proper display of available levels for chords.
    @RequestMapping(path = "/getChordLevel.json", method = RequestMethod.POST)
    public ChordLevel chordLevel(@RequestBody User myUser) {
        ChordLevel chordLevel = chordLevels.findByLevelNumber(myUser.currentChordLevel);
        return chordLevel;
    }



    // Allow the user to reach next level in Interval training up to level4
    @RequestMapping(path = "/nextIntervalLevel.json", method = RequestMethod.POST)
    public IntervalLevel nextIntervalLevel(@RequestBody User myUser) {
        UserStatus currentUserStatus = userStatuses.findByUser(myUser);
        IntervalLevel currentIntervalLevel = currentUserStatus.getIntervalLevel();
        if (myUser.currentIntervalLevel == currentIntervalLevel.getLevelNumber()) {
            if (currentIntervalLevel.getLevelNumber() <= 4) {
                int newIntervalLevel = currentIntervalLevel.getLevelNumber() + 1;
                currentIntervalLevel = intervalLevels.findByLevelNumber(newIntervalLevel);
            }
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
        if (myUser.currentScaleLevel == currentScaleLevel.getLevelNumber()) {
            if (currentScaleLevel.getLevelNumber() <= 4) {
                int newScaleLevel = currentScaleLevel.getLevelNumber() + 1;
                currentScaleLevel = scaleLevels.findByLevelNumber(newScaleLevel);
            }
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
        if (myUser.currentChordLevel == currentChordLevel.getLevelNumber()) {
            if (currentChordLevel.getLevelNumber() <= 4) {
                int newChordLevel = currentChordLevel.getLevelNumber() + 1;
                currentChordLevel = chordLevels.findByLevelNumber(newChordLevel);
            }
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

        int intervalRNG = (int)((Math.random() * intervalList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());

        returnIntervalContainer.setInterval(intervalList.get(intervalRNG).getInterval());
        returnIntervalContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnIntervalContainer.setNote(noteList.get(noteRNG).getNote());

        return returnIntervalContainer;
    }

    //Get endpoint to get a list of all intervals by level
    @RequestMapping(path = "/getListOfIntervals.json", method = RequestMethod.POST)
    public IntervalListContainer getListOfIntervals (@RequestBody User activeUser) {
        User myUser = users.findByEmail(activeUser.getEmail());
        IntervalLevel intLevel = intervalLevels.findByLevelNumber(myUser.currentIntervalLevel);
        int levelNumber = intLevel.getLevelNumber();

        IntervalListContainer myContainer = new IntervalListContainer();

        while (levelNumber != 0) {
            intLevel = intervalLevels.findByLevelNumber(levelNumber);

            List<Interval> interval = intervals.findByIntervalLevel(intLevel);
            for (Interval currentInterval : interval) {
                myContainer.myIntervals.add(currentInterval);
            }
            levelNumber--;
        }
        return myContainer;
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
    public ReturnChordContainer chord(@RequestBody ChordLevel chordLevel) {
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

        int chordRNG = (int)((Math.random() * chordList.size()));
        int octaveRNG = (int) (Math.random() * octaveList.size());
        int noteRNG = (int) (Math.random() * noteList.size());

        returnChordContainer.setChord(chordList.get(chordRNG).getChord());
        returnChordContainer.setOctave(octaveList.get(octaveRNG).getOctave());
        returnChordContainer.setNote(noteList.get(noteRNG).getNote());

        return returnChordContainer;
    }

}
