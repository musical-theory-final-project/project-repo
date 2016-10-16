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

	@Autowired
	ScaleLevelRepository scaleLevels;

	@Autowired
	ScaleRepository scales;

	@PostConstruct
	public void init() {
		IntervalLevel intervalLevel1 = new IntervalLevel(1);
		IntervalLevel intervalLevel2 = new IntervalLevel(2);
		IntervalLevel intervalLevel3 = new IntervalLevel(3);
		IntervalLevel intervalLevel4 = new IntervalLevel(4);

		ScaleLevel scaleLevel1 = new ScaleLevel(1);
		ScaleLevel scaleLevel2 = new ScaleLevel(2);
		ScaleLevel scaleLevel3 = new ScaleLevel(3);
		ScaleLevel scaleLevel4 = new ScaleLevel(4);

		if (intervalLevels.count() == 0) {
			intervalLevels.save(intervalLevel1);
			intervalLevels.save(intervalLevel2);
			intervalLevels.save(intervalLevel3);
			intervalLevels.save(intervalLevel4);
		}

		if (scaleLevels.count() == 0) {
			scaleLevels.save(scaleLevel1);
			scaleLevels.save(scaleLevel2);
			scaleLevels.save(scaleLevel3);
			scaleLevels.save(scaleLevel4);

		}

		if (notes.count() == 0) {
			Note noteAb = new Note("Ab");
			notes.save(noteAb);
			Note noteA = new Note("A");
			notes.save(noteA);
			Note noteAsharp = new Note("A#");
			notes.save(noteAsharp);
			Note noteBb = new Note("Bb");
			notes.save(noteBb);
			Note noteB = new Note("B");
			notes.save(noteB);
			Note noteCSharp = new Note("C#");
			notes.save(noteCSharp);
			Note noteC = new Note("C");
			notes.save(noteC);
			Note noteDb = new Note("Db");
			notes.save(noteDb);
			Note noteD = new Note("D");
			notes.save(noteD);
			Note noteDSharp = new Note("D#");
			notes.save(noteDSharp);
			Note noteEb = new Note("Eb");
			notes.save(noteEb);
			Note noteE = new Note("E");
			notes.save(noteE);
			Note noteF = new Note("F");
			notes.save(noteF);
			Note noteFSharp = new Note("F#");
			notes.save(noteFSharp);
			Note noteGb = new Note("Gb");
			notes.save(noteGb);
			Note noteG = new Note("G");
			notes.save(noteG);
			Note noteGSharp = new Note("G#");
			notes.save(noteGSharp);
		}

		if (octaves.count() == 0) {
			Octave octave0 = new Octave("0");
			octaves.save(octave0);
			Octave octave1 = new Octave("1");
			octaves.save(octave1);
			Octave octave2 = new Octave("2");
			octaves.save(octave2);
			Octave octave3 = new Octave("3", intervalLevel3, scaleLevel1);
			octaves.save(octave3);
			Octave octave4 = new Octave("4", intervalLevel1, scaleLevel2);
			octaves.save(octave4);
			Octave octave5 = new Octave("5", intervalLevel1, scaleLevel4);
			octaves.save(octave5);
			Octave octave6 = new Octave("6");
			octaves.save(octave6);
			Octave octave7 = new Octave("7");
			octaves.save(octave7);
		}

		if (intervals.count() == 0) {
			Interval intervalPerfectUnison = new Interval ("P1", intervalLevel1);
			intervals.save(intervalPerfectUnison);
			Interval intervalMinorSecond = new Interval ("m2", intervalLevel1);
			intervals.save(intervalMinorSecond);
			Interval intervalMajorSecond = new Interval ("M2", intervalLevel1);
			intervals.save(intervalMajorSecond);
			Interval intervalMinorThird = new Interval ("m3", intervalLevel1);
			intervals.save(intervalMinorThird);
			Interval intervalMajorThird = new Interval ("M3", intervalLevel1);
			intervals.save(intervalMajorThird);
			Interval intervalPerfectFourth = new Interval ("P4", intervalLevel2);
			intervals.save(intervalPerfectFourth);
			Interval intervalAugmentedFourth = new Interval ("TT", intervalLevel2);
			intervals.save(intervalAugmentedFourth);
			Interval intervalDiminishedFifth = new Interval ("TT", intervalLevel2);
			intervals.save(intervalDiminishedFifth);
			Interval intervalPerfectFifth= new Interval ("P5", intervalLevel2);
			intervals.save(intervalPerfectFifth);
			Interval intervalMinorSixth = new Interval ("m6", intervalLevel4);
			intervals.save(intervalMinorSixth);
			Interval intervalMajorSixth= new Interval ("M6", intervalLevel4);
			intervals.save(intervalMajorSixth);
			Interval intervalMinorSeventh= new Interval ("m7", intervalLevel4);
			intervals.save(intervalMinorSeventh);
			Interval intervalMajorSeventh= new Interval ("M7", intervalLevel4);
			intervals.save(intervalMajorSeventh);
			Interval intervalPerfectEighth= new Interval ("P8", intervalLevel4);
			intervals.save(intervalPerfectEighth);
		}

		if (scales.count() == 0) {
			Scale major = new Scale("major", scaleLevel1);
			scales.save(major);
			Scale minor = new Scale("minor", scaleLevel1);
			scales.save(minor);
			Scale majorPentatonic = new Scale("majorPentatonic", scaleLevel1);
			scales.save(majorPentatonic);
			Scale minorPentatonic = new Scale("minorPentatonic", scaleLevel2);
			scales.save(minorPentatonic);
			Scale dorian = new Scale("dorian", scaleLevel3);
			scales.save(dorian);
			Scale phrygian = new Scale("phrygian", scaleLevel4);
			scales.save(phrygian);
			Scale lydian = new Scale("lydian", scaleLevel3);
			scales.save(lydian);
			Scale mixolydian = new Scale("mixolydian", scaleLevel2);
			scales.save(mixolydian);
			Scale locrian = new Scale("locrian", scaleLevel4);
			scales.save(locrian);
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