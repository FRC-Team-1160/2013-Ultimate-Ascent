package com.team1160.feathersMcGraw.arm;

import com.team1160.feathersMcGraw.api.Constants;
import com.team1160.feathersMcGraw.api.DigitalServo;
import com.team1160.feathersMcGraw.commands.ArmCommand;

//import edu.wpi.first.wpilibj.Relay.Value;

import edu.wpi.first.wpilibj.Relay;

public class Arm {
	
	protected Relay motor;
	protected DigitalServo grip;
	
	protected static Arm _INSTANCE;
	
	public static Arm getInstance(){
		if(_INSTANCE == null){
			_INSTANCE = new Arm();
		}
		return _INSTANCE;
	}
	
	private Arm() {
		motor = new Relay(Constants.A_MOTOR_CAR, Constants.A_MOTOR_CHAN);
		grip = new DigitalServo(Constants.G_SERVO_CAR,Constants.G_SERVO_CHAN);
	}
	
	public void set(ArmCommand command){
		if(command.direction == 1){
			motor.set(Relay.Value.kForward);
		}else if(command.direction == -1){
			motor.set(Relay.Value.kReverse);
		}else{
			motor.set(Relay.Value.kOff);
		}
		grip.set(command.grip);
	}

}
