 package com.team1160.feathersMcGraw.pulley;

import com.team1160.feathersMcGraw.api.DigitalServo;
import com.team1160.feathersMcGraw.commands.PulleyCommand;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Servo;

public class Pulley {

	protected Jaguar motor;
	protected DigitalServo angle;
	protected Servo lock;
	protected double lockValue;
	protected double unlockValue;
	
	public Pulley(Jaguar motor, DigitalServo angle, Servo lock, double lockValue, double unlockValue){
		this.motor = motor;
		this.angle = angle;
		this.lock = lock;
		this.lockValue = lockValue;
		this.unlockValue = unlockValue;
	}
	
	public void setPulley(PulleyCommand command, int multiplier){
		// Multiplier is intended to be -1 -> 1 to show direction of motor
		if(command.locked){
			this.lock.set(lockValue);	
		}else{
			this.lock.set(unlockValue);
		}
		
		this.motor.set(command.velocity);
		this.angle.set(command.angle);
		}
	
	

}
