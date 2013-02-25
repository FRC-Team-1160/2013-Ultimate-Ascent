package com.team1160.feathersMcGraw.input;

import com.team1160.feathersMcGraw.input.inputStates.ArmStick;
import com.team1160.feathersMcGraw.input.inputStates.DriveStick;
import com.team1160.feathersMcGraw.input.inputStates.ToggleBoard;

public class InputState {
	public DriveStick driveStick;
	public ArmStick rightArmStick;
	public ArmStick leftArmStick;
	public SensorState sensorState;
    public ToggleBoard toggleBoard;
	
	public InputState(){
		driveStick = new DriveStick();
		rightArmStick = new ArmStick("Cooker right");
		leftArmStick = new ArmStick("Cooker left");
		sensorState = new SensorState();
		toggleBoard = new ToggleBoard();
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
