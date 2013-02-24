package com.team1160.feathersMcGraw.output;

import com.team1160.feathersMcGraw.commands.RobotCommand;
import com.team1160.feathersMcGraw.api.Constants;
import com.team1160.feathersMcGraw.api.DigitalServo;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Servo;

/* The output manager
 * outputs stuff... yeah
 * sorry the comments are probably going to die down in quality
 * im tired, cold, and hurried soooo i will come back in and fix this
 * i swear... if not and you want to see comments email the team and
 * i will fix it
 * 
 * @Author wallace
 */

/*
 * 
 * 
 */

public class OutputManager {

	/*
	 * These are all of the actuators on our bot
	 * they will be initialized in the constructor
	 */

	/*
	 * Working on cleaning this shit up... 
	 * by adding classes for us just to output to...
	 * this will also help with the pid code
	 * if it needs to be written
	 */
	
	
	private Servo gripperServo;
	private DigitalServo topPulleyServo;
	private Servo topPulleyLock;
	private DigitalServo rightPulleyServo;
	private Servo rightPulleyLock;
	private DigitalServo leftPulleyServo;
	private Servo leftPulleyLock;
	
	private Jaguar leftDT;
	private Jaguar rightDT;
	private Jaguar topPulleyJaguar;
	private Jaguar leftPulleyJaguar;
	private Jaguar rightPulleyJaguar;
	
	private RobotCommand currentCommand;
	private static OutputManager _INSTANCE;
	private OutputManager(){
		gripperServo = new Servo(Constants.G_SERVO_CAR, Constants.G_SERVO_CHAN);
		topPulleyServo = new DigitalServo(Constants.P_TOP_SERVO_CAR, Constants.P_TOP_SERVO_CHAN);
		topPulleyLock = new Servo(Constants.P_TOP_LOCK_CAR, Constants.P_TOP_LOCK_CHAN);
                                     rightPulleyServo = new DigitalServo(Constants.P_RIGHT_SERVO_CAR, Constants.P_RIGHT_SERVO_CHAN);
		rightPulleyLock = new Servo(Constants.P_RIGHT_LOCK_CAR, Constants.P_RIGHT_LOCK_CHAN);
		leftPulleyServo = new DigitalServo(Constants.P_LEFT_SERVO_CAR, Constants.P_LEFT_SERVO_CHAN);
		leftPulleyLock =  new Servo(Constants.P_LEFT_LOCK_CAR, Constants.P_LEFT_LOCK_CHAN);
		
		leftDT = new Jaguar(Constants.DT_LEFT_JAG_CAR, Constants.DT_LEFT_JAG_CHAN);
		rightDT = new Jaguar(Constants.DT_RIGHT_JAG_CAR, Constants.DT_RIGHT_JAG_CHAN);
		topPulleyJaguar = new Jaguar(Constants.P_TOP_JAG_CAR, Constants.P_TOP_JAG_CHAN);
		leftPulleyJaguar = new Jaguar(Constants.P_LEFT_JAG_CAR, Constants.P_LEFT_JAG_CHAN);
		rightPulleyJaguar = new Jaguar(Constants.P_RIGHT_JAG_CAR, Constants.P_RIGHT_JAG_CHAN);
	};
	public static OutputManager getInstance(){
		if(_INSTANCE == null){
			_INSTANCE = new OutputManager();
		}
		return _INSTANCE;
	}
	
	public void setOutput(RobotCommand currentCommand){
		this.currentCommand = currentCommand;
		setDriveTrain();
		setPulley();
		setArm();
	}
	private void setArm() {
		if(currentCommand.armCommand.grip){
			this.gripperServo.set(Constants.G_SERVO_GRIP);
		}else{
			this.gripperServo.set(Constants.G_SERVO_OPEN);
		}
		
	}
	private void setPulley() {
		setTopPulley();
		setRightPulley();
		setLeftPulley();
	}
	private void setTopPulley() {
		if(currentCommand.pulleySystemCommand.top.angle > Constants.P_TOP_MAX){
			topPulleyServo.set(Constants.P_TOP_MAX);
		}else if(currentCommand.pulleySystemCommand.top.angle < Constants.P_TOP_MIN){
			topPulleyServo.set(Constants.P_TOP_MIN);
		}else{
			topPulleyServo.set(currentCommand.pulleySystemCommand.top.angle);
		}
		
		if(currentCommand.pulleySystemCommand.top.locked){
			topPulleyLock.set(Constants.P_TOP_LOCK_LOCKED);
		}else{
			topPulleyLock.set(Constants.P_TOP_LOCK_OPEN);
		}
		
		this.topPulleyJaguar.set(currentCommand.pulleySystemCommand.top.velocity);
	}
	private void setRightPulley() {
		if(currentCommand.pulleySystemCommand.right.angle > Constants.P_RIGHT_MAX){
			rightPulleyServo.set(Constants.P_RIGHT_MAX);
		}else if(currentCommand.pulleySystemCommand.right.angle < Constants.P_RIGHT_MIN){
			rightPulleyServo.set(Constants.P_RIGHT_MIN);
		}else{
			rightPulleyServo.set(currentCommand.pulleySystemCommand.right.angle);
		}
		
		if(currentCommand.pulleySystemCommand.right.locked){
			rightPulleyLock.set(Constants.P_RIGHT_LOCK_LOCKED);
		}else{
			rightPulleyLock.set(Constants.P_TOP_LOCK_OPEN);
		}		
		
		this.rightPulleyJaguar.set(currentCommand.pulleySystemCommand.right.velocity);
	
	}
	private void setLeftPulley() {
		if(currentCommand.pulleySystemCommand.left.angle > Constants.P_LEFT_MAX){
			leftPulleyServo.set(Constants.P_LEFT_MAX);
		}else if(currentCommand.pulleySystemCommand.left.angle < Constants.P_LEFT_MIN){
			leftPulleyServo.set(Constants.P_LEFT_MIN);
		}else{
			leftPulleyServo.set(currentCommand.pulleySystemCommand.left.angle);
		}
		
		if(currentCommand.pulleySystemCommand.left.locked){
			leftPulleyLock.set(Constants.P_LEFT_LOCK_LOCKED);
		}else{
			leftPulleyLock.set(Constants.P_LEFT_LOCK_OPEN);
		}
	
		this.leftPulleyJaguar.set(currentCommand.pulleySystemCommand.left.velocity);
	
	}
	private void setDriveTrain() {
		leftDT.set(currentCommand.drivetrainCommand.left);
		rightDT.set(currentCommand.drivetrainCommand.right);
	}	
}