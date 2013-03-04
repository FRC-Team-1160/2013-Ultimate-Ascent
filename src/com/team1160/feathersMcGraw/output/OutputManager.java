package com.team1160.feathersMcGraw.output;

import com.team1160.feathersMcGraw.arm.Arm;
import com.team1160.feathersMcGraw.commands.RobotCommand;
import com.team1160.feathersMcGraw.drive.DriveTrain;
import com.team1160.feathersMcGraw.pulley.PulleySystem;

/* 
 * The output manager
 * Originally handled all of the outputs
 * how ever has turned into a  class whos 
 * only job is to pass the commands to the
 * right sub systems to deal with
 * 
 * @Author wallace
 */


public class OutputManager {

	protected DriveTrain drivetrain;
	protected PulleySystem pulleySystem;
	protected Arm arm;
	
	private static OutputManager _INSTANCE;
	private OutputManager(){
		drivetrain = DriveTrain.getInstance();
		pulleySystem = PulleySystem.getInstance();
		arm = Arm.getInstance();
	}
		
	public static OutputManager getInstance(){
		if(_INSTANCE == null){
			_INSTANCE = new OutputManager();
		}
		return _INSTANCE;
	}
	
	public void setOutput(RobotCommand currentCommand){
		drivetrain.setDriveTrain(currentCommand.drivetrainCommand);
		pulleySystem.setPulleySystem(currentCommand.pulleySystemCommand);
		arm.set(currentCommand.armCommand);
	}
	
}