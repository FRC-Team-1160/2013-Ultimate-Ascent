package com.team1160.feathersMcGraw.input;

import com.team1160.feathersMcGraw.input.inputStates.ArmStick;
import com.team1160.feathersMcGraw.input.inputStates.DriveStick;

public class InputState {
	public DriveStick driveStick;
	public ArmStick armStickOne;
	public ArmStick armStickTwo;
                   public SensorState sensorState;
        
	public InputState(){
		driveStick = new DriveStick();
		armStickOne = new ArmStick("Cooker 1");
		armStickTwo = new ArmStick("Cooker 2");
	}

	public String toString(){
		String output = "";
		output+="-------Input State\n";
		output+= driveStick;
		output+= armStickOne;
		output+= armStickTwo;
                                      output+= sensorState;
		return output;
	}
	
}
