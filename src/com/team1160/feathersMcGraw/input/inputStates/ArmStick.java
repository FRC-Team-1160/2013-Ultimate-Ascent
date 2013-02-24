package com.team1160.feathersMcGraw.input.inputStates;

/*
 * Simple input state for
 * holding the data sent off
 * from our joysticks. In an
 * easy to work with format
 * oh and the toString function
 * is nice.
 * 
 * @Author Wallace
 */

public class ArmStick {

	public boolean released;       // This booleans turn our buttons on the js
	public boolean lockRelease;    // into toggle buttons. These are used later
	                               // to gauge what state this js is in and
	                               // wether or not to engag the locks
	public double x;               // What they sound like, these are the 
	public double y;               // x and y axis
	
	public String name;            // the name has one job, to make out put easier
	                               // to read seeing as there will be more then one
	                               // of these sticks
	public ArmStick(String name) { // constructor stops null pointers
		this.name = name;          // and assigns the name
		x = 0;
		y = 0;
		released = true;
		lockRelease = true;
	}

	public void setReleaseStatus(boolean button){    // Used to make later code look nicer
		if(button && released){                      // basically toggles the toggle
			released = false;
		}else if(!button && !released){
			released = true;
		}
	}
	
	public void setLockStatus(boolean button){       // same as above ^^^^^^^^
		if(button && lockRelease){
			lockRelease = false;
		}else if(!button && !lockRelease){
			lockRelease = false;
		}
	}
	
	
	public String toString(){                               // used for debug but basically is all the
		String output="";                                   // data for text output
		output+="--Arm Stick " + name + "\n";
		output+="Pulley Released: " + released + "\n";
		output+="Lock Released:   " + lockRelease + "\n";
		output+="X:               " + x + "\n";
		output+="Y:               " + y + "\n";
		return output;
	}
	
}
