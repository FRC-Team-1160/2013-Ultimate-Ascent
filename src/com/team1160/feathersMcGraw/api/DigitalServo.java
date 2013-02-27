package com.team1160.feathersMcGraw.api;

import edu.wpi.first.wpilibj.Servo;

public class DigitalServo extends Servo {

	protected double upperStop;
	protected double lowerStop;
	protected double lastSet;
	public boolean freed;
	
	public DigitalServo(int channel) {
		super(channel);
		freed = false;
		setBounds(184,0,0,0,67);
		setStops(0, 1);
	}

	public DigitalServo(int slot, int channel) {
		super(slot, channel);
		freed = false;
		setBounds(184,0,0,0,67);
		setStops(0, 1);
	}
	
	public void setStops(double bot, double top){
		this.upperStop = top;
		this.lowerStop = bot;
	}
	
	public void set(double set){
		if(set == 1337){
			toLastSet();
		}
		if(set > upperStop){
			set = upperStop;
		}else if(set < lowerStop){
			set = lowerStop;
		}
		lastSet = set;
		super.set(set);
	}
	
	public void toLastSet(){
		super.set(lastSet);
	}
        
	public void free(){
		freed = true;
		super.free();
	}

}
