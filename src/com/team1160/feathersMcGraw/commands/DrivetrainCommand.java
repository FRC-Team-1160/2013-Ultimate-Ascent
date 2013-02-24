package com.team1160.feathersMcGraw.commands;

public class DrivetrainCommand {
	public double left;
	public double right;
	
	public String toString(){
		String output = "";
		output+="Drivetrain Command\n";
		output+="Left: " + left + "\n";
		output+="Right: " + right + "\n";
		return output;
	}
}
