package com.team1160.feathersMcGraw.pulley;

import com.team1160.feathersMcGraw.api.DigitalServo;
import com.team1160.feathersMcGraw.commands.PulleyCommand;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Servo;

public class Pulley {

	protected Jaguar motor;
	protected DigitalServo angle;
	protected Servo lock;
	
	public Pulley(Jaguar motor, DigitalServo angle, Servo lock){
		this.motor = motor;
		this.angle = angle;
		this.lock = lock;
	}
	
	public void setPulley(PulleyCommand command){
		this.motor.set(command.velocity);
		this.angle.set(command.angle);
		this.lock.set(0.0); // bullshit code TODO fix the lock command structure
	}
	
	

}
