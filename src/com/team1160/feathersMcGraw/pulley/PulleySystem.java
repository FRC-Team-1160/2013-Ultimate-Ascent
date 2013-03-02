package com.team1160.feathersMcGraw.pulley;

import com.team1160.feathersMcGraw.api.DigitalServo;
import com.team1160.feathersMcGraw.commands.PulleySystemCommand;
import com.team1160.feathersMcGraw.api.Constants;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Servo;

public class PulleySystem {

	protected Pulley left;
	protected Pulley right;
	protected Pulley middle;
		
	private static PulleySystem _INSTANCE;
	
	public static PulleySystem getInstance(){
		if(_INSTANCE == null){
			_INSTANCE = new PulleySystem();
		}
		return _INSTANCE;
	}
	
	private PulleySystem() {
		left = new Pulley(new Jaguar(Constants.P_LEFT_JAG_CAR, Constants.P_LEFT_JAG_CHAN), new DigitalServo(Constants.P_LEFT_SERVO_CAR, Constants.P_LEFT_SERVO_CHAN), new Servo(Constants.P_LEFT_LOCK_CAR, Constants.P_LEFT_LOCK_CHAN), Constants.P_LEFT_LOCK_LOCKED, Constants.P_LEFT_LOCK_OPEN);
		right = new Pulley(new Jaguar(Constants.P_RIGHT_JAG_CAR, Constants.P_RIGHT_JAG_CHAN), new DigitalServo(Constants.P_RIGHT_SERVO_CAR,Constants.P_RIGHT_SERVO_CHAN), new Servo(Constants.P_RIGHT_LOCK_CAR, Constants.P_RIGHT_LOCK_CHAN), Constants.P_RIGHT_LOCK_LOCKED, Constants.P_RIGHT_LOCK_OPEN);
		middle = new Pulley(new Jaguar(Constants.P_TOP_JAG_CAR, Constants.P_TOP_JAG_CHAN), new DigitalServo(Constants.P_TOP_SERVO_CAR, Constants.P_TOP_SERVO_CHAN), new Servo(Constants.P_TOP_LOCK_CAR, Constants.P_TOP_LOCK_CHAN), Constants.P_TOP_LOCK_LOCKED, Constants.P_TOP_LOCK_OPEN);
	}
	
	public void setPulleySystem(PulleySystemCommand command){
		left.setPulley(command.left);
		right.setPulley(command.right);
		middle.setPulley(command.top);
	}

}
