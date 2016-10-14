package com.tiy.MusicTheoryTrainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by RdDvls on 10/10/16.
 */
@Controller
public class MusicTheoryTrainerController {

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

	@PostConstruct
	public void init() {
		IntervalLevel level1 = new IntervalLevel(1);
		IntervalLevel level2 = new IntervalLevel(2);
		IntervalLevel level3 = new IntervalLevel(3);
		IntervalLevel level4 = new IntervalLevel(4);

		if (intervals.count() == 0) {
			intervalLevels.save(level1);
			intervalLevels.save(level2);
			intervalLevels.save(level3);
			intervalLevels.save(level4);
		}

		if (notes.count() == 0) {
			Note noteA = new Note("A");
			notes.save(noteA);
			Note noteB = new Note("B");
			notes.save(noteB);
			Note noteC = new Note("C");
			notes.save(noteC);
			Note noteD = new Note("D");
			notes.save(noteD);
			Note noteE = new Note("E");
			notes.save(noteE);
			Note noteF = new Note("F");
			notes.save(noteF);
			Note noteG = new Note("G");
			notes.save(noteG);
		}

		if (octaves.count() == 0) {
			Octave octave0 = new Octave("0");
			octaves.save(octave0);
			Octave octave1 = new Octave("1");
			octaves.save(octave1);
			Octave octave2 = new Octave("2");
			octaves.save(octave2);
			Octave octave3 = new Octave("3", level3);
			octaves.save(octave3);
			Octave octave4 = new Octave("4", level1);
			octaves.save(octave4);
			Octave octave5 = new Octave("5", level1);
			octaves.save(octave5);
			Octave octave6 = new Octave("6");
			octaves.save(octave6);
			Octave octave7 = new Octave("7");
			octaves.save(octave7);
		}

		if (intervals.count() == 0) {
			Interval intervalPerfectUnison = new Interval ("P1", level1);
			intervals.save(intervalPerfectUnison);
			Interval intervalMinorSecond = new Interval ("m2", level1);
			intervals.save(intervalMinorSecond);
			Interval intervalMajorSecond = new Interval ("M2", level1);
			intervals.save(intervalMajorSecond);
			Interval intervalMinorThird = new Interval ("m3", level1);
			intervals.save(intervalMinorThird);
			Interval intervalMajorThird = new Interval ("M3", level1);
			intervals.save(intervalMajorThird);
			Interval intervalPerfectFourth = new Interval ("P4", level2);
			intervals.save(intervalPerfectFourth);
			Interval intervalAugmentedFourth = new Interval ("TT", level2);
			intervals.save(intervalAugmentedFourth);
			Interval intervalDiminishedFifth = new Interval ("TT", level2);
			intervals.save(intervalDiminishedFifth);
			Interval intervalPerfectFifth= new Interval ("P5", level2);
			intervals.save(intervalPerfectFifth);
			Interval intervalMinorSixth = new Interval ("m6", level4);
			intervals.save(intervalMinorSixth);
			Interval intervalMajorSixth= new Interval ("M6", level4);
			intervals.save(intervalMajorSixth);
			Interval intervalMinorSeventh= new Interval ("m7", level4);
			intervals.save(intervalMinorSeventh);
			Interval intervalMajorSeventh= new Interval ("M7", level4);
			intervals.save(intervalMajorSeventh);
			Interval intervalPerfectEighth= new Interval ("P8", level4);
			intervals.save(intervalPerfectEighth);
		}
	}

	@RequestMapping(path = "/midi", method = RequestMethod.GET)
	public String midiPage(Model model, HttpSession session) {
		return "midi";
	}

	@RequestMapping(path = "/testhome", method = RequestMethod.GET)   //Created so clay can test variable assignment in angular
	public String testHomePage(Model model, HttpSession session){
		return "testhome";
	}



	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		return "home";
	}

//	@RequestMapping(path = "/register", method = RequestMethod.GET)
//	public String newPlayer(HttpSession session, String firstName, String lastName, String email, String password){
//		User user = users.findByEmail(email);
//		if(user == null){
//			user = new User(firstName,lastName,email,password);
//			users.save(user);
//		}
//		session.setAttribute("user",user);
//		return "redirect:/gamemenu";
//	}
}