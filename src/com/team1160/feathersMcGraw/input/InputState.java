package com.team1160.feathersMcGraw.input;

import com.team1160.feathersMcGraw.input.inputStates.ArmStick;
import com.team1160.feathersMcGraw.input.inputStates.DriveStick;

public class InputState {
	public DriveStick driveStick;
	public ArmStick rightArmStick;
	public ArmStick leftArmStick;
                   public SensorState sensorState;
        
	public InputState(){
		driveStick = new DriveStick();
		rightArmStick = new ArmStick("Cooker right");
		leftArmStick = new ArmStick("Cooker left");
	}

	public String toString(){
		String output = "";
		output+="-------Input State\n";
		output+= driveStick;
		output+= rightArmStick;
		output+= leftArmStick;
                                      output+= sensorState;
		return output;
	}
	
}
