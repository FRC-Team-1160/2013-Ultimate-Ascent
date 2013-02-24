package com.team1160.feathersMcGraw.input.inputStates;

/*
 * TODO add comments
 * 
 * @Author Wallace
 */

public class DriveStick {

	public boolean climbReleased;
	public boolean pulleyRelease;
	public boolean armRelease;
                   public boolean autoClimbRelease;
        
        
	public double x;
	public double y;
	
	public DriveStick() {
		x = 0;
		y = 0;
		climbReleased = true;
		pulleyRelease = true;
                                     armRelease = true;
        }
	
	public void setClimbRelease(boolean button){
		if(button && climbReleased){
			climbReleased = false;
		}else if(!button && !climbReleased){
			climbReleased = true;
		}
	}
	
	public void setPulleyRelease(boolean button){
		if(button && pulleyRelease){
			pulleyRelease = false;
		}else if(!button && !pulleyRelease){
			pulleyRelease = true;
		}
	}

                public void setArmRelease(boolean button){
                                      if(button && armRelease){
                                                    armRelease = false;
                                      }else if(!button && !armRelease){
                                                    armRelease = true;
                                      }
                }
                
                public void setAutoClimbRelease(boolean button){
                    if(button && autoClimbRelease){
                        autoClimbRelease = false;
                    }else if(!button && !autoClimbRelease){
                        autoClimbRelease = true;
                    }
                }
        
	public String toString(){
		String output = "";
		output+="--Drive Stick\n ";
		output+="Climb Release:   " + climbReleased + "\n";
		output+="Pulley Released: " + pulleyRelease + "\n";
		output+="X:               " + x + "\n";
		output+="Y:               " + y + "\n";
		return output;
	}
	
}
