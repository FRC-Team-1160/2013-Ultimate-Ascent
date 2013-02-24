package com.team1160.feathersMcGraw.commands;

/*
 * Our robot has three pulleys on it,
 * to make it easier to work with we 
 * wrapped them up in here 
 * 
 * @Author Wallace
 */

public class PulleySystemCommand {
	public PulleyCommand top;
	public PulleyCommand right;
	public PulleyCommand left;
	
	public PulleySystemCommand(){
                           top = new PulleyCommand();
                           right = new PulleyCommand();
                           left = new PulleyCommand();
                   }
	public String toString(){
		String output = "";
		output += "Pulley Subsystem \n";
		output += "Top Pulley:\n";
                                     output += top;
                                     output += "Right Pulley:\n";
		output += right;
		output += "Left Pulley:\n";
                                     output += left;
		return output;
	}
}
