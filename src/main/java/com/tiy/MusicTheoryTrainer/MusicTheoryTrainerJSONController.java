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

    @RequestMapping(path = "/login.json", method = RequestMethod.POST)
    public User login(@RequestBody LoginInfoPost loginInfo) {
        User myUser = users.findByEmail(loginInfo.getEmail());
        if (myUser == null) {
            System.out.println("User does not exist.");
        }
        return myUser;
    }

    @RequestMapping(path = "/register.json", method = RequestMethod.POST)
    public User register(@RequestBody RegisterInfoPost registerInfo) {
        User myUser = users.findByEmail(registerInfo.getEmail());
        if (myUser == null) {
            myUser = new User(registerInfo.getFirstName(), registerInfo.getLastName(), registerInfo.getEmail(), registerInfo.getPassword());
            IntervalLevel intLevel = intervalLevels.findByLevelNumber(1);
            UserStatus myStatus = new UserStatus(myUser, intLevel);
            users.save(myUser);
            userStatuses.save(myStatus);
        } else {
            System.out.println("This email already exists in the database.");
        }
        return myUser;
    }

    @RequestMapping(path = "/getIntervalLevel.json", method = RequestMethod.POST)
    public IntervalLevel intervalLevel(@RequestBody User myUser) {
        UserStatus userStatus = userStatuses.findByUser(myUser);
        IntervalLevel intLevel = intervalLevels.findByLevelNumber(userStatus.getIntervalLevel().levelNumber);
//        intervalLevels.save(intLevel);
        return intLevel;
    }



    // POST endpoint for app usage
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

    // GET endpoint to test JSON container
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

}
