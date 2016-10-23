package com.tiy.MusicTheoryTrainer;

import java.util.ArrayList;

/**
 * Created by Brett on 10/21/16.
 */
public class ListContainer {
	ArrayList<Interval> myIntervals = new ArrayList<>();

	public ArrayList<Scale> myScales = new ArrayList<>();

	public void setMyIntervals(ArrayList<Interval> myIntervals) {
		this.myIntervals = myIntervals;
	}

	public ArrayList<Interval> getMyIntervals() {
		return myIntervals;
	}

	public ArrayList<Scale> getMyScales() {
		return myScales;
	}

	public void setMyScales(ArrayList<Scale> myScales) {
		this.myScales = myScales;
	}

	public ListContainer() {
	}
}


