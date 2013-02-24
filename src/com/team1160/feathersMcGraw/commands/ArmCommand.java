package com.team1160.feathersMcGraw.commands;

public class ArmCommand {
	public double angle;      // Holds the data for the arm angle
	public boolean grip;      // Holds data on if the claw should be opened or closed
	
	public String toString(){                 // Print function helps a lot with debugging
		String output = "";                   // Initialize string
		output += "Arm Command \n";           // Lable for legibility
		output += "Angle: " + angle + "\n";   // The angle
		output += "Grip: " + grip + "\n";     // The grip
		return output;                        // Return the string
	}
	
	
}
