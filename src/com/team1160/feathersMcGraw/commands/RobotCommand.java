package com.team1160.feathersMcGraw.commands;

/*
 * This is the file that contains all of the commands
 * for the robot. It is created by either the teleop
 * or auto manager depending on which stage we are in
 * it is taken by the output manager and turned into
 * -gasp- output. 
 * 
 * @Author Wallace
 */

public class RobotCommand {
	
	public ArmCommand armCommand;
	public DrivetrainCommand drivetrainCommand;
	public PulleySystemCommand pulleySystemCommand;
	
	public RobotCommand(){
		this.armCommand = new ArmCommand();
		this.drivetrainCommand = new DrivetrainCommand();
		this.pulleySystemCommand = new PulleySystemCommand();
	}
	
	public String toString(){
		String output = "";
		output += "/---------------\n";
		output += "Robot Command";
		output += drivetrainCommand;
		output += pulleySystemCommand;
		output += armCommand;
		return output;
	}
	
}
