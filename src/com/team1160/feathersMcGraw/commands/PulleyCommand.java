package com.team1160.feathersMcGraw.commands;

/*
 * The pulley command holds the data for 
 * the pulley, the position of the servo
 * position of pulley and the velocity of
 * the pulley.
 * 
 * @Author Wallce
 */


public class PulleyCommand {
	public double velocity;                 // The speed at which to extend
	public double length;                   // Length of pulley (no code uses this yet)
	public boolean locked;                  // If the ratchet should lock
	public double angle;                    // Angle of servo, it was protected for good reason
	public double home;
                  
	public PulleyCommand(){
		locked = false;
		velocity = 0;
		length = 1337;
	}
	
                   public void setHome(double newHome){
                        this.home = newHome;
                   }
                   
                   public void home(){
                       this.angle = home;
                   }                    
	
	public String toString(){
		String output = "";
		output += "Velocity: " + velocity + "\n";
		output += "Length: " + length + "\n";
		output += "Locked: " + locked + "\n";
		output += "Angle: " + angle + "\n";
		return output;
	}
	
}
